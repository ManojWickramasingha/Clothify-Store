package service.custom.impl;

import dto.Category;
import dto.OrderDetail;
import dto.Product;
import dto.Suppler;
import entity.ProductEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.CategoryDao;
import repository.custom.ProductDao;
import repository.custom.SupplerDao;
import service.custom.ProductService;
import util.DaoType;

public class ProductServiceImpl implements ProductService {
    private ProductDao repository = DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);

    private SupplerDao s_repository = DaoFactory.getInstance().getDaoType(DaoType.SUPPLER);

    private CategoryDao c_repository = DaoFactory.getInstance().getDaoType(DaoType.CATEGORY);
    private ModelMapper mapper = new ModelMapper();
    @Override
    public boolean addProduct(Product product) {
       Boolean isAdd = repository.save(mapper.map(product, ProductEntity.class));
       if(isAdd){
           return true;
       }
        return false;
    }

    @Override
    public ObservableList<String> getAllSupplerId() {
      return   s_repository.idList();

    }

    @Override
    public Product searchproduct(String id) {
        ProductEntity productEntity = repository.findById(id);
       return mapper.map(productEntity,Product.class);

    }

    @Override
    public boolean deleteProduct(String id) {
       Boolean isDelete = repository.deleteById(id);
       if(isDelete){
           return true;
       }
        return false;
    }

    @Override
    public boolean updateProduct(Product product) {
      Boolean isUpdate =  repository.update(mapper.map(product, ProductEntity.class));
      if(isUpdate){
          return true;
      }
        return false;
    }

    @Override
    public ObservableList<Integer> getAllCategoryId()
    {
       return c_repository.getAllIds();

    }

    @Override
    public Boolean updateStoke(ObservableList<OrderDetail> orderDetailList) {
        return null;
    }
}
