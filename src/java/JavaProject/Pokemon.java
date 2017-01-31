package JavaProject;

public class Pokemon {

    private int id, default_exp;
    private String name, description, image_url;

    Pokemon() {
        this.default_exp = 10;
    }

    public int getId() {
        return id;
    }

    public int getDefault_exp() {
        return default_exp;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDefault_exp(int default_exp) {
        this.default_exp = default_exp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
