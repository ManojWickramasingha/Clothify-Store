package controller.suppler_management;

import javafx.collections.ObservableList;
import model.Suppler;

public interface SupplerManagementService {
    Boolean addSuppler(Suppler suppler);
    String genarateId();

    ObservableList<Suppler> loadTable();

    Boolean updateSuppler(Suppler suppler);

    Boolean deleteSuppler(String id);



}
