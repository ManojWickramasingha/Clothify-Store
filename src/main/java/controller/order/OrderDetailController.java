package controller.order;

import entity.OrderDetailEntity;
import javafx.collections.ObservableList;
import dto.OrderDetail;
import util.CrudUtil;

public class OrderDetailController {
    private static OrderDetailController instance;
    private OrderDetailController(){}

    public static OrderDetailController getInstance(){
        return instance==null? instance = new OrderDetailController(): instance;
    }

    private CrudUtil util = CrudUtil.getInstance();

    public Boolean addOrderDetails(ObservableList<OrderDetail> orderDetails){
        for(OrderDetail obj : orderDetails){
          Boolean isAdd  = addOrderDetails(obj);
          if(!isAdd){
              return false;
          }
        }
        return true;
    }

    private Boolean addOrderDetails(OrderDetail orderDetail){
        String SQL = "INSERT INTO order_details values(?,?,?,?)";
       Boolean isAdd = util.execute(SQL,
                orderDetail.getOrderId(),
                orderDetail.getProductId(),
                orderDetail.getSize(),
                orderDetail.getQty());
       if(isAdd){
           return true;
       }
        return false;
    }


}
