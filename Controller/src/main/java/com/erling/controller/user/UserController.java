package com.erling.controller.user;

import com.erling.entity.user.User;
import com.erling.service.user.ser.UserService;
import com.erling.utils.result.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/user/api")
public class UserController {
    UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 登录
     * @param user 包含用户邮箱和密码的登录请求体
     * @param result 参数校验结果绑定对象
     * @return 包含认证令牌的响应实体
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody @Valid User user,
            BindingResult result
    ){
        if(result.hasErrors()){
            return ResponseEntity.ok(
                    new Result<>(400,
                            Objects.requireNonNull(result.getFieldError()).getDefaultMessage(),
                            null
                    )
            );
        }
        return userService.Login(user);
    }

    /**
     * 注册
     * @param user 包含邮箱、密码等信息的注册请求体
     * @param result 参数校验结果绑定对象
     * @return 注册操作结果响应
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody @Valid User user,
            @RequestParam String code,
            BindingResult result
    ){
        if(result.hasErrors()){
            return ResponseEntity.ok(
                    new Result<>(400,
                            Objects.requireNonNull(result.getFieldError()).getDefaultMessage(),
                            null
                    )
            );
        }
        if(userService.checkCode(code)){
            return userService.
                    Register(user);
        }
        else{
            return ResponseEntity.ok(
                    new Result<>(400,
                            "验证码错误",
                            null
                    )
            );
        }

    }
    @GetMapping("/getCodeImage")
    public ResponseEntity<byte[]> getCode(){
        return userService.getCodeImage();
    }

    @GetMapping("/verifyToken")
    public ResponseEntity<Result<?>> verifyToken(
            @RequestHeader("Authorization") String token
    ){
        return userService.checkToken(token);
    }

    @GetMapping("/verifyToken_1")
    public ResponseEntity<Result<?>> verifyToken_1(
            HttpServletRequest request
    ){
        return userService.checkToken(request);
    }

}
