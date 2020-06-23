package utils;

import java.util.Properties;

public class Appconfig {
    private Properties props;
    private Appconfig() {
        try{
            props = new Properties();
            props.load(getClass().getResourceAsStream("../resources/config.properties"));

        }catch (Exception e){

        }

    }

    private static Appconfig instance;

    public static Appconfig get(){
        if(instance == null)
            instance = new Appconfig();
        return instance;
    }


    public String getAppName(){
        return props.getProperty("appname","APP");
    }

    public String getVersion(){
        return props.getProperty("verision","APP");
    }

    public String getReleased(){
        return props.getProperty("released","APP");
    }

    public String getConnectionString(){
        return props.getProperty("connectionString");
    }


    public String getDriverType(){
        return props.getProperty("drivetType");
    }



}
