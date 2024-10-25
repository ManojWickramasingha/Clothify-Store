package repository.custom.impl;

import controller.order.OrderDetailController;
import controller.product_management.ProductManagementController;
import db.DbConnection;
import dto.Product;
import entity.OrderEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.OrderDao;
import repository.custom.OrderDetailDao;
import repository.custom.ProductDao;
import service.ServiceFactory;
import service.custom.ProductService;
import util.CrudUtil;
import util.DaoType;
import util.ServiceType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDaoImpl implements OrderDao {

    final CrudUtil util =CrudUtil.getInstance();
    final OrderDetailDao orderDetails = DaoFactory.getInstance().getDaoType(DaoType.ORDERDETAILS);
    final ProductDao productrepo = DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);
    final ProductService service = ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);

    ModelMapper mapper = new ModelMapper();
    @Override
    public Boolean save(OrderEntity order) {
        String SQL = "INSERT INTO orders values(?,?,?)";
        Connection connection = null;

        try {
            connection   = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setObject(1,order.getId());
            psTm.setObject(2,order.getDate());
            psTm.setObject(3,order.getTime());
            Boolean isAdd = psTm.executeUpdate()>0;
            if(isAdd){
                Boolean isAddOrderDetails = orderDetails.addOrderDetails(order.getOrderDetailList());
                if(isAddOrderDetails){
                    Boolean isUpdateStock = productrepo.updateStoke(order.getOrderDetailList());
                    if(isUpdateStock){
                        new Alert(Alert.AlertType.CONFIRMATION,"Stock Update successful").show();
                        connection.commit();
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Boolean update(OrderEntity entity) {
        return null;
    }

    @Override
    public ObservableList<OrderEntity> getAll() {
        return null;
    }

    @Override
    public String invoiceNumber() {
        String SQL = "SELECT invoice FROM place_order ORDER BY invoice DESC LIMIT 1";
        ResultSet resultSet = util.execute(SQL);
        try {
            while(resultSet.next()){
                String lastInvoice = resultSet.getString("invoice");
                String sub = lastInvoice.substring(1);
                Integer number = Integer.parseInt(sub);
                number++;
                return String.format("I%03d", number);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "I001";
    }

    @Override
    public String genarateId() {
        String SQL = "SELECT id FROM orders ORDER BY id DESC LIMIT 1";
        ResultSet resultSet = util.execute(SQL);
        try {
            while(resultSet.next()){
                String lastId = resultSet.getString("id");
                String lastNum = lastId.substring(1);
                Integer num = Integer.parseInt(lastNum);
                num++;
                return String.format("O%03d",num);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "O001";

    }

    @Override
    public ObservableList<String> getProductId() {
        String SQL = "SELECT id FROM product Order by id DESC";
        ResultSet resultSet =  util.execute(SQL);
        ObservableList<String> productIds = FXCollections.observableArrayList();
        try {
            while(resultSet.next()){
                productIds.add(
                        resultSet.getString("id")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(!productIds.isEmpty()){
            return productIds;
        }
        return null;
    }

    @Override
    public ObservableList<String> getSize() {
        String SQL = "SELECT size FROM category_range ORDER BY size DESC";
        ResultSet resultSet = util.execute(SQL);
        ObservableList<String> sizes = FXCollections.observableArrayList();
        try {
            while(resultSet.next()){
                sizes.add(
                        resultSet.getString("size")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(!sizes.isEmpty()){
            return sizes;
        }
        return null;
    }

    @Override
    public Product search(String id) {
        String SQL = "SELECT * FROM product WHERE id=?";
        ResultSet resultSet = util.execute(SQL,id);
        try {
            while(resultSet.next()){
                return new Product(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("categoryId"),
                        resultSet.getString("size"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("qty"),
                        resultSet.getString("suppler_id")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
