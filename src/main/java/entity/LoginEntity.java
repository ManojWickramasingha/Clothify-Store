package entity;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginEntity {
    private String email;
    private String password;
    private String role;
    private String name;
}
