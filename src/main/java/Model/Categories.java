package Model;

public class Categories {
    private int CategoriesID;
    private String Name;
    private String Description;

    public int getCategoriesID() {
        return CategoriesID;
    }

    public void setCategoriesID(int categoriesID) {
        CategoriesID = categoriesID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
