import java.lang.String;
import java.util.Date;

public class ShipperMethod
{
    private int _shipperMethodID;
    private int _shipperID;
    private String _methodName;
    private String _methodDescription;
    private double _methodCost;
    private int _active;
    private String _dateAdded;
    private String _dateModified;

    public int getShipperMethodID()
    {
        return _shipperMethodID;
    }

    public void setShipperMethodID(int shipperMethodID)
    {
        _shipperMethodID = shipperMethodID;
    }

    public int getShipperID()
    {
        return _shipperID;
    }

    public void setShipperID(int shipperID)
    {
        _shipperID = shipperID;
    }

    public String getMethodName()
    {
        return _methodName;
    }

    public void setMethodName(String methodName)
    {
        _methodName = methodName;
    }

    public String getMethodDescription()
    {
        return _methodDescription;
    }

    public void setMethodDescription(String methodDescription)
    {
        _methodDescription = methodDescription;
    }

    public double getMethodCost()
    {
        return _methodCost;
    }

    public void setMethodCost(double methodCost)
    {
        _methodCost = methodCost;
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
