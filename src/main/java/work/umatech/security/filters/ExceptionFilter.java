package work.umatech.security.filters;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import work.umatech.security.config.Dictionary;
import work.umatech.security.exception.AuthFailException;
import work.umatech.security.exception.ServerException;
import work.umatech.security.vo.Response;

import java.io.IOException;

@Slf4j
@Component
public class ExceptionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        }  catch (AuthFailException | ServerException e) {
            log.error("Auth or Server Exception");
            log.error("ExceptionFilter: " + e.getMessage());
            ObjectMapper objectMapper = new ObjectMapper();
            Response response = new Response(Dictionary.STATUS_FAIL,e.getMessage());
            servletResponse.getWriter().write(objectMapper.writeValueAsString(response));
        } catch (Exception e) {
            log.error("General Exception");
            log.error("ExceptionFilter: " + e.getMessage());
            ObjectMapper objectMapper = new ObjectMapper();
            Response response = new Response(Dictionary.STATUS_FAIL, e.getMessage());
            servletResponse.getWriter().write(objectMapper.writeValueAsString(response));
        }
    }


}
