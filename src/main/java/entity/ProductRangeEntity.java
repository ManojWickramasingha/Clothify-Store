package entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductRangeEntity {
    private String id;
    private String type;
    private String size;
    private String style;
    private Integer categoryId;
}
