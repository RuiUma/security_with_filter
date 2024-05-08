package work.umatech.security.exception;

import java.io.Serial;

public class AuthFailException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public AuthFailException(String message) {
        super(message);
    }
}
