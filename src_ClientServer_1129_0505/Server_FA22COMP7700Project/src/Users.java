import java.util.Date;
import java.lang.String;

public class Users
{
    private int _userID;
    private String _username;
    private int _userRoleID;
    private String _firstName;
    private String _lastName;
    private String _password;
    private int _active;
    private String _dateAdded;
    private String _dateModified;
    private UserRole _userRole;
    private Authorization _authorization;

    public int getUserID()
    {
        return _userID;
    }

    public void setUserID(int userID)
    {
        _userID = userID;
    }


    public String getUsername()
    {
        return _username;
    }

    public void setUsername(String username)
    {
        _username = username;
    }


    public int getUserRoleID()
    {
        return _userRoleID;
    }

    public void setUserRoleID(int userRoleID)
    {
        _userRoleID = userRoleID;
    }


    public String getFirstName()
    {
        return _firstName;
    }

    public void setFirstName(String firstName)
    {
        _firstName = firstName;
    }


    public String getLastName()
    {
        return _lastName;
    }

    public void setLastName(String lastName)
    {
        _lastName = lastName;
    }


    public String getPassword()
    {
        return _password;
    }

    public void setPassword(String password)
    {
        _password = password;
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

    public UserRole getUserRole()
    {
        return _userRole;
    }

    public void setUserRole(UserRole userRole)
    {
        _userRole = _userRole;
    }

    public Authorization getAuthorization()
    {
        return _authorization;
    }

    public void setAuthorization(Authorization authorization)
    {
        _authorization = authorization;
    }

}
