package service.custom.impl;

import controller.product_range.ProductRangeService;
import dto.ProductRange;
import entity.ProductRangeEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.ProductRangeDao;
import util.DaoType;


public class productRangeServiceImpl implements ProductRangeService {

    final ProductRangeDao repository = DaoFactory.getInstance().getDaoType(DaoType.RANGE);

    final ModelMapper mapper = new ModelMapper();
    @Override
    public ObservableList<ProductRange> loadTable() {
        ObservableList<ProductRangeEntity> productEntityList = repository.getAll();
        ObservableList<ProductRange>  productRangeList = FXCollections.observableArrayList();
        for(ProductRangeEntity productRangeEntity : productEntityList){
          productRangeList.add(mapper.map(productRangeEntity,ProductRange.class));
        }
        return productRangeList;
    }



    @Override
    public Boolean addProductRange(ProductRange productRange) {
      return   repository.save(mapper.map(productRange, ProductRangeEntity.class));

    }

    @Override
    public String genarateId() {
        return repository.findLastId();
    }

    @Override
    public Boolean deleteRange(String id) {
        return repository.deleteById(id);
    }

    @Override
    public Boolean updateRange(ProductRange productRange) {
        return repository.update(mapper.map(productRange,ProductRangeEntity.class));
    }
}
