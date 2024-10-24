package entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryEntity {
    private Integer id;
    private String name;
    private String description;
}
