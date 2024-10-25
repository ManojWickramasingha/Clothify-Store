package service.custom.impl;

import config.MapperConfig;
import dto.Cart;
import dto.Order;
import dto.Product;
import entity.OrderEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.OrderDao;
import service.custom.OrderService;
import util.DaoType;

import java.time.LocalDate;

public class OrderServiceImpl implements OrderService {

    private  Double NET = 0.0;
    final OrderDao repository = DaoFactory.getInstance().getDaoType(DaoType.ORDER);
    private ObservableList<Cart> cartObservableList = FXCollections.observableArrayList();

    final ModelMapper mapper = new ModelMapper();
    @Override
    public String genarateId() {
        return repository.genarateId();
    }

    @Override
    public LocalDate genarateDate() {
        return LocalDate.now();
    }

    @Override
    public ObservableList<String> getProductId() {
        return repository.getProductId();
    }

    @Override
    public ObservableList<String> getSize() {
        return repository.getSize();
    }

    @Override
    public Product search(String id) {
        return repository.search(id);
    }

    @Override
    public ObservableList<Cart> addcartList(Cart cart) {
        boolean add = cartObservableList.add(cart);
        if(add){
            calcTotal(cart.getTotal());
            return cartObservableList;
        }
        return null;
    }

    private Double calcTotal(Double total) {
        NET += total;
        return NET;
    }

    @Override
    public Double getCalTotal() {
        return NET;
    }

    @Override
    public String invoiceNumber() {
        return repository.invoiceNumber();
    }

    @Override
    public Boolean placeOrder(Order order) {
        OrderEntity orderEntity = MapperConfig.mapToEntity(order);
       return repository.save(orderEntity);
    }

}
