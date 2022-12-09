import java.util.Date;
import java.lang.String;

public class UserRole
{
    private int _roleID;
    private String _roleName;
    private String _roleDescription;
    private int _authorizationID;
    private int _active;
    private String _dateAdded;
    private String _dateModified;

    public int getRoleID(){ return _roleID; }
    public void setRoleID(int roleID) { _roleID = roleID; }

    public String getRoleName(){ return _roleName; }
    public void setRoleName(String roleName) { _roleName = roleName; }

    public String getRoleDescription(){ return _roleDescription; }
    public void setRoleDescription(String roleDescription) { _roleDescription = roleDescription; }

    public int getAuthorizationID(){ return _authorizationID; }
    public void setAuthorizationID(int authorizationID) { _authorizationID = authorizationID; }

    public int getActive() {return _active;}
    public void setActive(int active) {_active = active;}

    public String getDateAdded() {return _dateAdded;}
    public void setDateAdded(String dateAdded) {_dateAdded = dateAdded;}

    public String getDateModified() {return _dateModified;}
    public void setDateModified(String dateModified) {_dateModified = dateModified;}
}