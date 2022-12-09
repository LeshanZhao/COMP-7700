import java.util.ArrayList;

public class Orders
{
    private int _orderID;
    private int _customerID;
    private int _shippingAddressID;
    private String _orderDate;
    private double _subtotal;
    private double _salesTax;
    private double _shippingAmount;
    private double _totalAmount;
    private int _shipperID;
    private String _dateAdded;
    private String _dateModified;
    private int _statusID;
    private ArrayList<OrderDetails> _orderDetailList = new ArrayList<OrderDetails>();

    //Properties for Order Fields
    public int getOrderID()
    {
        return _orderID;
    }
    public void setOrderID(int orderID)
    {
        _orderID = orderID;
    }
    public int getCustomerID()
    {
        return _customerID;
    }
    public void setCustomerID(int customerID)
    {
        _customerID = customerID;
    }
    public int getShippingAddressID()
    {
        return _shippingAddressID;
    }
    public void setShippingAddressID(int shippingAddressID)
    {
        _shippingAddressID = shippingAddressID;
    }
    public String getOrderDate()
    {
        return _orderDate;
    }
    public void setOrderDate(String orderDate)
    {
        _orderDate = orderDate;
    }

    public double getSubtotal()
    {
        return _subtotal;
    }
    public void setSubtotal(double subtotal)
    {
        _subtotal = subtotal;
    }

    public double getSalesTax()
    {
        return _salesTax;
    }
    public void setSalesTax(double salesTax)
    {
        _salesTax = salesTax;
    }

    public double getShippingAmount()
    {
        return _shippingAmount;
    }
    public void setShippingAmount(double shippingAmount)
    {
        _shippingAmount = shippingAmount;
    }

    public double getTotalAmount()
    {
        return _totalAmount;
    }
    public void setTotalAmount(double totalAmount)
    {
        _totalAmount = totalAmount;
    }

    public int getShipperID()
    {
        return _shipperID;
    }
    public void setShipperID(int shipperID)
    {
        _shipperID = shipperID;
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

    public int getStatusID()
    {
        return _statusID;
    }
    public void setStatusID(int statusID)
    {
        _statusID = statusID;
    }

    public ArrayList<OrderDetails> getOrderDetailList()
    {
        return _orderDetailList;
    }
    public void setOrderDetailList(ArrayList<OrderDetails> orderDetailList)
    {
        _orderDetailList = orderDetailList;
    }

    public void addOrderDetails(OrderDetails line)
    {
        _orderDetailList.add(line);
    }
}

