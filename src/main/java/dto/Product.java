package dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
    private String id;
    private String name;
    private Integer categoryId;
    private String size;
    private Double price;
    private Integer qty;
    private String supperId;
}
