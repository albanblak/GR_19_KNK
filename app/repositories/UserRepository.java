package repositories;

import com.mysql.jdbc.Connection;
import components.ErrorPopupComponent;
import models.User;
import models.UserRole;
import utils.DateHelper;
import utils.DbHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserRepository {

    public static int count() throws Exception{
        Connection conn = DbHelper.getConnetion();
        ResultSet res = conn.prepareStatement("Select  COUNT(*) FROM Konvikti").executeQuery();
        res.next();
        System.out.println("me sukses");
        return res.getInt(1);
    }

    public static List<User> list(int page, int perPage) throws Exception{
        ArrayList<User> list = new ArrayList<>();
        Connection conn = DbHelper.getConnetion();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users order by idusers asc limit ? offset ?");
        stmt.setInt(1,page);
        stmt.setInt(2,page*perPage);
        ResultSet res = stmt.executeQuery();
        while(res.next()){
            list.add(parseFromRes(res));
        }
        return list;
    }

    public static User find(int id)throws Exception{
        PreparedStatement stmt = DbHelper.getConnetion().prepareStatement("select  * from users where idusers = ? limit 1");
        stmt.setInt(1,id);
        ResultSet res = stmt.executeQuery();
       // res.next();
        if(res.next() == false){
            return null;
        }
        return parseFromRes(res);
    }


    public static User find(String email) throws  Exception{
        PreparedStatement stmt = DbHelper.getConnetion().prepareStatement("select  * from users where email = ? limit 1");
        stmt.setString(1,email);
        ResultSet res = stmt.executeQuery();
        res.next();

        if(!res.next()){
            return null;
        }


        return parseFromRes(res);
    }

    public static User create(User model) throws Exception{
        String query = "Insert into users (uname,email,password,salt,role,active) values(?,?,?,?,?,?)";
        PreparedStatement stmt = DbHelper.getConnetion().prepareStatement(query);
        stmt.setString(1,model.getName());
        stmt.setString(2,model.getEmail());
        stmt.setString(3,model.getPassword());
        stmt.setString(4,model.getSalt());
        stmt.setString(5,model.getRole() == UserRole.Admin ? "A" : "E");
        stmt.setInt(6,model.getActive() ? 1: 0);

        if(stmt.executeUpdate() != 1)
            throw new Exception("ERR_NO_ROW_CHANGE");

        stmt = DbHelper.getConnetion().prepareStatement("SELECT  * from users order by createdAt desc limit 1");
        ResultSet res = stmt.executeQuery();
        res.next();
        return parseFromRes(res);
    }


    public static User update (User model) throws Exception{
        String query = "Update users set name = ? , emial = ?, password = ?, salt = ?, role = ?, active = ?,updatedAt = CURRENT_TIMESTAMP where id = ?";
        PreparedStatement stmt = DbHelper.getConnetion().prepareStatement(query);
        stmt.setString(1, model.getName());
        stmt.setString(2, model.getEmail());
        stmt.setString(3, model.getPassword());
        stmt.setString(4, model.getSalt());
        stmt.setString(5, model.getRole() == UserRole.Admin ? "A" : "E");
        stmt.setInt(7, model.getActive() ? 1 : 0);
        stmt.setInt(7, model.getId());

        if (stmt.executeUpdate() != 1)
            throw new Exception("ERR_NO_ROW_CHANGE");

        return find(model.getId());
    }


    public static boolean remove(int id) throws Exception {
        String query = "DELETE FROM users WHERE idusers = ?";
        PreparedStatement stmt = DbHelper.getConnetion().prepareStatement(query);
        stmt.setInt(1, id);
        return stmt.executeUpdate() == 1;
    }



    private static User parseFromRes(ResultSet res) {
        try{

            int id = res.getInt("idusers");
            String name = res.getString("uname");
            String email = res.getString("email");
            String password = res.getString("password");
            String salt = res.getString("salt");
            UserRole role = res.getString("role").equals("A") ? UserRole.Admin : UserRole.Employee;
            boolean active = res.getInt("active") == 1;
            Date createdAt = DateHelper.fromSql(res.getString("createdAt"));
            Date updatedAt = DateHelper.fromSql(res.getString("updatedAt"));
            return new User(id,name,email,password,salt,role,active,createdAt,updatedAt);
        }catch (Exception e){
            ErrorPopupComponent.show(e);
            return  null;
        }
    }

}
