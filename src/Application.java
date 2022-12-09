import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class Application
{
    private static Application _instance;   // Singleton pattern
    public static Application getInstance()
    {
        if (_instance == null)
        {
            _instance = new Application();
        }
        return _instance;
    }
    // Main components of this application



    /************************************ Current Users *********************************************************/
    private Users _currentUser = null;
    public Users getCurrectUser()
    {
        return _currentUser;
    }
    public void setCurrentUser(Users user)
    {
        _currentUser = user;
    }
/*    private JFrame _priorView;
    public JFrame getPriorView()
    {
        return _priorView;
    }
    public void setPriorView(JFrame priorView)
    {
        _priorView = priorView;
    }*/
    /************************************ Product *****************************************************/
    private ProductMaintenanceView _frmproductMaintView = new ProductMaintenanceView();
    public ProductMaintenanceView getProductMaintenanceView()
    {
        return _frmproductMaintView;
    }
    private ProductController _productController ;
    public ProductController getProductController()
    {
        return _productController;
    }
    /************************************ Welcome *****************************************************/
    private WelcomeView _welcomeView = new WelcomeView();
    public WelcomeView getWelcomeView()
    {
        return _welcomeView;
    }
    private WelcomeController _welcomeController;
    public WelcomeController getWelcomeController ()
    {
        return _welcomeController;
    }
    /************************************ Login *********************************************************/
    private LoginScreenView _loginScreenView = new LoginScreenView();
    public LoginScreenView getLoginScreenView()
    {
        return _loginScreenView;
    }
    private LoginController _loginController;
    /*public LoginController getLoginController() { return _loginController;}*/
    /************************************ Register *****************************************************/
    private RegisterViewScreen _registerView = new RegisterViewScreen();
    public RegisterViewScreen getRegisterView()
    {
        return _registerView;
    }
    private RegisterController _registerController;
    public RegisterController getRegisterController()
    {
        return _registerController;
    }
    /************************************ Buyer *********************************************************/
/*
    private BuyerView _buyerView = new BuyerView();
    public BuyerView getBuyerView()
    {
        return _buyerView;
    }
    private BuyerController _buyerController;
    public BuyerController getBuyerController()
    {
        return _buyerController;
    }
*/
    /************************************ Search Products***************************************************/
    private SearchView _searchView = new SearchView();
    public SearchView getSearchView()
    {
        return _searchView;
    }
    public void setSearchView(SearchView searchView)
    {
        _searchView = searchView;
    }
    private SearchController _searchController;
    public SearchController getSearchController()
    {
        return _searchController;
    }
    private SearchResultPanel _pnlSearchResult = new SearchResultPanel(_searchView);
    public SearchResultPanel getPnlSearchResult()
    {
        return _pnlSearchResult;
    }
    private SearchResultController _searchResultController;
    public SearchResultController getSearchResultController()
    {
        return _searchResultController;
    }
    private ItemDetailPanel _itemDetailPanel = new ItemDetailPanel(_searchView);
    public ItemDetailPanel getItemDetailPanel()
    {
        return _itemDetailPanel;
    }
    private ItemDetailController _itemDetailController;
    public ItemDetailController getItemDetailController()
    {
        return _itemDetailController;
    }
    /************************************** Buyer *********************************************************/
    private CheckoutView _checkoutView = new CheckoutView();
    public CheckoutView getCheckoutView()
    {
        return _checkoutView;
    }
    private CheckoutController _checkoutController;
    public CheckoutController getCheckoutController()
    {
        return _checkoutController;
    }
    private ShoppingCartView _shoppingCartView = new ShoppingCartView();
    public ShoppingCartView getShoppingCartView()
    {
        return _shoppingCartView;
    }
    private ShoppingCartController _shoppingCartController;
    public ShoppingCartController getShoppingCartController()
    {
        return _shoppingCartController;
    }
    /************************************** Seller *********************************************************/
    private SellerView _sellerView = new SellerView();
    public SellerView getSellerView()
    {
        return _sellerView;
    }
    private SellerController _sellerController;
    public SellerController getSellerController()
    {
        return _sellerController;
    }
    private SuperUserView _superUserView = new SuperUserView();
    public SuperUserView getSuperUserView()
    {
        return _superUserView;
    }
    private SuperUserController _superUserController;
    public SuperUserController getSuperUserController()
    {
        return _superUserController;
    }
    private Connection _connection;
    public Connection getConnection()
    {
        return _connection;
    }
    private DataAdapter _dataAdapter;
    public DataAdapter getDataAdapter()
    {
        return _dataAdapter;
    }
    private void initializeDatabase(Statement stmt) throws SQLException
    {
        // create the tables and insert sample data here!

        stmt.execute("create table Product1 (ProductID int PRIMARY KEY, ProductName char(30), Price double, Quantity double)");
        //    stmt.execute("create table Order (ProductID int PRIMARY KEY, ProductName char(30), Price double, Quantity double)");

    }

    /************************************** Main Application Constructor **********************************/
    private Application()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            _connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\djackson\\Documents\\FA22COMP7700Project\\OnLineShoppingDb.sqlite");
//          _connection = DriverManager.getConnection("jdbc:sqlite:E:\\CS\\java\\7700\\newRepo4\\FA22COMP7700Project\\OnLineShoppingDb.sqlite");

            Statement stmt = _connection.createStatement();
            if (!stmt.executeQuery("select * from Product").next()) // product table does not exist
                initializeDatabase(stmt);
        }
        catch(ClassNotFoundException classNotFoundException)
        {
            System.out.println("SQLite is not installed. System exits with error! \r\n");
            System.out.println("Error message is " + classNotFoundException.getMessage());
            System.out.println("Stack trace is " + classNotFoundException.getStackTrace());
            System.exit(1);
        }
        catch (SQLException ex)
        {
            System.out.println("SQLite database is not ready. System exits with error!");
            System.out.println("Error message is " + ex.getMessage());
            System.out.println("Stack trace is " + ex.getStackTrace());
            System.exit(2);
        }

        _dataAdapter = new DataAdapter(_connection);
        _welcomeController = new WelcomeController(_welcomeView, _dataAdapter);
        _loginController = new LoginController(_loginScreenView, _dataAdapter);
        _searchController = new SearchController(_searchView, _dataAdapter);
        _searchResultController = new SearchResultController(_searchView, _pnlSearchResult, _searchView.getPnlItemDetail());
        _sellerController = new SellerController(_sellerView, _dataAdapter);
        _itemDetailController = new ItemDetailController(_searchView, _dataAdapter);
        _checkoutController = new CheckoutController(_checkoutView, _dataAdapter);

    }

    /* Application main method */
    public static void main(String[] args)
    {
        Application.getInstance().getWelcomeView().setVisible(true);
    }
}

