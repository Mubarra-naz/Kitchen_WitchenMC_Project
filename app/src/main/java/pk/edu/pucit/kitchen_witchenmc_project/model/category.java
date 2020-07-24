package pk.edu.pucit.kitchen_witchenmc_project.model;

public class category {
    private String name;
    private String img;

    public category() {
<<<<<<< HEAD
=======
        name=null;
        img=null;
>>>>>>> master
    }

    public category(String name, String img) {
        this.name = name;
        this.img = img;
    }

    public category(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
