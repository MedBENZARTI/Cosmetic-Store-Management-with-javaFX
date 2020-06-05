package Classes;

public class Category {
    private String CatName;
    private String CatID;

    public Category(String Name, String ID) {
        this.CatName = Name;
        this.CatID = ID;
    }

    public String getCatName() {
        return CatName;
    }

    public void setCatName(String catName) {
        CatName = catName;
    }

    public String getCatID() {
        return CatID;
    }

    public void setCatID(String catID) {
        CatID = catID;
    }
}