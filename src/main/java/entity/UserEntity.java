package entity;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserEntity {
    private String email;
    private String password;
    private String role;
    private String name;
}
