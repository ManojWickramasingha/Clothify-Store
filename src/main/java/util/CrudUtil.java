package util;

import db.DbConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudUtil {

    private static CrudUtil instance;

    private CrudUtil(){}

    public static CrudUtil getInstance(){
        return instance==null? instance = new CrudUtil():instance;
    }

    public <T>T execute(String SQL,Object...args){
        PreparedStatement psTm = null;
        try {
            psTm = DbConnection.getInstance().getConnection().prepareStatement(SQL);

            for(int i=0;i< args.length;i++){
                psTm.setObject(i+1,args[i]);
            }

            if(SQL.startsWith("SELECT") || SQL.startsWith("select")){
                return (T) psTm.executeQuery();
            }else{
               return (T) (Boolean) (psTm.executeUpdate()>0);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }




    }
}
