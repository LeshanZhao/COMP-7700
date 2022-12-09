import java.lang.String;

public class OrderDetails
{
    private int _orderDetailsID;
    private int _orderID;
    private int _productID;
    private int _quantity;
    private double _lineTotal;
    private String  _dateAdded;
    private String  _dateModified;

    //Properties for OrderDetail Fields
    public int getOrderDetailsID()
    {
        return _orderDetailsID;
    }
    public void setOrderDetailsID(int orderDetailsID)
    {
        _orderDetailsID = orderDetailsID;
    }
    public int getOrderID()
    {
        return _orderID;
    }
    public void setOrderID(int orderID)
    {
        _orderID = orderID;
    }
    public int getProductID()
    {
        return _productID;
    }
    public void setProductID(int productID)
    {
        _productID = productID;
    }
    public int getQuantity()
    {
        return _quantity;
    }
    public void setQuantity(int quantity)
    {
        _quantity = quantity;
    }
    public double getLineTotal()
    {
        return _lineTotal;
    }
    public void setLineTotal(double lineTotal)
    {
        _lineTotal = lineTotal;
    }
    public String  getDateAdded()
    {
        return _dateAdded;
    }
    public void setDateAdded(String  dateAdded)
    {
        _dateAdded = dateAdded;
    }
    public String  getDateModified()
    {
        return _dateModified;
    }
    public void setDateModified(String  dateModified)
    {
        _dateModified = dateModified;
    }
}
