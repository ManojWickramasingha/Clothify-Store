package repository.custom.impl;

import dto.OrderDetail;
import entity.OrderDetailEntity;
import javafx.collections.ObservableList;
import repository.custom.OrderDetailDao;
import util.CrudUtil;

public class OrderDetailDaoImpl implements OrderDetailDao {

    final CrudUtil util = CrudUtil.getInstance();
    @Override
    public Boolean save(OrderDetailEntity entity) {
        return null;
    }

    @Override
    public Boolean update(OrderDetailEntity entity) {
        return null;
    }

    @Override
    public ObservableList<OrderDetailEntity> getAll() {
        return null;
    }

    @Override
    public Boolean addOrderDetails(ObservableList<OrderDetailEntity> orderDetails) {
        for(OrderDetailEntity obj : orderDetails){
            Boolean isAdd  = addOrderDetails(obj);
            if(!isAdd){
                return false;
            }
        }
        return true;
    }

    private Boolean addOrderDetails(OrderDetailEntity orderDetail){
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
