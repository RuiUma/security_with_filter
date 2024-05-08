package work.umatech.security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import work.umatech.security.exception.AuthFailException;


@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(AuthFailException.class)
    @ResponseBody
    public ResponseEntity<Object> authExceptionHandler(AuthFailException exception) {
        log.error("auth exception");
        log.error(exception.getMessage());
        String bodyOfResponse = "This should be application specific";
        return new ResponseEntity<>(bodyOfResponse, HttpStatus.CONFLICT);
    }



    @ExceptionHandler(Exception.class)
    public void exceptionHandler(Exception exception) {
        log.error("exception");
        log.error(exception.getMessage());
    }
}



