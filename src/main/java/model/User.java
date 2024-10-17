package model;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private String email;
    private String password;
}
