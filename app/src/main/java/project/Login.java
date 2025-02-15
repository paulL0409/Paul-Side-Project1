package project;
import java.nio.file.FileAlreadyExistsException;
import java.sql.*;


public class Login {
    public static boolean isUserNameTaken(String name){
        String query = "SELECT * FROM user WHERE user_name = '" + name + "'";
        try{
            Connection conn = MyJDBC.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                return true; // username is already used
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false; // such username doesn't exist
    }
    public static boolean register_user(String name, String password){
        if(isUserNameTaken(name)){
            System.out.println("This user already exist");
            return false;
        }
        String query = "INSERT INTO user (user_name, user_password) VALUES ('" + name + "', '" + password + "')";
        try{
            Connection conn = MyJDBC.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            int rowsInserted = pstmt.executeUpdate(); // execute insert
            return rowsInserted > 0; //return true if successful
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    public static boolean check_password(String name, String password){
        String query = "SELECT user_password FROM user WHERE user_name = '" + name + "'";
        try{
            Connection conn = MyJDBC.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                return true;
            }else{
                return false;
            }

        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    public static boolean login(String name, String password){
        if (!isUserNameTaken(name)){
            System.out.println("No such user name");
            return false;
        }
        else{
            if(check_password(name, password)) {
                System.out.println("login suceessful");
                return true;
            }else{
                System.out.println("wrong password");
                return false;
            }
        }
    }
}
