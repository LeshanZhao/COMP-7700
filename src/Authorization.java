
import java.lang.String;


public class Authorization
{
    private int _authorizationID;
    private String _authorizationLevel;
    private int _product;
    private int _payment;
    private int _shipper;
    private int _user;
    private int _order;
    private int _supplier;
    private int _contactInfo;
    private int _active;
    private String _dateAdded;
    private String _dateModified;
    // Properties

    public int getAuthorizationID()
    {
        return _authorizationID;
    }
    public void setAuthorizationID(int authorizationID)
    {
        _authorizationID = authorizationID;
    }
    public String getAuthorizationLevel()
    {
        return _authorizationLevel;
    }
    public void setAuthorizationLevel(String authorizationLevel)
    {
        _authorizationLevel = authorizationLevel;
    }
    public int getProduct()
    {
        return _product;
    }
    public void setProduct(int product)
    {
        _product = product;
    }
    public int getPayment()
    {
        return _payment;
    }
    public void setPayment(int payment)
    {
        _payment = payment;
    }
    public int getShipper()
    {
        return _shipper;
    }
    public void setShipper(int shipper)
    {
        _shipper = shipper;
    }
    public int getUser()
    {
        return _user;
    }
    public void setUser(int user)
    {
        _user = user;
    }
    public int getOrders()
    {
        return _order;
    }
    public void setOrders(int order)
    {
        _order = order;
    }
    public int getSupplier()
    {
        return _supplier;
    }
    public void setSupplier(int supplier)
    {
        _supplier = supplier;
    }
    public int getContactInfo()
    {
        return _contactInfo;
    }
    public void setContactInfo(int contactInfo)
    {
        _contactInfo = contactInfo;
    }
    public int getActive()
    {
        return _active;
    }
    public void setActive(int active)
    {
        _active = active;
    }
    public String getDateAdded()
    {
        return _dateAdded;
    }
    public void setDateAdded(String dateAdded)
    {
        _dateAdded = dateAdded;
    }
    public String getDateModified()
    {
        return _dateModified;
    }
    public void setDateModified(String dateModified)
    {
        _dateModified = dateModified;
    }
}
