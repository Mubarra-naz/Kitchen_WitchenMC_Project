package pk.edu.pucit.kitchen_witchenmc_project.model;

public class User {
    private String name;
    private String password;
    public User(){

    }
    public User(String usrName, String usrPass){
        name=usrName;
        password=usrPass;
    }
    public String getName(){
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
