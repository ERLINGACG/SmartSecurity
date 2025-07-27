package com.erling.controller.user;

import com.erling.entity.user.User;
import com.erling.service.user.ser.UserService;
import com.erling.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/test")
public class UserControllerTest {
    UserService  userService;

    public UserControllerTest(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        return userService.Login(user);
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user, BindingResult result){

        return userService.Register(user);
    }


    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return userService.getAll();
    }
}
