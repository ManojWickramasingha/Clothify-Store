package model;

import javafx.collections.ObservableList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String id;
    private LocalDate date;
    private LocalTime time;
    private ObservableList<OrderDetail> orderDetailList;
    private PlaceOrder invoice;
}
