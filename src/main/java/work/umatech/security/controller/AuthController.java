package work.umatech.security.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.umatech.security.exception.AuthFailException;
import work.umatech.security.service.AuthService;
import work.umatech.security.vo.User;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;


    @PostMapping("/login")
    public String login(@RequestHeader Map<String, String> headers) {
//        User user = authService.getUserInfo(headers);
//        return user.toString();
        throw new AuthFailException("auth filter exception");
    }
}
