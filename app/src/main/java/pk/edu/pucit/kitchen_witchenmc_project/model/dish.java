package pk.edu.pucit.kitchen_witchenmc_project.model;

public class dish {
    String name;
    String img;
    String ingredients;
    String price;

    public dish(String name, String img, String ingredients, String price) {
        this.name = name;
        this.img = img;
        this.ingredients = ingredients;
        this.price = price;
    }
    public dish(String name, String img, String price){
        this.name = name;
        this.img = img;
        this.price = price;
    }

    public dish() {
        this.name=null;
        this.img=null;
        ingredients=null;
        this.price= null;
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

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
