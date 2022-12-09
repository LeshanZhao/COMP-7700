import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DataAdapter
{


    public enum EditMode { VIEW, ADD, EDIT, DELETE}
    private ArrayList<Product> _productList = new ArrayList<Product>();
    private ArrayList<ProductDetails> _productDetailsList = new ArrayList<ProductDetails>();
    private ArrayList<Supplier> _supplierList = new ArrayList<Supplier>();
    private ArrayList<Users> _usersList = new ArrayList<Users>();
    private ArrayList<Orders> _ordersList = new ArrayList<Orders>();
    private ArrayList<OrderDetails> _orderDetailList = new ArrayList<OrderDetails>();
    private ArrayList<ContactInfo> _contactInfoList = new ArrayList<ContactInfo>();
    private ArrayList<Types> _typeList = new ArrayList<Types>();
    private Connection _connection;
    private Statement _statement;
    public DataAdapter(Connection connection)
    {
        try
        {
            this._connection = connection;
            this._statement = _connection.createStatement();
        }
        catch (SQLException sqlException)
        {
            System.out.println("Error occurred on Data Adapter Constructor");
            System.out.println("SQL Exception Message is " + sqlException.getMessage());
            System.out.println("SQL Exception Stack Trace is " + sqlException.getSQLState());
        }
    }
    public Product GetProductByID(int productID) throws SQLException
    {
        try
        {

            String query = "SELECT * FROM Product where ProductID = ?";

            PreparedStatement statement = _connection.prepareStatement(query);
            statement.setInt(1,productID);
            ResultSet resultset = statement.executeQuery();
            Product product = new Product();
            if (resultset.next())
            {
                product = FillProductObject(resultset);
            }
            resultset.close();
            statement.close();
            return product;
        } catch (SQLException sqlException)
        {
            System.out.println("Error retrieving product with product ID " + productID);
            sqlException.printStackTrace();;
            return null;
        }
    }
    public ArrayList<Product> GetAllProducts() throws SQLException
    {

        try
        {
            _productList = new ArrayList<Product>();
            String query = "SELECT * FROM Product";

            Statement statement = _connection.createStatement();
            ResultSet resultset = statement.executeQuery(query);
            while (resultset.next())
            {
                _productList.add(FillProductObject(resultset));
            }
            resultset.close();
            statement.close();
            return _productList;
        } catch (SQLException sqlException)
        {
            System.out.println("Error Retrieving Products...");
            sqlException.printStackTrace();;
            return null;
        }
    }
    public ArrayList<Product> GetAllProductsByProductDetails(int ProductDetailsID) throws SQLException
    {
        try
        {
            _productList = new ArrayList<Product>();
            String query = "SELECT ProductID FROM ProductDetails where ProductDetailsID = " + ProductDetailsID;

            Statement statement = _connection.createStatement();  //finish the SQL and get data into objects
            ResultSet resultset = statement.executeQuery(query);
            while (resultset.next())
            {
                int productID = resultset.getInt("ProductID");
                String productQuery = "SELECT * FROM Product where ProductID = " + productID;
                Statement productStatement = _connection.createStatement();
                ResultSet productResultSet = productStatement.executeQuery(productQuery);
                if (resultset.next())
                {

                    Product product = FillProductObject(resultset);
                    _productList.add(product);
                }
                productResultSet.close();
                productStatement.close();
            }
            resultset.close();
            statement.close();
            return _productList;
        } catch (SQLException sqlException)
        {
            System.out.println("Error Retrieving Products...");
            sqlException.printStackTrace();;
            return null;
        }
    }
    private Product FillProductObject(ResultSet resultset) throws SQLException
    {
        Product product = new Product();
        product.setProductID(resultset.getInt(1));
        product.setProductName(resultset.getString(2));
        product.setProductDescription(resultset.getString(3));
        product.setQuantityOnHand(resultset.getInt(4));
        product.setDateAdded(resultset.getString(5));
        product.setActive(resultset.getInt(6));
        product.setDateModified(resultset.getString(7));
        product.setSupplierID(resultset.getInt(8));
        product.setPrice(resultset.getDouble(9));
        product.setProductImage(ImportImageFromPath(resultset.getString(10)));
        product.setProductDetailsList(GetAllProductDetailsForProduct(product.getProductID()));
        product.setProductSupplier(GetSupplierForProduct(product.getSupplierID()));

        return product;
    }

    public ArrayList<Product> GetAllProductByCategoryAndSearchString(String category, String searchString ) throws SQLException
    {
        try
        {
            _productList = new ArrayList<Product>();
            String prodDetQuery = "SELECT * FROM ProductDetails where OptionValue = ?";
            //finish the SQL and get data into objects
            PreparedStatement statement = _connection.prepareStatement(prodDetQuery);
            statement.setString(1, category);
            ResultSet prodDetQueryRS = statement.executeQuery();
            while(prodDetQueryRS.next())
            {
                int productID = prodDetQueryRS.getInt("ProductID");
                String productQuery = "SELECT * FROM Product where ProductID = ?";
                PreparedStatement productStatement = _connection.prepareStatement(productQuery);
                productStatement.setInt(1, productID);
                ResultSet productResultSet = productStatement.executeQuery();
                while(productResultSet.next())
                {
                    if(productResultSet.getString("ProductName").toLowerCase().contains(searchString.toLowerCase()) ||
                            productResultSet.getString("ProductDescription").toLowerCase().contains(searchString.toLowerCase()))
                    {
                        Product product = FillProductObject(productResultSet);
                        _productList.add(product);
                    }
                }
                productResultSet.close();
                productStatement.close();
            }
            prodDetQueryRS.close();
            statement.close();
            return _productList;
        }
        catch (SQLException sqlException)
        {
            System.out.println("Error Retrieving Products with searchString and Category...");
            System.out.println("sqlException Message is " + sqlException.getMessage());
            System.out.println("sqlException Stack trace is " + sqlException.getStackTrace());
            return null;
        }
    }
    private BufferedImage ImportImageFromPath(String productImagePath)
    {
        BufferedImage image = null;
        if(productImagePath != null)
        {
            try
            {
                File imageFile = new File(productImagePath);
                image = ImageIO.read(imageFile);
            } catch (IOException ioException)
            {
                System.out.println("Error reading image file and converting to BufferedImage");
                System.out.println("IO Exception Message is " + ioException.getMessage());
                ioException.printStackTrace();
            }
        }
        return image;
    }

    public ArrayList<ProductDetails> GetAllProductDetailsForProduct(int productID) throws SQLException
    {
        try
        {
            _productDetailsList = new ArrayList<ProductDetails>();
            String query = "SELECT * FROM ProductDetails where ProductID = ?";

            PreparedStatement statement = _connection.prepareStatement(query);  //finish the SQL and get data into objects
            statement.setInt(1, productID);
            ResultSet resultset = statement.executeQuery();
            while (resultset.next())
            {
                ProductDetails productDetails = new ProductDetails();
                productDetails.setProductDetailsID(resultset.getInt(1));
                productDetails.setProductID(resultset.getInt(2));
                productDetails.setDetailsName(resultset.getString(3));
                productDetails.setDetailsDescription(resultset.getString(4));
                productDetails.setDetailsValue(resultset.getString(5));
                if(productDetails.getDetailsName().equals("Image"))
                {
                    productDetails.setDetailImage(ImportImageFromPath(productDetails.getDetailsValue()));
                }
                else
                {
                    productDetails.setDetailImage(null);
                }
                productDetails.setDateAdded(resultset.getString(6));
                productDetails.setDateModified(resultset.getString(7));
                productDetails.setActive(resultset.getInt(8));
                _productDetailsList.add(productDetails);
            }
            resultset.close();
            statement.close();
            return _productDetailsList;
        }
        catch (SQLException sqlException)
        {
            System.out.println("Error Retrieving Product Details...");
            sqlException.printStackTrace();
            return null;
        }
    }
    public Supplier GetSupplierForProduct(int supplierID) throws SQLException
    {
        try
        {
            String query = "SELECT * FROM Supplier where supplierID = " + supplierID;

            Statement statement = _connection.createStatement();  //finish the SQL and get data into objects
            ResultSet resultset = statement.executeQuery(query);
            if (resultset.next())
            {
                Supplier supplier = new Supplier();
                supplier.setSupplierID(resultset.getInt(1));
                supplier.setSupplierName (resultset.getString(2));
                supplier.setSupplierDescription(resultset.getString(3));
                supplier.setContactPersonName(resultset.getString(4));
                supplier.setAddressLine1(resultset.getString(5));
                supplier.setAddressLine2(resultset.getString(6));
                supplier.setCity(resultset.getString(7));
                supplier.setState(resultset.getString(8));
                supplier.setPostalCode(resultset.getString(9));
                supplier.setPhoneNumber(resultset.getString(10));
                supplier.setAlternatePhoneNumber(resultset.getString(11));
                supplier.setEmailAddress(resultset.getString(12));
                supplier.setActive(resultset.getInt(13));
                supplier.setDateAdded(resultset.getString(14));
                supplier.setDateModified(resultset.getString(15));
                resultset.close();
                statement.close();
                return supplier;
            }
        }
        catch (SQLException sqlException)
        {
            System.out.println("Error Retrieving Product Details...");
            sqlException.printStackTrace();
        }
        return null;
    }
    public Users GetUserWithUserID(int userID) throws SQLException
    {
        try
        {
            String query = "SELECT * FROM Users where userID = " + userID;
            Statement statement = _connection.createStatement();
            ResultSet resultset = statement.executeQuery(query);
            if (resultset.next())
            {
                Users user = GetUser(resultset);
                resultset.close();
                statement.close();
                return user;
            }
        }
        catch (SQLException sqlException)
        {
            System.out.println("Error Retrieving User for UserID " + userID);
            sqlException.printStackTrace();
        }
        return null;
    }
    public Users GetUserWithUsernamePassword(String username, String password) throws SQLException
    {
        try
        {
            String query = "SELECT * FROM Users WHERE Username = ? AND Password = ?";
            PreparedStatement statement = _connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultset = statement.executeQuery();
            if (resultset.next())
            {
                Users user = GetUser(resultset);
                resultset.close();
                statement.close();
                if(user.getUsername().equals(username) && user.getPassword().equals(password))
                {
                    return user;
                }
                else
                {
                    System.out.println("Username and/or password is invalid.");
                    JOptionPane.showMessageDialog(null, "Username and/or password is invalid.");
                }
            }
        }
        catch (SQLException sqlException)
        {
            System.out.println("Error Retrieving User for username " + username);
            System.out.println("SQL error is " + sqlException.getErrorCode() +
                    "\r\n Sql error message is " + sqlException.getMessage() + "\r\n");
            sqlException.printStackTrace();
        }
        return null;
    }
    public UserRole GetUserRoleWithUserRoleID(int userRoleID) throws SQLException
    {
        try
        {
            String query = "SELECT * FROM UserRole where RoleID = " + userRoleID;

            Statement statement = _connection.createStatement();
            ResultSet resultset = statement.executeQuery(query);
            if (resultset.next())
            {
                UserRole userRole = new UserRole();
                userRole.setRoleID(resultset.getInt(1));
                userRole.setRoleName (resultset.getString(2));
                userRole.setRoleDescription(resultset.getString(3));
                userRole.setAuthorizationID(resultset.getInt(4));
                userRole.setActive(resultset.getInt(5));
                userRole.setDateAdded(resultset.getString(6));
                userRole.setDateModified(resultset.getString(7));
                resultset.close();
                statement.close();
                return userRole;
            }
        }
        catch (SQLException sqlException)
        {
            System.out.println("Error Retrieving User Role for UserRoleID " + userRoleID);
            sqlException.printStackTrace();
        }
        return null;
    }
    public Authorization GetAuthorizationWithAuthorizationID(int authorizationID) throws SQLException
    {
        try
        {
            String query = "SELECT * FROM Authorization where authorizationID = " + authorizationID;
            Statement statement = _connection.createStatement();
            ResultSet resultset = statement.executeQuery(query);

            if (resultset.next())
            {
                Authorization authorization = new Authorization();
                authorization.setAuthorizationID(resultset.getInt(1));
                authorization.setAuthorizationLevel (resultset.getString(2));
                authorization.setProduct(resultset.getInt(3));
                authorization.setPayment(resultset.getInt(4));
                authorization.setShipper(resultset.getInt(5));
                authorization.setUser(resultset.getInt(6));
                authorization.setOrders(resultset.getInt(7));
                authorization.setSupplier(resultset.getInt(8));
                authorization.setContactInfo(resultset.getInt(9));
                authorization.setActive(resultset.getInt(10));
                authorization.setDateAdded(resultset.getString(11));
                authorization.setDateModified(resultset.getString(12));
                resultset.close();
                statement.close();
            }
        }
        catch (SQLException sqlException) {
            System.out.println("Error Retrieving Authorization Info for AuthorizationID " + authorizationID);
            sqlException.printStackTrace();
        }
        return null;
    }
    public ArrayList<Users> GetAllUsers() throws SQLException
    {
        try
        {
            _usersList = new ArrayList<Users>();
            String query = "SELECT * FROM Users";
            Statement statement = _connection.createStatement();
            ResultSet resultset = statement.executeQuery(query);
            while (resultset.next())
            {
                Users user = GetUser(resultset);
                _usersList.add(user);
            }
            resultset.close();
            statement.close();
            return _usersList;
        }
        catch (SQLException sqlException)
        {
            System.out.println("Error Retrieving list of users");
            sqlException.printStackTrace();
            return null;
        }
    }
    private Users GetUser(ResultSet resultset) throws SQLException
    {
        Users user = new Users();
        user.setUserID(resultset.getInt(1));
        user.setUsername(resultset.getString(2));
        user.setUserRoleID(resultset.getInt(3));
        user.setFirstName(resultset.getString(4));
        user.setLastName(resultset.getString(5));
        user.setPassword(resultset.getString(6));
        user.setActive(resultset.getInt(7));
        user.setDateAdded(resultset.getString(8));
        user.setDateModified(resultset.getString(9));
        UserRole userRole = GetUserRoleWithUserRoleID(user.getUserRoleID());
        user.setUserRole(userRole);
        user.setAuthorization(GetAuthorizationWithAuthorizationID(userRole.getAuthorizationID()));
        return user;
    }
    public ArrayList<Orders> GetAllOrdersForUser(int customerID) throws SQLException
    {
        try
        {
            String query = "SELECT * FROM Orders where CustomerID = " + customerID;

            Statement statement = _connection.createStatement();
            ResultSet resultset = statement.executeQuery(query);

            while (resultset.next())
            {
                Orders order = GetOrders(resultset);
                order.setOrderDetailList(GetOrderDetailsForOrder(order.getOrderID()));
                _ordersList.add(order);
            }
            resultset.close();
            statement.close();
            return _ordersList;
        }
        catch (SQLException sqlException)
        {
            System.out.println("Error Retrieving list of Orders by a certain userID " + customerID);
            sqlException.printStackTrace();
            return null;
        }
    }
    private ArrayList<OrderDetails> GetOrderDetailsForOrder(int orderID)
    {
        try
        {
            _orderDetailList = new ArrayList<OrderDetails>();
            String query = "SELECT * FROM OrderDetails where OrderID = " + orderID;

            Statement statement = _connection.createStatement();
            ResultSet resultset = statement.executeQuery(query);

            while (resultset.next())
            {
                OrderDetails orderDetails = new OrderDetails();
                orderDetails.setOrderDetailsID(resultset.getInt(1));
                orderDetails.setOrderID(resultset.getInt(2));
                orderDetails.setProductID(resultset.getInt(3));
                orderDetails.setLineTotal(resultset.getFloat(4));
                orderDetails.setDateAdded(resultset.getString(5));
                orderDetails.setDateModified(resultset.getString(6));
                _orderDetailList.add(orderDetails);
            }
            resultset.close();
            statement.close();
            return _orderDetailList;
        }
        catch (SQLException sqlException)
        {
            System.out.println("Error retrieving list of order details for a specific orderID " + orderID);
            sqlException.printStackTrace();
            return null;
        }
    }
    private Orders GetOrders(ResultSet orderResultSet) throws SQLException
    {
        Orders order = new Orders();
        order.setOrderID(orderResultSet.getInt(1));
        order.setCustomerID(orderResultSet.getInt(2));
        order.setShippingAddressID(orderResultSet.getInt(3));
        order.setOrderDate(orderResultSet.getString(4));
        order.setSubtotal(orderResultSet.getFloat(5));
        order.setSalesTax(orderResultSet.getFloat(6));
        order.setShippingAmount(orderResultSet.getFloat(7));
        order.setTotalAmount(orderResultSet.getFloat(8));
        order.setShipperID(orderResultSet.getInt(9));
        order.setDateAdded(orderResultSet.getString(10));
        order.setDateModified(orderResultSet.getString(11));
        order.setStatusID(orderResultSet.getInt(12));
        return order;
    }

    public ArrayList<ContactInfo> GetContactInfoByUser(int userID) throws SQLException
    {
        try
        {
            _contactInfoList = new ArrayList<ContactInfo>();
            String query = "SELECT * FROM ContactInfo where userID = " + userID;
            Statement statement = _connection.createStatement();
            ResultSet resultset = statement.executeQuery(query);
            while (resultset.next())
            {
                ContactInfo contactInfo = GetContactInfo(resultset);
                _contactInfoList.add(contactInfo);
            }
            resultset.close();
            statement.close();
            return _contactInfoList;
        }
        catch (SQLException sqlException)
        {
            System.out.println("Error Retrieving Contact Info List for user " + userID);
            sqlException.printStackTrace();
            return null;
        }
    }

    public ContactInfo GetShippingContactInfoByUser(int userID) throws SQLException
    {
        ContactInfo contactInfo = null;
        try
        {
            String query = "SELECT * FROM ContactInfo where userID = ? and  ContactTypeID = 16";
            PreparedStatement statement = _connection.prepareStatement(query);
            statement.setInt(1,userID);
            ResultSet resultset = statement.executeQuery();
            if (resultset.next())
            {
                contactInfo = GetContactInfo(resultset);
            }
            resultset.close();
            statement.close();
        }
        catch (SQLException sqlException)
        {
            System.out.println("Error Retrieving Contact Info List for user " + userID);
            sqlException.printStackTrace();
            return null;
        }
        return contactInfo;
    }

    private ContactInfo GetContactInfo(ResultSet resultset) throws SQLException
    {
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setContactInfoID(resultset.getInt(1));
        contactInfo.setContactTypeID(resultset.getInt(2));
        contactInfo.setContactUserID(resultset.getInt(3));
        contactInfo.setStreetAddress1(resultset.getString(4));
        contactInfo.setStreetAddress2(resultset.getString(5));
        contactInfo.setCity(resultset.getString(6));
        contactInfo.setState(resultset.getString(7));
        contactInfo.setPostalCode(resultset.getString(8));
        contactInfo.setMainPhoneNumber(resultset.getString(9));
        contactInfo.setAlternatePhoneNumber(resultset.getString(10));
        contactInfo.setEmailAddress(resultset.getString(11));
        contactInfo.setActive(resultset.getInt(12));
        contactInfo.setDateAdded(resultset.getString(13));
        contactInfo.setDateModified(resultset.getString(14));
        return contactInfo;
    }

    public ArrayList<Types> GetTypesByTypeName(String typeName) throws SQLException
    {
        try
        {
            _typeList = new ArrayList<Types>();
            String query = "SELECT * FROM Types where TypeName = ?";
            PreparedStatement statement = _connection.prepareStatement(query);
            statement.setString(1, typeName);
            ResultSet resultset = statement.executeQuery();
            while (resultset.next())
            {
                Types type = GetType(resultset);
                _typeList.add(type);
            }
            resultset.close();
            statement.close();
            return _typeList;
        }
        catch (SQLException sqlException)
        {
            System.out.println("Error retrieving types list for " + typeName);
            sqlException.printStackTrace();
            return null;
        }
    }
    public Types GetTypesByTypeValue(String typeValue) throws SQLException
    {
        try
        {
            Types type = null;
            String query = "SELECT * FROM Types where TypeValue = ?";
            PreparedStatement statement = _connection.prepareStatement(query);
            statement.setString(1, typeValue);
            ResultSet resultset = statement.executeQuery();
            if (resultset.next())
            {
                type = GetType(resultset);
            }
            resultset.close();
            statement.close();
            return type;
        }
        catch (SQLException sqlException)
        {
            System.out.println("Error retrieving types list for " + typeValue);
            sqlException.printStackTrace();
            return null;
        }
    }
    private Types GetType(ResultSet resultset) throws SQLException
    {
        Types type = new Types();
        type.setTypeID(resultset.getInt(1));
        type.setTypeName(resultset.getString(2));
        type.setTypeDescription(resultset.getString(3));
        type.setTableValue(resultset.getString(4));
        type.setActive(resultset.getInt(5));
        type.setDateAdded(resultset.getString(6));
        type.setDateModified(resultset.getString(7));
        return type;
    }

    public int GetNextOrderID()
    {
        int orderID = 0;
        try
        {
            String query = "SELECT Max(OrderID) FROM Orders";
            PreparedStatement statement = _connection.prepareStatement(query);
            ResultSet resultset = statement.executeQuery();

            if (resultset.next())
            {
                orderID = resultset.getInt(1);
                orderID++;
            }
            else
            {
                orderID = 100001;
            }
            resultset.close();
            statement.close();

        }
        catch (SQLException sqlException)
        {
            System.out.println("Error Retrieving next OrderID");
            sqlException.printStackTrace();
        }
        return orderID;
    }
    public int GetNextOrdersDetailID()
    {
        int orderDetailID = 0;
        try
        {
            String query = "SELECT MAX(orderDetailsID) FROM OrderDetails";
            PreparedStatement statement = _connection.prepareStatement(query);
            ResultSet resultset = statement.executeQuery();

            if (resultset.next())
            {
                orderDetailID = resultset.getInt(1);
                orderDetailID++;
            }
            else
            {
                orderDetailID = 10001;
            }
            resultset.close();
            statement.close();

        }
        catch (SQLException sqlException)
        {
            System.out.println("Error Retrieving next OrderDetails ID");
            sqlException.printStackTrace();
        }
        return orderDetailID;
    }
    public ArrayList<ShipperMethod> GetShipperMethodListByShipperID(int shipperID)
    {
        ArrayList<ShipperMethod> smlist = new ArrayList<ShipperMethod>();
        try
        {
            String query = "SELECT * FROM ShipperMethod where ShipperID = ?";
            PreparedStatement statement = _connection.prepareStatement(query);
            statement.setInt(1, shipperID);
            ResultSet resultset = statement.executeQuery();
            while (resultset.next())
            {
                ShipperMethod smethod = GetShipperMethod(resultset);
                smlist.add(smethod);
            }
            resultset.close();
            statement.close();
            return smlist;
        }
        catch (SQLException sqlException)
        {
            System.out.println("Error retrieving shipper methods list for " + shipperID);
            sqlException.printStackTrace();
            return null;
        }
    }
    public ShipperMethod GetShipperMethodByShipperIDAndMethod(int shipperID, String method)
    {
        ShipperMethod smethod = null;
        try
        {
            String query = "SELECT * FROM ShipperMethod where ShipperID = ? and MethodName = ?";
            PreparedStatement statement = _connection.prepareStatement(query);
            statement.setInt(1, shipperID);
            statement.setString(2,method);
            ResultSet resultset = statement.executeQuery();
            while (resultset.next())
            {
                smethod = GetShipperMethod(resultset);
            }
            resultset.close();
            statement.close();
        }
        catch (SQLException sqlException)
        {
            System.out.println("Error retrieving shipper method for " + shipperID + " and " + method);
            sqlException.printStackTrace();
            return null;
        }
        return smethod;
    }
    public ShipperMethod GetDefaultShipperMethod()
    {
        ShipperMethod smethod = null;
        try
        {
            String query = "SELECT * FROM ShipperMethod where ShipperID = 100";
            PreparedStatement statement = _connection.prepareStatement(query);
            ResultSet resultset = statement.executeQuery();
            if (resultset.next())
            {
                smethod = GetShipperMethod(resultset);
            }
            resultset.close();
            statement.close();

        }
        catch (SQLException sqlException)
        {
            System.out.println("Error retrieving default shipper method");
            sqlException.printStackTrace();
            return null;
        }
        return smethod;
    }

    private ShipperMethod GetShipperMethod(ResultSet resultset) throws SQLException
    {
        ShipperMethod sm = new ShipperMethod();
        sm.setShipperID(resultset.getInt(1));
        sm.setMethodName(resultset.getString(2));
        sm.setMethodDescription(resultset.getString(3));
        sm.setMethodCost(resultset.getDouble(4));
        sm.setActive(resultset.getInt(5));
        sm.setDateAdded(resultset.getString(6));
        sm.setDateModified(resultset.getString(7));
        sm.setShipperID(resultset.getInt(8));
        return sm;
    }

    public boolean saveProduct(Product product)
    {
        try
        {
            PreparedStatement statement = _connection.prepareStatement("SELECT * FROM Product WHERE ProductID = ?");
            statement.setInt(1, product.getProductID());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
            { // this product exists, update its fields
                statement = _connection.prepareStatement("UPDATE Product SET ProductName = ?, Price = ?, QuantityOnHand = ? WHERE ProductID = ?");
                statement.setString(1, product.getProductName());
                statement.setDouble(2, product.getPrice());
                statement.setDouble(3, product.getQuantityOnHand());
                statement.setInt(4, product.getProductID());
            }
            else
            { // this product does not exist, use insert into
                statement = _connection.prepareStatement("INSERT INTO Product VALUES (?, ?, ?, ?)");
                statement.setInt(1, product.getProductID());
                statement.setString(2, product.getProductName());
                statement.setDouble(3, product.getPrice());
                statement.setInt(4, product.getQuantityOnHand());
            }
            statement.execute();
            resultSet.close();
            statement.close();
            return true;        // save successfully

        } catch (SQLException e) {
            System.out.println("Database access error - updating product!");
            e.printStackTrace();
            return false; // cannot save!
        }
    }

    public ArrayList<Shipper> GetAllShippers()
    {
        ArrayList<Shipper> shipperList = new ArrayList<Shipper>();
        try
        {
            String query = "SELECT * FROM Shipper order by shipperID";
            PreparedStatement statement = _connection.prepareStatement(query);
            ResultSet resultset = statement.executeQuery();
            while (resultset.next())
            {
                Shipper shipper = GetShipperInfo(resultset);

                shipperList.add(shipper);
            }
            resultset.close();
            statement.close();
            return shipperList;
        }
        catch (SQLException sqlException)
        {
            System.out.println("Error retrieving List of Shippers");
            sqlException.printStackTrace();
            return null;
        }
    }

    public Shipper GetDefaultShipper()
    {
        Shipper shipper = null;
        try
        {
            String query = "SELECT * FROM Shipper where ShipperID = 100";
            PreparedStatement statement = _connection.prepareStatement(query);
            ResultSet resultset = statement.executeQuery();
            if (resultset.next())
            {
                shipper = GetShipperInfo(resultset);
            }
            resultset.close();
            statement.close();
        }
        catch (SQLException sqlException)
        {
            System.out.println("Error retrieving List of Shippers");
            sqlException.printStackTrace();
            return null;
        }
        return shipper;
    }

    private Shipper GetShipperInfo(ResultSet resultset) throws SQLException
    {
        Shipper s = new Shipper();
        s.setShipperID(resultset.getInt(1));
        s.setShipperName(resultset.getString(2));
        s.setActive(resultset.getInt(10));
        return s;
    }

    public boolean SaveOrder(Orders order)
    {

        boolean status = false;
        try
        {
            PreparedStatement statement = _connection.prepareStatement("INSERT INTO Orders VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setInt(1, order.getOrderID());
            statement.setInt(2, order.getCustomerID());
            statement.setInt(3, order.getShippingAddressID());
            statement.setString(4,order.getOrderDate());
            statement.setDouble(5, order.getSubtotal());
            statement.setDouble(6, order.getSalesTax());
            statement.setDouble(7, order.getShippingAmount());
            statement.setDouble(8, order.getTotalAmount());
            statement.setInt(9,order.getShipperID());
            statement.setString(10, order.getDateAdded());
            statement.setString(11, order.getDateModified());
            statement.setInt(12, order.getStatusID());
            statement.executeUpdate();
            //orderRS.close();
            statement.close();
            for(OrderDetails od : order.getOrderDetailList())
            {
                PreparedStatement odStatement = _connection.prepareStatement("INSERT INTO OrderDetails VALUES (?, ?, ?, ?, ?, ?, ?)");
                odStatement.setInt(1, od.getOrderDetailsID());
                odStatement.setInt(2, order.getOrderID());
                odStatement.setInt(3, od.getProductID());
                odStatement.setInt(4, od.getQuantity());
                odStatement.setDouble(5, od.getLineTotal());
                odStatement.setString(6,od.getDateAdded());
                odStatement.setString(7,od.getDateModified());
                odStatement.executeUpdate();
                //odRS.close();
                odStatement.close();
            }
            status = true;
        } catch (SQLException e) {
            System.out.println("Database access error - inserting new order and order details!");
            e.printStackTrace();
        }
        return status;
    }

    public ArrayList<Product> GetAllProductByCategory(String category)
    {
        try
        {
            _productList = new ArrayList<Product>();
            String prodDetQuery = "SELECT * FROM ProductDetails where OptionValue = ?";
            //finish the SQL and get data into objects
            PreparedStatement statement = _connection.prepareStatement(prodDetQuery);
            statement.setString(1, category);
            ResultSet prodDetQueryRS = statement.executeQuery();
            while(prodDetQueryRS.next())
            {
                int productID = prodDetQueryRS.getInt("ProductID");
                String productQuery = "SELECT * FROM Product where ProductID = ?";
                PreparedStatement productStatement = _connection.prepareStatement(productQuery);
                productStatement.setInt(1, productID);
                ResultSet productResultSet = productStatement.executeQuery();
                while(productResultSet.next())
                {
                    Product product = FillProductObject(productResultSet);
                    _productList.add(product);
                }
                productResultSet.close();
                productStatement.close();
            }
            prodDetQueryRS.close();
            statement.close();
            return _productList;
        }
        catch (SQLException sqlException)
        {
            System.out.println("Error Retrieving Products with Category...");
            System.out.println("sqlException Message is " + sqlException.getMessage());
            System.out.println("sqlException Stack trace is " + sqlException.getStackTrace());
            return null;
        }
    }
}

