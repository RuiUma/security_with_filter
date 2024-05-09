package work.umatech.security.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;
import work.umatech.security.config.Dictionary;
import work.umatech.security.config.Status;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Response implements Serializable {

    private Integer status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String err;
    private Object data;

//    private List<ApiSubError> subErrors;

    private Response() {
        timestamp = LocalDateTime.now();
        status = Dictionary.STATUS_SUCCESS;
    }

    public Response(Integer status) {
        this();
        this.status = status;
    }

    public Response(Integer status, String err) {
        this();
        this.status = status;
        this.err = err;
    }

    public Response(Integer status, String err, Object data) {
        this();
        this.status = status;
        this.data = data;
        this.err = err;
    }
}



