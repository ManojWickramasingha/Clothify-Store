package entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDetailEntity {
    private String orderId;
    private String productId;
    private String size;
    private Integer qty;
}
