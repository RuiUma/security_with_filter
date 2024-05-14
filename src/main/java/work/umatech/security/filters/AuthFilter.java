package work.umatech.security.filters;
import jakarta.annotation.Resource;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import work.umatech.security.config.Dictionary;
import work.umatech.security.config.HeaderMapRequestWrapper;
import work.umatech.security.exception.AuthFailException;
import work.umatech.security.service.AuthService;
import work.umatech.security.service.JwtService;
import work.umatech.security.service.RedisService;
import work.umatech.security.vo.UserTable;

import java.io.IOException;
import java.util.Map;


@Slf4j
@Component
public class AuthFilter implements Filter {

    @Resource
    JwtService jwtService;

    @Resource
    AuthService authService;

    @Resource
    RedisService redisService;

    @Value("${spring.isDev}")
    String isDev;

    @Value("${jwt.expirationTime}")
    Long redisTimeOutInSec;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("start auth filter");


        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String path = httpRequest.getServletPath();
        log.info(path);
        if (authService.checkUrlPassAuth(path)) {
            log.info("path to auth...");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        Cookie[] cookies = httpRequest.getCookies();

        if ("true".equals(isDev)) {
            log.info("dev mode");
            HeaderMapRequestWrapper requestWrapper = new HeaderMapRequestWrapper(httpRequest);

            requestWrapper.addHeader("userName", "testUserName");
            requestWrapper.addHeader("role", "testRole");
            requestWrapper.addHeader("email", "testEmail");
//            throw new AuthFailException("test auth");

            filterChain.doFilter(requestWrapper, servletResponse);
            return;
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

                    Map<String, Object> objectMap = (Map<String, Object>)o;
                    HeaderMapRequestWrapper requestWrapper = new HeaderMapRequestWrapper(httpRequest);
                    requestWrapper.addHeader(Dictionary.USER_NAME, objectMap.get(Dictionary.USER_NAME).toString());
                    requestWrapper.addHeader(Dictionary.USER_ROLE, objectMap.get(Dictionary.USER_ROLE).toString());
                    requestWrapper.addHeader(Dictionary.USER_EMAIL, objectMap.get(Dictionary.USER_EMAIL).toString());

                    Boolean renew = redisService.renewExpireTime(token, redisTimeOutInSec);
                    if (!renew) {
                        redisService.setWithTimeout(token,objectMap,redisTimeOutInSec);
                    }
                    filterChain.doFilter(requestWrapper, servletResponse);
                }
            }
        }
        log.info("end auth filter");





    }
}
