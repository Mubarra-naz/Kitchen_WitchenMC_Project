package pk.edu.pucit.kitchen_witchenmc_project.model;

public class cartItem {
    String name;
    String img;
    int quantity;
    String price;
    public cartItem(String img, String name, String price, int counter) {
        this.name = name;
        this.img = img;
        this.quantity = counter;
        this.price = price;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
