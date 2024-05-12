package model;

public class ParentCategory implements IModel {
    private int id;
    private String name;
    private String imageURL;
    private String beforeData;

    public ParentCategory(int id, String name, String imageURL) {
        this.id = id;
        this.name = name;
        this.imageURL = imageURL;
        this.beforeData = toString();
    }
    public ParentCategory( String name, String imageURL) {
        this.name = name;
        this.imageURL = imageURL;
        this.beforeData = toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "ParentCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }

    @Override
    public String table() {
        return "parent_categories";
    }

    @Override
    public String beforeData() {
        return beforeData;
    }

    @Override
    public String afterData() {
        return toString();
    }
}

