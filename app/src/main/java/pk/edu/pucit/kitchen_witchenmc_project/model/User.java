package pk.edu.pucit.kitchen_witchenmc_project.model;

public class User {
    private String name;
    private String mail;
    private String password;
    public User(){

    }
    public User(String usrName, String usrMail, String usrPass){
        name=usrName;
        mail=usrMail;
        password=usrPass;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public User(String mail, String password) {
        this.mail = mail;
        this.password = password;
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
