import java.util.ArrayList;

public class ProductModel {

    private int _productID;
    private String _productName;
    private String _productDescription;
    private int _quantityOnHand;
    private double _price;
    private String _dateAdded;
    private int _active;
    private String _dateModified;
    private int _supplierID;
//    private ArrayList<ProductDetails> _productDetailsList;
    private Supplier _productSupplier;

    public int getProductID() { return _productID; }
    public void setProductID(int productID) {_productID = productID;}

    public String getProductName() {return _productName;}
    public void setProductName(String productName) {_productName = productName;}

    public String getProductDescription() {return _productDescription;}
    public void setProductDescription(String productDescription) {_productDescription = productDescription;}

    public int getQuantityOnHand() {return _quantityOnHand;}
    public void setQuantityOnHand(int quantityOnHand) {_quantityOnHand = quantityOnHand;}

    public double getPrice() {return _price;}
    public void setPrice(double price) {_price = price;}

    public String getDateAdded(){return _dateAdded;}
    public void setDateAdded(String dateAdded) {_dateAdded = dateAdded;}

    public int getActive() {return _active;}
    public void setActive(int active) {_active = active;}

    public String getDateModified(){return _dateModified;}
    public void setDateModified(String dateModified) {_dateModified = dateModified;}

    public int getSupplierID() { return _supplierID; }
    public void setSupplierID(int supplierID) {_supplierID = supplierID;}

//    public ArrayList<ProductDetails> getProductDetailsList(){return _productDetailsList;}
//    public void setProductDetailsList(ArrayList<ProductDetails> productDetailsList) {_productDetailsList = productDetailsList;}

    public Supplier getProductSupplier(){return _productSupplier;}
    public void setProductSupplier(Supplier productSupplier){_productSupplier = productSupplier;}

}



