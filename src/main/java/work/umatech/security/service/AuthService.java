package work.umatech.security.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.umatech.security.config.Dictionary;
import work.umatech.security.vo.User;
import java.util.Map;

@Service
public class AuthService {


    private final JwtService jwtService;


    private final RedisService redisService;

    public AuthService(JwtService jwtService, RedisService redisService) {
        this.jwtService = jwtService;
        this.redisService = redisService;
    }

    public String genToken(User user) {
        Map<String, Object> claim = new java.util.HashMap<>();
        claim.put(Dictionary.USER_NAME, user.getUserName());
        claim.put(Dictionary.USER_EMAIL, user.getEmail());
        claim.put(Dictionary.USER_ROLE, user.getRole());
        String subject = "";

        return jwtService.generateToken(claim, subject);

    }

    private User verifyToken(String token) {
        return (User)redisService.get(token);
    }



}
