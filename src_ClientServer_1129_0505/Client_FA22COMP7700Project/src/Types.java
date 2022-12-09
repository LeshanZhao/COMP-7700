import java.lang.String;

public class Types
{
    private int _typeID;
    private String _typeName;
    private String _typeDescription;
    private String _typeValue;
    private int _active;
    private String _dateAdded;
    private String _dateModified;

    public int getTypeID()
    {
        return _typeID;
    }

    public void setTypeID(int typeID)
    {
        _typeID = typeID;
    }

    public String getTypeName()
    {
        return _typeName;
    }

    public void setTypeName(String typeName)
    {
        _typeName = typeName;
    }

    public String getTypeDescription()
    {
        return _typeDescription;
    }

    public void setTypeDescription(String typeDescription)
    {
        _typeDescription = typeDescription;
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

    public String getTypeValue()
    {
        return _typeValue;
    }

    public void setTableValue(String typeValue)
    {
        _typeValue = typeValue;
    }
}
