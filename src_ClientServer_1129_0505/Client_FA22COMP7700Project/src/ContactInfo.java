import java.lang.String;
import java.util.Date;

public class ContactInfo
{
    private int _contactInfoID;
    private int _contactTypeID;
    private int _userID;
    private String _streetAddress1;
    private String _streetAddress2;
    private String _city;
    private String _state;
    private String _postalCode;
    private String _mainPhoneNumber;
    private String _alternatePhoneNumber;
    private String _emailAddress;
    private int _active;
    private String _dateAdded;
    private String _dateModified;

    public int getContactInfoID()
    {
        return _contactInfoID;
    }
    public void setContactInfoID(int contactInfoID )
    {
        _contactInfoID = contactInfoID;
    }
    public int getTypeID()
    {
        return _contactTypeID;
    }
    public void setContactTypeID(int contactTypeID )
    {
        _contactTypeID = contactTypeID;
    }
    public int getContactUserID()
    {
        return _userID;
    }
    public void setContactUserID(int userID )
    {
        _userID = userID;
    }
    public String getStreetAddress1()
    {
        return _streetAddress1;
    }
    public void setStreetAddress1(String streetAddress1 )
    {
        _streetAddress1 = streetAddress1;
    }
    public String getStreetAddress2()
    {
        return _streetAddress2;
    }
    public void setStreetAddress2(String streetAddress2 )
    {
        _streetAddress2 = streetAddress2;
    }
    public String getCity()
    {
        return _city;
    }
    public void setCity(String city )
    {
        _city = city;
    }
    public String getState()
    {
        return _state;
    }
    public void setState(String state )
    {
        _state = state;
    }
    public String getPostalCode()
    {
        return _postalCode;
    }
    public void setPostalCode(String postalCode )
    {
        _postalCode = postalCode;
    }
    public String getMainPhoneNumber()
    {
        return _mainPhoneNumber;
    }
    public void setMainPhoneNumber(String mainPhoneNumber )
    {
        _mainPhoneNumber = mainPhoneNumber;
    }
    public String getAlternatePhoneNumber()
    {
        return _alternatePhoneNumber;
    }
    public void setAlternatePhoneNumber(String alternatePhoneNumber )
    {
        _alternatePhoneNumber  = alternatePhoneNumber;
    }
    public String getEmailAddress()
    {
        return _emailAddress;
    }
    public void setEmailAddress(String emailAddress )
    {
        _emailAddress = emailAddress;
    }
    public int getActive()
    {
        return _active;
    }
    public void setActive(int active )
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

