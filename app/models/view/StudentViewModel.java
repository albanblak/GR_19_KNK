package models.view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import models.Student;
import utils.DateHelper;
import utils.DbHelper;

import java.util.Date;

public class StudentViewModel {

    private IntegerProperty id;
    private StringProperty emri;
    private StringProperty mbiemri;
    private StringProperty ditelindja;
    private StringProperty email;
    private IntegerProperty tel;
    private StringProperty fk;
    private IntegerProperty dhoma;
    private StringProperty vendi;
    private StringProperty regjistruar;
    private StringProperty foto;

    public StudentViewModel(){
        id = new SimpleIntegerProperty();
        emri = new SimpleStringProperty();
        mbiemri = new SimpleStringProperty();
        ditelindja = new SimpleStringProperty();
        email = new SimpleStringProperty();
        tel = new SimpleIntegerProperty();
        fk = new SimpleStringProperty();
        dhoma = new SimpleIntegerProperty();
        vendi = new SimpleStringProperty();
        regjistruar = new SimpleStringProperty();
        foto = new SimpleStringProperty();
    }

    public StudentViewModel(Student model){
        this();
        this.setId(model.getId());
        this.setEmri(model.getEmri());
        this.setMbiemri(model.getMbiemri());
        this.setDitelindja(DateHelper.toSqlFormat(model.getDitelindja()));
        this.setEmail(model.getEmail());
        this.setTel(model.getTel());
        this.setFk(model.getFk());
        this.setDhoma(model.getDhoma());
        this.setVendi(model.getVendi());
        this.setRegjistruar(DateHelper.toSqlFormat(model.getRegjistruar()));
        this.setFoto(model.getFoto());

    }

   public IntegerProperty idProperty(){
        return id;
   }

   public int getId(){
        return id.getValue();
   }

   public void setId(int value){
        id.setValue(value);
   }

   public StringProperty emriProperty(){
        return emri;
   }

   public String getEmri(){
        return emri.getValue();
   }

   public void setEmri(String value){
        emri.setValue(value);
    }


    public StringProperty mbiemriProperty(){
        return mbiemri;
    }

    public String getMbiemri(){
        return mbiemri.getValue();
    }


    public void setMbiemri(String value){
        mbiemri.setValue(value);
    }

    public StringProperty ditelindjaProperty(){
        return ditelindja;
    }

    public Date getDitelindja(){
        try {
           return DateHelper.fromSql(ditelindja.getValue()) ;
        }catch (Exception e){
            return null;
        }

    }

    public void setDitelindja(String value){
        ditelindja.setValue(value);
    }


    public StringProperty emailProperty(){
        return email;
    }

    public String getEmail(){
        return email.getValue();
    }

    public void setEmail(String value){
        email.setValue(value);
    }

    public IntegerProperty telProperty(){
        return tel;
    }

    public int getTel(){
        return tel.getValue();
    }

    public void setTel(int value){
        tel.setValue(value);
    }

    public StringProperty fkProperty(){
        return fk;
    }

    public String getFk(){
        return fk.getValue();
    }

    public void setFk(String value){
        fk.setValue(value);
    }

    public IntegerProperty dhomaProperty(){
        return dhoma;
    }

    public int getDhoma(){
        return dhoma.getValue();
    }

    public void setDhoma(int value){
        dhoma.setValue(value);
    }

    public StringProperty vendiProperty(){
        return vendi;
    }

    public String getVendi(){
        return vendi.getValue();
    }

    public void setVendi(String value){
        vendi.set(value);
    }

    public StringProperty regjistruarProperty(){
        return regjistruar;
    }

    public Date getRegjistruar(){
        try{
            return DateHelper.fromSql(regjistruar.getValue());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setRegjistruar(String value){
        regjistruar.setValue(value);
    }

    public StringProperty fotoProperty(){
        return foto;
    }

    public String getFoto(){
        return foto.getValue();
    }

    public void setFoto(String value){
        foto.setValue(value);
    }


    public Student getModel(){
        return new Student(getId(),getEmri(),getMbiemri(),getDitelindja(),getEmail(),getTel(),getFk(),getDhoma(),getVendi(),getRegjistruar(),getFoto());
    }












}
