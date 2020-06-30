package models;

import java.util.Date;

public class Student {

    private int id;
    private String emri;
    private String mbiemri;
    private Date ditelindja;
    private String email;
    private int tel;
    private String fk;
    private int dhoma;
    private String vendi;
    private Date regjistruar;
    private String foto;

    public Student(){
        this(-1,"","",new Date(),"",-1,"",-1,"",new Date(),"");
    }

    public Student(int id, String emri,String mbiemri,Date ditelindja,String email,
                   int tel, String fk,int dhoma,String vendi,
                   Date regjistruar,String foto){
        this.id = id;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.ditelindja = ditelindja;
        this.email = email;
        this.tel = tel;
        this.fk = fk;
        this.dhoma = dhoma;
        this.vendi = vendi;
        this.regjistruar = regjistruar;
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
    public Date getDitelindja(){
        return this.ditelindja;
    }

    public void setDitelindja(Date ditelindja){
        this.ditelindja = ditelindja;
    }

    public String getEmail(){
        return  this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public int getTel(){
        return this.tel;
    }

    public void setTel(int tel){
        this.tel = tel;
    }

    public String getFk(){
        return this.fk;
    }

    public void setFk(String fk){
        this.fk = fk;
    }

    public int getDhoma(){
        return this.dhoma;
    }

    public void setDhoma(int dhoma){
        this.dhoma = dhoma;
    }


   public String getVendi(){
        return this.vendi;
   }

   public void setVendi(String vendi){
        this.vendi = vendi;
   }


   public Date getRegjistruar(){
        return this.regjistruar;
   }

   public String getFoto(){
        return this.foto;
   }

   public void setFoto(String foto){
        this.foto = foto;
   }




}
