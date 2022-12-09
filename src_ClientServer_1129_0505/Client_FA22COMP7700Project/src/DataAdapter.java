import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
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
            String query = "SELECT * FROM Product where ProductID = " + productID;

            Statement statement = _connection.createStatement();
            ResultSet resultset = statement.executeQuery(query);
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
        product.setProductDetailsList(GetAllProductDetailsForProduct(product.getProductID()));
        product.setProductSupplier(GetSupplierForProduct(product.getSupplierID()));
        product.setProductImage(ImportImageFromPath(resultset.getString(10)));
        return product;
    }

    public ArrayList<Product> GetAllProductByCategoryAndSearchString(String category, String searchString ) throws SQLException
    {
        try
        {
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
            String query = "SELECT * FROM ProductDetails where ProductID =" + productID;

            Statement statement = _connection.createStatement();  //finish the SQL and get data into objects
            ResultSet resultset = statement.executeQuery(query);
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
                    productDetails.setOptionImage(ImportImageFromPath(productDetails.getDetailsValue()));
                }
                else
                {
                    productDetails.setOptionImage(null);
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

    private ArrayList<ContactInfo> GetContactInfoByUser(int userID) throws SQLException
    {
        try
        {
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

    private ContactInfo GetContactInfo(ResultSet resultset) throws SQLException
    {
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setContactInfoID(resultset.getInt(1));
        contactInfo.setContactTypeID(resultset.getInt(2));
        contactInfo.setContactUserID(resultset.getInt(3));
        contactInfo.setStreetAddress1(resultset.getString(4));
        contactInfo.setStreetAddress2(resultset.getString(5));
        contactInfo.setCity(resultset.getNString(6));
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
}

