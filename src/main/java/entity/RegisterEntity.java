package entity;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterEntity {
    private String password;
    private String conPassword;
    private String name;
    private String email;
}
