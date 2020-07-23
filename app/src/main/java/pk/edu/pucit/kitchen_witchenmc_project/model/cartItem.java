package pk.edu.pucit.kitchen_witchenmc_project.model;

public class cartItem {
    String name;
    String img;
    int quantity;
    int price;
    public cartItem(String img, String name, int price, int qtty) {
        this.name = name;
        this.img = img;
        this.quantity = qtty;
        this.price = price;
    }

    public cartItem() {
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
