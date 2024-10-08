package work.umatech.security.filters;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class LogFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("Starting ........");
//        log.info(servletRequest.toString());
        filterChain.doFilter(servletRequest, servletResponse);
//        log.info(servletResponse.toString());
        log.info("Ending ........");
    }
}

