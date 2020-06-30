package repositories;

import components.ErrorPopupComponent;
import models.Punetoret;
import models.PunetoretRole;
import models.Student;
import utils.DateHelper;
import utils.DbHelper;

import java.net.PortUnreachableException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PunetoretRepository {

    public static int count() throws Exception{
        Connection conn = DbHelper.getConnetion();
        ResultSet res = conn.prepareStatement("select count(*) from punetoret").executeQuery();
        res.next();
        return res.getInt(1);
    }

    public static Punetoret find(String email) throws Exception{
        PreparedStatement stmt = DbHelper.getConnetion().prepareStatement("select * from punetoret where pemail = ?");
        stmt.setString(1,email);
        ResultSet res = stmt.executeQuery();
        res.next();
        if(!res.next()){
            return null;
        }

        return parseFromRes(res);
    }

    public static Punetoret create(Punetoret model) throws Exception{
        String query = "Insert into punetoret (pemri,pmbiemri,ditelinda,proli,ptel,pemail,pvendi) values(?,?,?,?,?,?,?)";
        PreparedStatement stmt = DbHelper.getConnetion().prepareStatement(query);
        stmt.setString(1,model.getEmri());
        stmt.setString(2,model.getMbiemri());
        stmt.setString(3,DateHelper.toSqlFormat(model.getDitelindja()));
        stmt.setString(4,retRoli(model.getRoli()));




        stmt.setInt(5,model.getTel());
        stmt.setString(6,model.getEmail());
        stmt.setString(7,model.getVendi());

        if(stmt.executeUpdate() != 1){
            throw new Exception("ERR_NO_ROW_CHANGE");
        }

        stmt = DbHelper.getConnetion().prepareStatement("select  * from punetoret order by pkrijuar desc limit 1");
        ResultSet res = stmt.executeQuery();
        res.next();
        return parseFromRes(res);


    }



    public static Punetoret update (Punetoret model) throws Exception{
        try {

            String query = "Update punetoret set pemri = ? , pmbiemri = ?, ditelinda = ?, proli = ?,pemail = ?, ptel = ?, pvendi = ?,pfoto = ? where idpunetoret = ?";
            PreparedStatement stmt = DbHelper.getConnetion().prepareStatement(query);
            stmt.setString(1, model.getEmri());
            stmt.setString(2,model.getMbiemri());
            stmt.setString(3,DateHelper.toSqlFormat(model.getDitelindja()));
            stmt.setString(4,retRoli(model.getRoli()));
            stmt.setString(5,model.getEmail());
            stmt.setInt(6,model.getTel());
            stmt.setString(7,model.getVendi());
            stmt.setString(8,model.getFoto());
            stmt.setInt(9,model.getId());
        if (stmt.executeUpdate() != 1)
               throw new Exception("ERR_NO_ROW_CHANGE");

            return find(model.getEmail());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }





    public static List<Punetoret> list(int page, int perPage) throws Exception{
        ArrayList<Punetoret> list = new ArrayList<>();
        Connection conn = DbHelper.getConnetion();
        PreparedStatement stmt = conn.prepareStatement("select * from punetoret order by idpunetoret asc limit ? offset ?");
        stmt.setInt(1,page);
        stmt.setInt(2,page*perPage);
        ResultSet res = stmt.executeQuery();
        while (res.next()){
            list.add(parseFromRes(res));
        }
        return list;

    }

    public static boolean remove(int id) throws Exception {
        String query = "DELETE FROM punetoret WHERE idpunetoret = ?";
        PreparedStatement stmt = DbHelper.getConnetion().prepareStatement(query);
        stmt.setInt(1, id);
        return stmt.executeUpdate() == 1;
    }




    private static Punetoret parseFromRes(ResultSet res){
        try {
           int id = res.getInt("idpunetoret");
           String emri = res.getString("pemri");
           String mbiemri = res.getString("pmbiemri");
           Date ditelindja = res.getDate("ditelinda");
           PunetoretRole roli;
          if(res.getString("proli").equals("Teknik")){
                roli = PunetoretRole.Teknik;
            }else if(res.getString("proli").equals("Recepsionist") ){
                roli = PunetoretRole.Recepsionist;
            }else if(res.getString("proli").equals("Shef") ){
                roli = PunetoretRole.Shef;
            }else if(res.getString("proli").equals("Referent") ){
                roli = PunetoretRole.Referent;
            }else {
              roli = null;
          }
           int tel = res.getInt("ptel");
            String email = res.getString("pemail");
            String pvendi = res.getString("pvendi");
            Date krijuar = DateHelper.fromSql(res.getString("pkrijuar"));
            String foto = res.getString("pfoto");

            return new Punetoret(id,emri,mbiemri,ditelindja,roli,tel,email,pvendi,krijuar,foto);

        }catch (Exception e){
            ErrorPopupComponent.show(e);
            return null;
        }
    }
    private static String retRoli(PunetoretRole role){
       if(role == PunetoretRole.Teknik){
           return "Teknik";
       } else if (role ==PunetoretRole.Referent) {
           return "Referent";
       }else if(role == PunetoretRole.Shef){
           return "Shef";
       }else if(role == PunetoretRole.Recepsionist){
           return "Recepsionist";
       }else{
           return "";
       }

    }

}
