import java.lang.String;
import java.util.Date;

public class Shipper
{
    private int _shipperID;
    private String _shipperName;
    private String _shipperAddress1;
    private String _shipperAddress2;
    private String _city;
    private String _state;
    private String _postalCode;
    private String _mainPhoneNumber;
    private String _emailAddress;
    private int _active;
    private String _dateAdded;
    private String _dateModified;

    public int getShipperID(){ return _shipperID; }
    public void setShipperID(int shipperID) { _shipperID = shipperID; }

    public String getShipperName() { return _shipperName; }
    public void setShipperName(String shipperName) { _shipperName = shipperName; }

    public String getShipperAddress1() { return _shipperAddress1; }
    public void setShipperAddress1(String shipperAddress1) { _shipperAddress1 = shipperAddress1; }

    public String getShipperAddress2() { return _shipperAddress2; }
    public void setShipperAddress2(String shipperAddress2) { _shipperAddress2 = shipperAddress2; }

    public String getCity() { return _city; }
    public void setCity(String city) { _city = city; }

    public String getState() { return _state; }
    public void setState(String state) { _state = state; }

    public String getPostalCode() { return _postalCode; }
    public void setPostalCode(String postalCode) { _postalCode = postalCode; }

    public String getMainPhoneNumber() { return _mainPhoneNumber; }
    public void setMainPhoneNumber(String mainPhoneNumber) { _mainPhoneNumber = mainPhoneNumber; }

    public String getEmailAddress() { return _emailAddress; }
    public void setEmailAddress(String emailAddress) { _emailAddress = emailAddress; }

    public int getActive() { return _active; }
    public void setActive(int active) { _active = active; }

    public String getDateAdded() { return _dateAdded; }
    public void setDateAdded(String dateAdded) { _dateAdded = dateAdded; }

    public String getDateModified() { return _dateModified; }
    public void setDateModified(String dateModified) { _dateModified = dateModified; }
}

