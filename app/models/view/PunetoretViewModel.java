package models.view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import models.Punetoret;
import models.PunetoretRole;
import utils.DateHelper;

import java.util.Date;

public class PunetoretViewModel {

    private IntegerProperty id;
    private StringProperty emri;
    private StringProperty mbiemri;
    private StringProperty ditelindja;
    private StringProperty roli;
    private StringProperty email;
    private IntegerProperty tel;


    private StringProperty vendi;
    private StringProperty regjistruar;
    private StringProperty foto;

    public PunetoretViewModel(){
        id = new SimpleIntegerProperty();
        emri = new SimpleStringProperty();
        mbiemri = new SimpleStringProperty();
        ditelindja = new SimpleStringProperty();
        roli = new SimpleStringProperty();
        email = new SimpleStringProperty();
        tel = new SimpleIntegerProperty();

        vendi = new SimpleStringProperty();
        regjistruar = new SimpleStringProperty();
        foto = new SimpleStringProperty();
    }

    public PunetoretViewModel(Punetoret model){
        this();
        this.setId(model.getId());
        this.setEmri(model.getEmri());
        this.setMbiemri(model.getMbiemri());
        this.setDitelindja(DateHelper.toSqlFormat(model.getDitelindja()));
        this.setRoli(model.getRoli());
        this.setEmail(model.getEmail());
        this.setTel(model.getTel());

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

    public StringProperty roliProperty(){
        return roli;
    }


    public PunetoretRole getRoli(){
        if(roli.getValue() == "Teknik") {
            return PunetoretRole.Teknik;
        }else if(roli.getValue() == "Recepsionist"){
            return PunetoretRole.Recepsionist;
        }else if(roli.getValue() =="Shef"){
            return PunetoretRole.Shef;
        }else if(roli.getValue() == "Referent"){
            return PunetoretRole.Referent;
        }else{
            return  null;
        }
    }

    public void setRoli(String value){
        roli.setValue(value);
    }

    public void setRoli(PunetoretRole value){
        if(value == PunetoretRole.Teknik){
            roli.setValue("Teknik");
        }else if(value == PunetoretRole.Recepsionist){
            roli.setValue("Recepsionist");
        }else if(value == PunetoretRole.Referent){
            roli.setValue("Referent");
        }else if(value == PunetoretRole.Shef){
            roli.setValue("Shef");
        }else{
            roli.setValue("nothing");
        }


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

    public Punetoret getModel(){
        return new Punetoret(getId(),getEmri(),getMbiemri(),getDitelindja(),getRoli(),getTel(),getEmail(),getVendi(),getRegjistruar(),getFoto());
    }


}
