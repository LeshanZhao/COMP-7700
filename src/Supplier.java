import java.util.Date;
import java.lang.String;

public class Supplier
{
    private int _supplierID;
    private String _supplierName;
    private String _supplierDescription;
    private String _contactPersonName;
    private String _addressLine1;
    private String _addressLine2;
    private String _city;
    private String _state;
    private String _postalCode;
    private String _phoneNumber;
    private String _alternatePhoneNumber;
    private String _emailAddress;
    private int _active;
    private String _dateAdded;
    private String _dateModified;

    public int getSupplierID() { return _supplierID; }
    public void setSupplierID(int supplierID) { _supplierID = supplierID; }
    public String getSupplierName() { return _supplierName; }
    public void setSupplierName(String supplierName) { _supplierName = supplierName; }
    public String getSupplierDescription() { return _supplierDescription; }
    public void setSupplierDescription(String supplierDescription) { _supplierDescription = supplierDescription; }
    public String getContactPersonName() { return _contactPersonName; }
    public void setContactPersonName(String contactPersonName) { _contactPersonName = contactPersonName; }
    public String getAddressLine1() { return _addressLine1; }
    public void setAddressLine1(String addressLine1) { _addressLine1 = addressLine1; }
    public String getAddressLine2() { return _addressLine2; }
    public void setAddressLine2(String addressLine2) { _addressLine2 = addressLine2; }
    public String getCity() { return _city; }
    public void setCity(String city) { _city = city; }
    public String getState() { return _state; }
    public void setState(String state) { _state = state; }
    public String getPostalCode() { return _postalCode; }
    public void setPostalCode(String postalCode) { _postalCode = postalCode; }
    public String getPhoneNumber() { return _phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { _phoneNumber = phoneNumber; }
    public String getAlternatePhoneNumber() { return _alternatePhoneNumber; }
    public void setAlternatePhoneNumber(String alternatePhoneNumber) { _alternatePhoneNumber = alternatePhoneNumber; }
    public String getEmailAddress() { return _emailAddress; }
    public void setEmailAddress(String emailAddress) { _emailAddress = emailAddress; }
    public int getActive() { return _active; }
    public void setActive(int active) { _active = active; }
    public String getDateAdded() { return _dateAdded; }
    public void setDateAdded(String dateAdded) { _dateAdded = dateAdded; }
    public String getDateModified() { return _dateModified; }
    public void setDateModified(String dateModified) { _dateModified = dateModified; }

}

