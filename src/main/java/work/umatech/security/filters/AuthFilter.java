package work.umatech.security.filters;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import work.umatech.security.config.Dictionary;
import work.umatech.security.config.HeaderMapRequestWrapper;
import work.umatech.security.exception.AuthFailException;
import work.umatech.security.service.JwtService;
import work.umatech.security.service.RedisService;
import work.umatech.security.vo.User;

import java.io.IOException;
import java.util.Arrays;




@Slf4j
@Component
public class AuthFilter implements Filter {

    @Autowired
    JwtService jwtService;

    @Autowired
    RedisService redisService;

    @Value("${spring.isDev}")
    String isDev;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("start auth filter");

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        Cookie[] cookies = httpRequest.getCookies();

        if ("true".equals(isDev)) {
            log.info("dev mode");
            HeaderMapRequestWrapper requestWrapper = new HeaderMapRequestWrapper(httpRequest);

            requestWrapper.addHeader("userName", "testUserName");
            requestWrapper.addHeader("role", "testRole");
            requestWrapper.addHeader("email", "testEmail");
            throw new AuthFailException("test auth");

//            filterChain.doFilter(requestWrapper, servletResponse);
//            return;
        }

        if (cookies != null) {
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("token")){
                    log.info("token found");
                    String token = cookie.getValue();
                    Object o = redisService.get(token);
                    if(o == null) {
                        log.info("token not valid");
                        throw new AuthFailException("token not valid");
                    }

                    User user = (User)o;
                    HeaderMapRequestWrapper requestWrapper = new HeaderMapRequestWrapper(httpRequest);
                    requestWrapper.addHeader(Dictionary.USER_NAME, user.getUserName());
                    requestWrapper.addHeader(Dictionary.USER_ROLE, user.getRole());
                    requestWrapper.addHeader(Dictionary.USER_EMAIL, user.getEmail());



                    filterChain.doFilter(requestWrapper, servletResponse);


                }
            }
        }
        log.info("end auth filter");





    }
}
