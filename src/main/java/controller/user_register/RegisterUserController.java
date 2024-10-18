package controller.user_register;

import db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterUserController implements RegisterUserService{
    public static RegisterUserController instance;

    private RegisterUserController(){}

    public static RegisterUserController getInstance(){
        return instance==null?instance = new RegisterUserController():instance;
    }
    @Override
    public boolean userReister(String password,String cPassword,String email,String name) {
        if(password.isEmpty() || name.isEmpty() || email.isEmpty()){
            System.out.println("Text filed are completed");
        }else{

            if(password.equals(cPassword) && isValidPassword(password)){
                if(isValideEmail(email)){
                    try {

                        Connection connection = DbConnection.getInstance().getConnection();
                        PreparedStatement psTm = connection.prepareStatement("INSERT INTO register values(?,?,?,?)");
                        psTm.setObject(1,email);
                        psTm.setObject(2,password);
                        psTm.setObject(3,null);
                        psTm.setObject(4,name);

                        boolean isAdded = psTm.executeUpdate()>0;

                        if(isAdded){
                            return true;
                        }

                    } catch (SQLException e) {
                        throw new RuntimeException(e);
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



    private boolean isValidPassword(String password){

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
    private boolean isValideEmail(String email){
        String regexPattern = "^(.+)@(\\S+)$";
        Pattern p = Pattern.compile(regexPattern);
        if(email == null){
            return false;
        }
        Matcher matcher = p.matcher(email);
        return  matcher.matches();

    }
}
