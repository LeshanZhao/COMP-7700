import java.awt.image.BufferedImage;
import java.lang.String;


public class ProductDetails
{
    private int _productDetailsID;
    private int _productID;
    private int _productDetailsTypeID;
    private String _DetailsName;
    private String _DetailsDescription;
    private String _DetailsValue;
    private String _dateAdded;
    private String _dateModified;
    private int _active;
    private BufferedImage _optionImage;

    public int getProductDetailsID(){return _productDetailsID;}
    public void setProductDetailsID(int productDetailsID) {_productDetailsID = productDetailsID;}

    public int getProductID() {return _productID;}
    public void setProductID(int productID) { _productID = productID;}

    public int getProductDetailsTypeID() {return _productDetailsTypeID;}
    public void setProductDetailsTypeID(int productDetailsTypeID) { _productDetailsTypeID = productDetailsTypeID;}

    public String getDetailsName(){return _DetailsName;}
    public void setDetailsName(String DetailsName){ _DetailsName = DetailsName;}

    public String getDetailsDescription(){return _DetailsDescription;}
    public void setDetailsDescription(String DetailsDescription){ _DetailsDescription = DetailsDescription;}

    public String getDetailsValue(){return _DetailsValue;}
    public void setDetailsValue(String DetailsValue){ _DetailsValue = DetailsValue;}

    public String getDateAdded(){return _dateAdded;}
    public void setDateAdded(String dateAdded) {_dateAdded = dateAdded;}

    public String getDateModified(){return _dateModified;}
    public void setDateModified(String dateModified) {_dateModified = dateModified;}

    public int getActive() {return _active;}
    public void setActive(int active) {_active = active;}

    public BufferedImage getOptionImage()
    {
        return _optionImage;
    }
    public void setOptionImage(BufferedImage optionImage)
    {
        _optionImage = optionImage;
    }
}

