package models;

import java.util.Date;

public class Pagesat {

    private int idPagesa;
    private int idStudent;
    private PagesaLloji lloji;
    private boolean pagesa;
    private Date muaji;

    public Pagesat(int idPagesa, int idStudent, PagesaLloji lloji, boolean pagesa, Date muaji){
       this.idPagesa = idPagesa;
       this.idStudent = idStudent;
       this.lloji = lloji;
       this.pagesa = pagesa;
       this.muaji = muaji;
    }


    private PagesaLloji getLloji(){
        return this.lloji;
    }

    private void setLloji(PagesaLloji lloji){
        this.lloji = lloji;
    }

    private boolean getPages(){
        return this.pagesa;
    }

    private void setPagesa(boolean pagesa){
        this.pagesa = pagesa;
    }

    private Date getMuaji(){
        return this.muaji;
    }

    private void setMuaji(Date muaji){
        this.muaji = muaji;
    }




}
