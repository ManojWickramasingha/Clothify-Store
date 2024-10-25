package entity;

import dto.OrderDetail;
import dto.PlaceOrder;
import javafx.collections.ObservableList;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderEntity {
    private String id;
    private LocalDate date;
    private LocalTime time;
    private ObservableList<OrderDetailEntity> orderDetailList;
    private PlaceOrderEntity invoice;
}
