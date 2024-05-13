package work.umatech.security.controller;


import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import work.umatech.security.config.Dictionary;
import work.umatech.security.exception.AuthFailException;
import work.umatech.security.service.AuthService;
import work.umatech.security.service.BcryptService;
import work.umatech.security.service.JwtService;
import work.umatech.security.service.UserService;
import work.umatech.security.vo.Response;
import work.umatech.security.vo.UserTable;


import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    AuthService authService;

    @Resource
    BcryptService bcryptService;

    @Resource
    UserService userService;

    @Resource
    JwtService jwtService;


    @PostMapping("/login")
    public Response login(@RequestHeader Map<String, String> headers, @RequestBody UserTable user) {
        log.info(headers.toString());
        UserTable userRes = userService.getUserByEmail(user.getEmail());
        if (userRes == null) {
            throw new AuthFailException("User not found");
        }

        if(!bcryptService.verify(user.getPassword(), userRes.getPassword())) {
            throw new AuthFailException("Password does not match");
        }
        return new Response(authService.genTokenFromUser(userRes));
    }

    @PostMapping("/register")
    public Response register(@RequestBody UserTable newUser) {
        if(null != userService.getUserByEmail(newUser.getEmail())) {
            throw new AuthFailException("User Already Exists");
        }

        newUser.setPassword(bcryptService.bcryptEncode(newUser.getPassword()));

        UserTable saveResult = userService.save(newUser);
        if (saveResult == null) {
            throw new AuthFailException("Register failed");
        }

        return new Response(authService.genTokenFromUser(saveResult));
    }


}
