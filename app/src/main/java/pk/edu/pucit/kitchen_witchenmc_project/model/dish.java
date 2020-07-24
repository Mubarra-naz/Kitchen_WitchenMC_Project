package pk.edu.pucit.kitchen_witchenmc_project.model;

public class dish {
    String name;
    String img;
    String ingredients;
    long price;
    String categoryName;

    public dish(String name, String img, String ingredients, long price, String cat) {
        this.name = name;
        this.img = img;
        this.ingredients = ingredients;
        this.price = price;
        this.categoryName=cat;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public dish(String name, String img, long price, String cat){
        this.name = name;
        this.img = img;
        this.price = price;
        this.categoryName=cat;
    }

    public dish() {
        this.name=null;
        this.img=null;
        ingredients=null;
        this.price= 0;
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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
