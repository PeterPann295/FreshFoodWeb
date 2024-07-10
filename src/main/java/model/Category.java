package model;


public class Category implements IModel{
    private int id;
    private String name;
    private ParentCategory parentCategory;
    private String beforeData;
    public Category() {};

    public Category(int id, String name, ParentCategory parentCategory) {
        this.id = id;
        this.name = name;
        this.parentCategory = parentCategory;
        this.beforeData = toString();
    }
    public Category( String name, ParentCategory parentCategory) {
        this.name = name;
        this.parentCategory = parentCategory;
        this.beforeData = toString();
    }

    public Category(int categoryId) {
        this.id = categoryId;
    }

    public Category(String categoryName) {
        this.name = categoryName;
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

    public ParentCategory getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(ParentCategory parentCategory) {
        this.parentCategory = parentCategory;
    }

    public String getBeforeData() {
        return beforeData;
    }

    public void setBeforeData(String beforeData) {
        this.beforeData = beforeData;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentCategory=" + parentCategory +
                '}';
    }

    @Override
    public String table() {
        return "Categories";
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
