package config;

import dto.Order;
import dto.OrderDetail;
import entity.OrderDetailEntity;
import entity.OrderEntity;
import entity.PlaceOrderEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.ArrayList;
import java.util.List;


public class MapperConfig {
    private static final ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.addMappings(new PropertyMap<Order, OrderEntity>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setDate(source.getDate());

                // Handle the conversion of ObservableList to List
                using(ctx -> {
                    List<OrderDetailEntity> orderDetailEntities = new ArrayList<>();
                    if (source.getOrderDetailList() != null) {
                        for (OrderDetail orderDetail : source.getOrderDetailList()) {
                            OrderDetailEntity detailEntity = modelMapper.map(orderDetail, OrderDetailEntity.class);
                            orderDetailEntities.add(detailEntity);
                        }
                    }
                    return orderDetailEntities;
                }).map(source.getOrderDetailList(), destination.getOrderDetailList());

                // Explicitly mapping PlaceOrder to PlaceOrderEntity
                using(ctx -> {
                    if (source.getInvoice() != null) {
                        return modelMapper.map(source.getInvoice(), PlaceOrderEntity.class);
                    }
                    return null;
                }).map(source.getInvoice(), destination.getInvoice());
            }
        });
    }

    public static OrderEntity mapToEntity(Order order) {
        return modelMapper.map(order, OrderEntity.class);
    }
}
