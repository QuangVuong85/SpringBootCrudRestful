package sbcrudrestful.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class Utilslib {
    public static Connection getConnection(){
        Connection conn=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection(String.format("jdbc:mysql://%s:%s/%s?%s",
                    Constants.HOST, Constants.PORT, Constants.DATABASE_NAME, Constants.PARAMETER), Constants.USER,
                    Constants.PASSWORD);
        }catch(Exception e){System.out.println(e);}
        return conn;
    }
}
