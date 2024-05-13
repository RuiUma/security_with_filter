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
import work.umatech.security.exception.ServerException;
import work.umatech.security.vo.Response;


@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(AuthFailException.class)
    public ResponseEntity<Response> authExceptionHandler(AuthFailException exception) {
        log.error("auth exception");
        log.error(exception.getMessage());
        Response response = new Response(Dictionary.STATUS_FAIL, exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<Response> serverExceptionHandler(ServerException exception) {
        log.error("server exception");
        log.error(exception.getMessage());
        Response response = new Response(Dictionary.STATUS_FAIL, exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> exceptionHandler(Exception exception) {
        log.error("exception");
        log.error(exception.getMessage());
        Response response = new Response(Dictionary.STATUS_FAIL, exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}



