package work.umatech.security.vo;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class UserTable {

    @Id @GeneratedValue
    private Long id;

    private String username;

    private String password;

    private String role;

    @Column(unique = true)
    private String email;


}
