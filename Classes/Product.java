package Classes;

public class Product {
    private String ProductID;
    private String ProductName;
    private Double BuyingPrice;
    private Double SellingPrice;
    private String ProductCat;
    private String Mark;
    private int Qte;

    public String getProductID() {
        return ProductID;
    }

    public int getQte() {
        return Qte;
    }

    public void setQte(int Qte) {
        this.Qte = Qte;
    }

    public void setProductID(String ProductID) {
        this.ProductID = ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public Double getBuyingPrice() {
        return BuyingPrice;
    }

    public void setBuyingPrice(Double BuyingPrice) {
        this.BuyingPrice = BuyingPrice;
    }

    public Double getSellingPrice() {
        return SellingPrice;
    }

    public void setSellingPrice(Double SellingPrice) {
        this.SellingPrice = SellingPrice;
    }

    public String getProductCat() {
        return ProductCat;
    }

    public void setProductCat(String ProductCat) {
        this.ProductCat = ProductCat;
    }

    public String getMark() {
        return Mark;
    }

    public void setMark(String mark) {
        this.Mark = mark;
    }

    public Product(String ProductID, String ProductName, Double BuyingPrice, Double SellingPrice, String ProductCat,
            String mark, int Qte) {
        // String uuid= UUID.randomUUID().toString();
        // this.ProductID=uuid.substring(0, uuid.indexOf('-'));
        this.ProductID=ProductID;
        this.ProductName = ProductName;
        this.BuyingPrice = BuyingPrice;
        this.SellingPrice = SellingPrice;
        this.ProductCat = ProductCat;
        this.Mark = mark;
        this.Qte = Qte;
    }

    public void AfficheDetailsProduct() {
        System.out.println("Product: " + this.ProductName);
        System.out.println("ID: " + this.ProductID);
        System.out.println("Mark: " + this.Mark);
        System.out.println("Categorie: " + this.ProductCat);
        System.out.println("Price d'achat: " + this.BuyingPrice + " dt.");
        System.out.println("Price de vente: " + this.SellingPrice + " dt.");
        System.out.println("Quantitée: " + this.Qte);
    }

    public void AfficheDetailsProductDeCategorie() {
        System.out.println("Product: " + this.ProductName);
        System.out.println("ID: " + this.ProductID);
        System.out.println("Mark: " + this.Mark);
        System.out.println("Price d'achat: " + this.BuyingPrice + " dt.");
        System.out.println("Price de vente: " + this.SellingPrice + " dt.");
        System.out.println("Quantitée: " + this.Qte);
    }

    public void AfficheProductTicket() {
        System.out.println("- Product: " + this.ProductName + " | Code: " + this.ProductID + " | Price: "
                + this.SellingPrice + " dt.");
    }

    public void Remise(Double r) {
        this.SellingPrice = (1-r) * this.SellingPrice;
    }

    public void AddProduct(int nbr) {
        this.Qte += nbr;
    }

    public void SellProduct(int nbr) {
        this.Qte -= nbr;
    }
}