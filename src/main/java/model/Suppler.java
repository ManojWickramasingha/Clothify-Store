package model;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Suppler {
    private String id;
    private String name;
    private String company;
    private String email;
    private LocalDate createDate;
}
