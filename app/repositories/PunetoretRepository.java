package repositories;

import components.ErrorPopupComponent;
import models.Punetoret;
import models.PunetoretRole;
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
        String query = "Insert into punetoret (pemri,pmbiemri,proli,ptel,pemail,pvendi) values(?,?,?,?,?,?)";
        PreparedStatement stmt = DbHelper.getConnetion().prepareStatement(query);
        stmt.setString(1,model.getEmri());
        stmt.setString(2,model.getMbiemri());
        if(model.getRoli().equals(PunetoretRole.Teknik)){
            stmt.setString(3,"pteknik");
        }else if(model.getRoli().equals(PunetoretRole.Recepsionist)){
            stmt.setString(3,"recepsionist");
        }else if(model.getRoli().equals(PunetoretRole.Shef)){
            stmt.setString(3,"shef");
        }else if(model.getRoli().equals(PunetoretRole.Referent)){
            stmt.setString(3,"refernent");
        }else{
            stmt.setString(3,"refernent");
        }
        stmt.setInt(4,model.getTel());
        stmt.setString(5,model.getEmail());
        stmt.setString(6,model.getVendi());

        if(stmt.executeUpdate() != 1){
            throw new Exception("ERR_NO_ROW_CHANGE");
        }

        stmt = DbHelper.getConnetion().prepareStatement("select  * from punetoret order by pkrijuar desc limit 1");
        ResultSet res = stmt.executeQuery();
        res.next();
        return parseFromRes(res);


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




    private static Punetoret parseFromRes(ResultSet res){
        try {
           int id = res.getInt("idpunetoret");
           String emri = res.getString("pemri");
           String mbiemri = res.getString("pmbiemri");
            PunetoretRole roli = null;
            if(res.getString("proli").equals("pteknik")){
                roli = PunetoretRole.Teknik;
            }else if(res.getString("proli").equals("recepsionist") ){
                roli = PunetoretRole.Recepsionist;
            }else if(res.getString("proli").equals("shef") ){
                roli = PunetoretRole.Shef;
            }else if(res.getString("proli").equals("referent") ){
                roli = PunetoretRole.Referent;
            }
           int tel = res.getInt("ptel");
            String email = res.getString("pemail");
            String pvendi = res.getString("pvendi");
            Date krijuar = DateHelper.fromSql(res.getString("pkrijuar"));
            String foto = res.getString("pfoto");

            return new Punetoret(id,emri,mbiemri,roli,tel,email,pvendi,krijuar,foto);

        }catch (Exception e){
            ErrorPopupComponent.show(e);
            return null;
        }
    }


}
