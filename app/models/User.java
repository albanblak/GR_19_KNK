package models;

import java.util.Date;

public class User {

    private int id;
    private String uname;
    private String email;
    private String password;
    private String salt;
    private UserRole role;
    private boolean active;
    private Date createdAt;
    private Date updatedAt;


    public User(){
        this(-1,"","","","",UserRole.Employee,true,new Date(),new Date());
    }

    public User(int id, String name,String email, String password, String salt, UserRole role,
                boolean active, Date createdAt, Date updatedAt){
        this.id = id;
        this.uname = name;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.role = role;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public int getId(){
        return id;
    }

    public String getName(){
        return uname;
    }

    public void setName(String name){
        this.uname = name;
    }


    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getSalt(){
        return salt;
    }

    public void setSalt(String salt){
        this.salt = salt;
    }

    public String getPassword(){
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole(){
        return role;
    }

    public void setRole(UserRole role){
        this.role = role;
    }

    public boolean getActive(){
        return  active;
    }

    public void setActive(boolean active){
        this.active  = active;
    }

    public Date getCreatedAt(){
        return createdAt;
    }

    public Date getUpdatedAt(){
   return updatedAt;
    }





}
