package pk.edu.pucit.kitchen_witchenmc_project.model;

public class cartItem {
    String name;
    String img;
    long quantity;
    long price;
    int userId;
    int status;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public cartItem() {
        name=null;
        img=null;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public cartItem(String img, String name, long price, int qtty, int status, int usrId ) {
        this.name = name;
        this.img = img;
        this.quantity = qtty;
        this.price = price;
        this.status=status;
        this.userId=usrId;
    }

    public cartItem(String img, String name, long price, int qtty) {
        this.img=null;
        this.name=null;
        this.price=0;
        this.quantity=0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
