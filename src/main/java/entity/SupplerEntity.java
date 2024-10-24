package entity;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SupplerEntity {
    private String id;
    private String name;
    private String company;
    private String email;
    private LocalDate createDate;
}
