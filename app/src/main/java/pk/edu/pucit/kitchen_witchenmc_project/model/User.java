package pk.edu.pucit.kitchen_witchenmc_project.model;

public class User {
    private String name;
    private String password;
    private String mail;
    public User(){

    }
    public User(String usrName, String usrMAil, String usrPass){
        name=usrName;
        mail=usrMAil;
        password=usrPass;
    }
    public String getName(){
        return name;
    }

    public User(String password, String mail) {
        this.password = password;
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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
