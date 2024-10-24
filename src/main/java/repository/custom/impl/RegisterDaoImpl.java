package repository.custom.impl;

import entity.RegisterEntity;
import javafx.collections.ObservableList;
import repository.custom.RegisterDao;
import util.CrudUtil;

import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterDaoImpl implements RegisterDao {

    private CrudUtil util = CrudUtil.getInstance();
    @Override
    public Boolean save(RegisterEntity entity) {
        return null;
    }

    @Override
    public Boolean update(RegisterEntity entity) {
        return null;
    }

    @Override
    public ResultSet genarateIdms() {
        return null;
    }

    @Override
    public ObservableList getAll() {
        return null;
    }

    @Override
    public boolean userRegister(String password, String conPassword, String email, String name) {
        if(password.isEmpty() || name.isEmpty() || email.isEmpty()){
            System.out.println("Text filed are completed");
        }else{

            if(password.equals(conPassword) && isValidPassword(password)){
                if(isValideEmail(email)){
                    String SQL = "INSERT INTO register values(?,?,?,?)";


                    Boolean isAdded =   util.execute(SQL,email,password,null,name);

                    if(isAdded){
                        return true;
                    }


                } else{
                    System.out.println("Email is not valid");
                }
            }else{
                System.out.println("Password not match or password not valide");
            }

        }
        return false;
    }

    private boolean isValideEmail(String email) {
        String regexPattern = "^(.+)@(\\S+)$";
        Pattern p = Pattern.compile(regexPattern);
        if(email == null){
            return false;
        }
        Matcher matcher = p.matcher(email);
        return  matcher.matches();

    }

    private boolean isValidPassword(String password) {

        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        Pattern p = Pattern.compile(regex);
        if (password == null) {
            return false;
        }
        Matcher m = p.matcher(password);
        return m.matches();

    }
}
