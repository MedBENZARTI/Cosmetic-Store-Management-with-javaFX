package Classes;

import java.sql.Date;

public class Sale {
    private String ClientID;
    private String SaleID;
    private String ProductID;
    private String EmployeeID;
    Date SaleDate;
    private int Qte;
    private Double TotalePrice;

    public String getClientID() {
        return ClientID;
    }

    public Double getTotalePrice() {
        return TotalePrice;
    }

    public void setTotalePrice(Double totalePrice) {
        this.TotalePrice = totalePrice;
    }

    public void setClientID(String clientID) {
        ClientID = clientID;
    }

    public String getSaleID() {
        return SaleID;
    }

    public void setSaleID(String saleID) {
        SaleID = saleID;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public Date getSaleDate() {
        return SaleDate;
    }

    public void setSaleDate(Date saleDate) {
        SaleDate = saleDate;
    }

    public int getQte() {
        return Qte;
    }

    public void setQte(int qte) {
        Qte = qte;
    }

    public Sale(String clientID, String saleID, String productID, Date saleDate, int qte,Double totalePrice,String employeeID) {
        ClientID = clientID;
        SaleID = saleID;
        ProductID = productID;
        SaleDate = saleDate;
        Qte = qte;
        TotalePrice = totalePrice;
        EmployeeID = employeeID;
    }

    public String getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(String employeeID) {
        EmployeeID = employeeID;
    }
}