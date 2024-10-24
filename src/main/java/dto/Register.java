package dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Register {
    private String email;
    private String password;
    private String role;
    private String name;
}
