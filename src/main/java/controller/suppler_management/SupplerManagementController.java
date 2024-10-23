package controller.suppler_management;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Suppler;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplerManagementController implements SupplerManagementService{

    private static SupplerManagementController instance;

    private SupplerManagementController(){}

    public static SupplerManagementController getInstance(){
        return instance==null?instance=new SupplerManagementController():instance;
    }


    private CrudUtil util = CrudUtil.getInstance();
    @Override
    public Boolean addSuppler(Suppler suppler) {
        String SQL = "INSERT INTO suppler VALUES(?,?,?,?,?)";
       return util.execute(SQL,
                suppler.getId(),
                suppler.getName(),
                suppler.getCompany(),
                suppler.getEmail(),
                suppler.getCreateDate());

    }

    public String genarateId(){
        String SQL = "SELECT id FROM suppler ORDER BY id DESC LIMIT 1";
        ResultSet resultSet = util.execute(SQL);
        String lastId = null;
        try {
           while(resultSet.next()){
                lastId = resultSet.getString("id");
               String numericPart = lastId.substring(1);
               int number = Integer.parseInt(numericPart);
               number++;
               return String.format("S%03d", number);

           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public ObservableList<Suppler> loadTable() {
        ObservableList<Suppler> supplers = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM suppler";
        ResultSet resultSet = util.execute(SQL);
        try {
            while (resultSet.next()){
                supplers.add(
                        new Suppler(
                                resultSet.getString("id"),
                                resultSet.getString("name"),
                                resultSet.getString("company"),
                                resultSet.getString("email"),
                                resultSet.getDate("create_date").toLocalDate()
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(!supplers.isEmpty()){
            return supplers;
        }
        return null;
    }

    @Override
    public Boolean updateSuppler(Suppler suppler) {
        String SQL = "UPDATE suppler SET name=?,company=?,email=?";
       Boolean isUpdate = util.execute(SQL,suppler.getName(),suppler.getCompany(),suppler.getEmail());
       if(isUpdate){
           return true;
       }
        return null;
    }

    @Override
    public Boolean deleteSuppler(String id) {
        String SQL = "DELETE FROM suppler WHERE id=?";
        Boolean isDelete =  util.execute(SQL,id);
        if(isDelete){
            return true;
        }
        return false;
    }

}
