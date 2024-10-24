package service.custom;

import dto.Suppler;
import javafx.collections.ObservableList;
import service.SuperService;

public interface SupplerService extends SuperService {
    Boolean addSuppler(Suppler suppler);
    String genarateId();

    ObservableList<Suppler> loadTable();

    Boolean updateSuppler(Suppler suppler);

    Boolean deleteSuppler(String id);
}
