package work.umatech.security.exception;

import java.io.Serial;

public class ServerException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    public ServerException(String message) {
        super(message);
    }
}
