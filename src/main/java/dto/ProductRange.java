package dto;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductRange {
    private String id;
    private String type;
    private String size;
    private String style;
    private Integer categoryId;
}
