package project;

import java.sql.*;

public class MyJDBC {


    public static Connection getConnection(){
        try{
            return DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/login_schema",
                    "root",
                    "antony0409"
            );
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args){

    }
}
