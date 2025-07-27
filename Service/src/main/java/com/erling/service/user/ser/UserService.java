package com.erling.service.user.ser;

import com.erling.dao.user.UserMapper;
import com.erling.entity.user.User;
import com.erling.utils.jwt.JwtUtils;
import com.erling.utils.log.Logger;
import com.erling.utils.passworld.PasswordUtils;
import com.erling.utils.result.Result;
import com.google.code.kaptcha.Producer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Objects;

import static com.erling.utils.jwt.JwtUtils.EXPIRATION_MS;

@Service
public class UserService {
    UserMapper  userMapper;

    HttpServletRequest request;

    String code;
    Producer  producer;
    @Autowired
    public UserService(
            UserMapper userMapper,
            HttpServletRequest request,
            Producer producer
    ) {
        this.userMapper = userMapper;
        this.request = request;
        this.producer = producer;

    }
    public ResponseEntity<Result<?>> Login(User user) {
        User u = userMapper.getUserByEmail(user.getEmail());
        if (u != null) {
            if (PasswordUtils.VerifyPassword(user.getPasswordHash(), u.getPasswordHash())) {
                String token = JwtUtils.generateToken(user.getEmail());
                return ResponseEntity.ok()
//                        .header("Authorization", token)
                        .header(HttpHeaders.SET_COOKIE,
                                String.format("jwt_token=%s; Path=/; HttpOnly; Max-Age=%d; SameSite=Strict",
                                        token,
                                        EXPIRATION_MS/1000))
                        .body(new Result<>(
                                200,
                                "登录成功",
                               token
                            )
                        );
            } else {
                return ResponseEntity.ok(
                        new Result<>(
                                200,
                                "密码错误",
                                null
                        )
                );
            }
        }
        return ResponseEntity.ok(
                new Result<>(
                        200,
                        "邮箱未注册",
                        null
                )
        );
    }
    public ResponseEntity<Result<?>> Register(@Valid User user) {
        User u = userMapper.getUserByEmail(user.getEmail());

        if (u != null) {
            return ResponseEntity.ok(
                    new Result<>(
                            400,
                            "邮箱已注册",
                            null
                    )
            );
        }
        try{
            user.setCreatedAt(LocalDateTime.now());
            user.setPasswordHash(PasswordUtils.EncodePassword(user.getPasswordHash()));
            boolean b = userMapper.insertUser(user);

            return ResponseEntity.ok(
                    new Result<>(
                            200,
                            "注册成功",
                            b
                    )
            );

        }catch (Exception e){
            Logger.getLogger(UserService.class).error(e.getMessage());
            return ResponseEntity.ok(
                    new Result<>(
                            400,
                            "注册失败",
                            null
                    )
            );
        }
    }
    public ResponseEntity<Result<?>> getAll(){
        return ResponseEntity.ok(
                new Result<>(
                        200,
                        "success",
                        userMapper.getAllUsers()
                )
        );
    }

    public ResponseEntity<byte[]> getCodeImage() {
        try {
            code = producer.createText();
            System.out.println(code);
            BufferedImage image = producer.createImage(code);

            ByteArrayOutputStream bass = new ByteArrayOutputStream();
            ImageIO.write(image, "jpeg", bass);
            byte[] imageBytes = bass.toByteArray();

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(imageBytes);
        } catch (IOException e) {
            Logger.getLogger(UserService.class).error("验证码生成失败: {}", e.getMessage());
            return ResponseEntity.
                    status(
                    HttpStatus.INTERNAL_SERVER_ERROR
                    ).
                    build();
        }

    }
    public ResponseEntity<Result<?>> checkToken(String token) {
        if(JwtUtils.isTokenExpired(token)){
            return ResponseEntity.ok(
                    new Result<>(
                            200,
                            "token验证成功",
                            null
                    )
            );
        }
        else{
            return ResponseEntity.ok(
                    new Result<>(
                            400,
                            "token验证失败",
                            null
                    )
            );
        }
    }
    public ResponseEntity<Result<?>> checkToken(HttpServletRequest request) {
//        String token = request.getHeader("Authorization");
        String token = request.getCookies()[0].getValue();
        System.out.println(token);
        if(token == null){
            return ResponseEntity.ok(
                    new Result<>(
                            400,
                            "token为空",
                            null
                    )
            );
        }
        return checkToken(token);
    }
    public boolean checkCode(String inputCode) {
        return Objects.equals(inputCode, code);
    }
    public boolean checkToken_s(String token) {
        return JwtUtils.isTokenExpired(token);
    }
}
