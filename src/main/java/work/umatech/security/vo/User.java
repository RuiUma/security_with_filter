package work.umatech.security.vo;

import lombok.Data;


@Data
public class User {
    private String userName;
    private String password;
    private String role;
    private String email;
}
