package repositories;

import com.mysql.jdbc.Connection;
import components.ErrorPopupComponent;
import models.Student;
import models.User;
import models.UserRole;
import utils.DateHelper;
import utils.DbHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentRepository {

    public static int count() throws Exception{
        Connection conn = DbHelper.getConnetion();
        ResultSet res = conn.prepareStatement("SELECT  COUNT(*) from student").executeQuery();
        res.next();
        return res.getInt(1);

    }

    public static List<Student> list(int page, int perPage) throws Exception{
        ArrayList<Student> list = new ArrayList<>();
        Connection conn = DbHelper.getConnetion();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM student order by idstudent asc limit ? offset ?");
        stmt.setInt(1,page);
        stmt.setInt(2,page*perPage);
        ResultSet res = stmt.executeQuery();
        while(res.next()){
            list.add(parseFromRes(res));
        }
        return list;
    }

    public static Student find(int id)throws Exception{
        PreparedStatement stmt = DbHelper.getConnetion().prepareStatement("select  * from student where idstudent = ? limit 1");
        stmt.setInt(1,id);
        ResultSet res = stmt.executeQuery();
        // res.next();
        if(res.next() == false){
            return null;
        }
        return parseFromRes(res);
    }


    public static Student find(String email) throws  Exception{
        PreparedStatement stmt = DbHelper.getConnetion().prepareStatement("select  * from student where semail = ? limit 1");
        stmt.setString(1,email);
        ResultSet res = stmt.executeQuery();


        if(!res.next()){
            return null;
        }
        return parseFromRes(res);
    }


    public static Student create(Student model) throws Exception{
        String query = "Insert into student (semri,smbiemri,ditelindja,semail,stel,sfk,dhoma,vendi,foto) values(?,?,?,?,?,?,?,?,?)";
        PreparedStatement stmt = DbHelper.getConnetion().prepareStatement(query);
        stmt.setString(1,model.getEmri());
        stmt.setString(2,model.getMbiemri());
        stmt.setString(3,DateHelper.toSqlFormat(model.getDitelindja()));
        stmt.setString(4,model.getEmail());
        stmt.setInt(5,model.getTel());
        stmt.setString(6,model.getFk());
        stmt.setInt(7,model.getDhoma());
        stmt.setString(8,model.getVendi());
        stmt.setString(9,model.getFoto());

        if(stmt.executeUpdate() != 1)
            throw new Exception("ERR_NO_ROW_CHANGE");

        stmt = DbHelper.getConnetion().prepareStatement("SELECT  * from student order by regjistruar desc limit 1");
        ResultSet res = stmt.executeQuery();
        res.next();
        return parseFromRes(res);
    }


    public static Student update (Student model) throws Exception{
        String query = "Update student set semri = ? , smbiemri = ?, ditelindja = ?, semail = ?, stel = ?, sfk = ?, dhoma = ?,vendi = ?,foto = ? where idstudent = ?";
        PreparedStatement stmt = DbHelper.getConnetion().prepareStatement(query);
        stmt.setString(1, model.getEmri());
        stmt.setString(2,model.getMbiemri());
        stmt.setString(3,DateHelper.toSqlFormat(model.getDitelindja()));
        stmt.setString(4,model.getEmail());
        stmt.setInt(5,model.getTel());
        stmt.setString(6,model.getFk());
        stmt.setInt(7,model.getDhoma());
        stmt.setString(8,model.getVendi());
        stmt.setString(9,model.getFoto());
        stmt.setInt(10,model.getId());
        if (stmt.executeUpdate() != 1)
            throw new Exception("ERR_NO_ROW_CHANGE");

        return find(model.getId());
    }


    public static boolean remove(int id) throws Exception {
        String query = "DELETE FROM student WHERE idstudent = ?";
        PreparedStatement stmt = DbHelper.getConnetion().prepareStatement(query);
        stmt.setInt(1, id);
        return stmt.executeUpdate() == 1;
    }



    private static Student parseFromRes(ResultSet res)throws Exception {
        try{
            int id = res.getInt("idstudent");
            String emri = res.getString("semri");
            String smbiemri = res.getString("smbiemri");
            Date ditelinda = res.getDate("ditelindja");
            String email = res.getString("semail");
            int tel  = res.getInt("stel");
            String fk = res.getString("sfk");
            int dhoma = res.getInt("dhoma");
            String vendi = res.getString("vendi");
            Date regjistruar = res.getDate("regjistruar");
            String foto = res.getString("foto");
            return new Student(id,emri,smbiemri,ditelinda,email,tel,fk,dhoma,vendi,regjistruar,foto);
        }catch (Exception e){
            ErrorPopupComponent.show(e);
            return  null;
        }
    }
}
