package entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductEntity {
    private String id;
    private String name;
    private Integer categoryId;
    private String size;
    private Double price;
    private Integer qty;
    private String supperId;
}
