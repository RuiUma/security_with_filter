package work.umatech.security.service;
import org.springframework.stereotype.Service;
import work.umatech.security.vo.User;
import java.util.Map;

@Service
public class AuthService {
    public User getUserInfo(Map<String, String> headers) {
        User user = new User();
        user.setUserName(headers.get("userName"));
        user.setRole(headers.get("role"));
        user.setEmail(headers.get("email"));
        return user;
    }
}
