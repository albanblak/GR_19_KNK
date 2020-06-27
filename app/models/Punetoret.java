package models;

import java.net.PortUnreachableException;
import java.util.Date;

public class Punetoret {

    private int id;
    private String emri;
    private String mbiemri;
    private PunetoretRole roli;
    private int tel;
    private String email;
    private String vendi;
    private Date krijuar;
    private String foto;


    public Punetoret(){
        this(-1,"","",PunetoretRole.Teknik,-1,"","",new Date(),"");
    }

    public Punetoret(int id, String emri,String mbiemri,PunetoretRole roli,
                     int tel, String email,String vendi,
                      Date krijuar,String foto){
        this.id = id;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.roli = roli;
        this.tel = tel;
        this.email = email;
        this.vendi = vendi;
        this.krijuar = krijuar;
        this.foto = foto;
    }

    public int getId(){
        return this.id;
    }

    public String getEmri(){
        return this.emri;
    }

    public void setEmri(String emri){
        this.emri = emri;
    }

    public String getMbiemri(){
        return this.mbiemri;
    }

    public void setMbiemri(String mbiemri){
        this.mbiemri = mbiemri;
    }

    public PunetoretRole getRoli(){
        return this.roli;
    }

    public void setRoli(PunetoretRole roli){
        this.roli = roli;
    }

    public int getTel(){
        return this.tel;
    }

    public void setTel(int tel){
        this.tel = tel;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getVendi(){
        return this.vendi;
    }

    public void setVendi(String vendi){
        this.vendi = vendi;
    }


    public Date getKrijuar(){
        return this.krijuar;
    }

    public String getFoto(){
        return this.foto;
    }

    public void setFoto(String foto){
        this.foto = foto;
    }







}
