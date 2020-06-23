package utils;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbHelper {

    private static Connection conn = null;
    public static Connection getConnetion() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = (Connection) DriverManager.getConnection(Appconfig.get().getConnectionString());
        }
        return conn;
    }


    public static void closeConnection() throws Exception{
        if(conn != null){
            conn.close();
        }
    }


    public static void test() throws Exception{
        try{
            Connection conn = getConnetion();
            Statement st  =  conn.createStatement();
            ResultSet resultSet =  st.executeQuery("select * from Konvikti");
            while(resultSet.next()){
                System.out.println(resultSet.getString("kShefi"));
            }
        }catch (Exception e){
           e.printStackTrace();
           e.getMessage();
        }
    }


}
