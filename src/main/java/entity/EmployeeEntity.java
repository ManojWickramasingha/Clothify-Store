package entity;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeEntity {
    private String id;
    private String name;
    private String company;
    private String email;
}
