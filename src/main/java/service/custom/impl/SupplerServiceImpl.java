package service.custom.impl;

import dto.Suppler;
import entity.SupplerEntity;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.SupplerDao;
import service.custom.SupplerService;
import util.DaoType;

public class SupplerServiceImpl implements SupplerService {

    private SupplerDao repository = DaoFactory.getInstance().getDaoType(DaoType.SUPPLER);

    private ModelMapper mapper = new ModelMapper();


    @Override
    public Boolean addSuppler(Suppler suppler) {
      return  repository.save(mapper.map(suppler, SupplerEntity.class));

    }

    @Override
    public String genarateId() {
        return null;
    }

    @Override
    public ObservableList<Suppler> loadTable() {
        return null;
    }

    @Override
    public Boolean updateSuppler(Suppler suppler) {

        return  repository.update(mapper.map(suppler, SupplerEntity.class));
    }

    @Override
    public Boolean deleteSuppler(String id) {
      return repository.deleteById(id);

    }
}
