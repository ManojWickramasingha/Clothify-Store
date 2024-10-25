package entity;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PlaceOrderEntity {
    private String orderId;
    private String invoice;
}
