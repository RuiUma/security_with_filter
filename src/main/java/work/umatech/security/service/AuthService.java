package work.umatech.security.service;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import work.umatech.security.config.Dictionary;
import work.umatech.security.vo.UserTable;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class AuthService {


    private final JwtService jwtService;


    private final RedisService redisService;

    @Value("${jwt.expirationTime}")
    Long redisTimeOutInSec;

    public AuthService(JwtService jwtService, RedisService redisService) {
        this.jwtService = jwtService;
        this.redisService = redisService;
    }

    public String genToken(UserTable user) {
        Map<String, Object> claim = new java.util.HashMap<>();
        claim.put(Dictionary.USER_NAME, user.getUsername());
        claim.put(Dictionary.USER_EMAIL, user.getEmail());
        claim.put(Dictionary.USER_ROLE, user.getRole());
        String subject = "";

        return jwtService.generateToken(claim, subject);

    }

    private UserTable verifyToken(String token) {
        return (UserTable)redisService.get(token);
    }

    public String  genTokenFromUser(UserTable user) {
        Map<String, Object> claim = new HashMap<>();
        claim.put(Dictionary.USER_NAME, user.getUsername());
        claim.put(Dictionary.USER_EMAIL, user.getEmail());
        claim.put(Dictionary.USER_ROLE, user.getRole());
        String jwt = jwtService.generateToken(claim, Dictionary.SUBJECT);
        redisService.setWithTimeout(jwt,claim, redisTimeOutInSec);
        return jwt;
    }

    public Boolean checkUrlPassAuth(String requestPath) {
        for(String url: Dictionary.AUTH_URL_LIST) {
            log.info(url);
            if (requestPath.startsWith(url)) {
                return true;
            }
        }
        return false;
    }



}
