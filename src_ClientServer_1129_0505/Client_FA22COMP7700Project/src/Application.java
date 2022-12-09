import javax.swing.*;
import java.sql.Connection;
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
    private JFrame _priorView;
    public Users getCurrectUser()
    {
        return _currentUser;
    }
    public void setCurrentUser(Users user)
    {
        _currentUser = user;
    }
    public JFrame getPriorView()
    {
        return _priorView;
    }
    public void setPriorView(JFrame priorView)
    {
        _priorView = priorView;
    }
    /************************************ Product *****************************************************/
    private ProductMaintenanceView _frmproductMaintView = new ProductMaintenanceView();
    private ProductController _productController ;
    public ProductMaintenanceView getProductMaintenanceView()
    {
        return _frmproductMaintView;
    }
    public ProductController getProductController()
    {
        return _productController;
    }
    /************************************ Welcome *****************************************************/
    private WelcomeView _welcomeView = new WelcomeView();
    private WelcomeController _welcomeController;
    public WelcomeView getWelcomeView()
    {
        return _welcomeView;
    }
    public WelcomeController getWelcomeController ()
    {
        return _welcomeController;
    }
    /************************************ Login *********************************************************/
    private LoginScreenView _loginScreenView = new LoginScreenView();
    private LoginController _loginController;
    public LoginScreenView getLoginScreenView()
    {
        return _loginScreenView;
    }
    /*public LoginController getLoginController() { return _loginController;}*/
    /************************************ Register *****************************************************/
    private RegisterViewScreen _registerView = new RegisterViewScreen();
    private RegisterController _registerController;
    public RegisterViewScreen getRegisterView()
    {
        return _registerView;
    }
    public RegisterController getRegisterController()
    {
        return _registerController;
    }
    /************************************ Buyer *********************************************************/
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
    /************************************ Search Products***************************************************/
    private SearchView _searchView = new SearchView();
    private SearchResultPanel _pnlSearchResult = new SearchResultPanel(_searchView);
    private ItemDetailController _itemDetailController;
    private SearchController _searchController;
    private SearchResultController _searchResultController;
    public SearchView getSearchView()
    {
        return _searchView;
    }
    public SearchController getSearchController()
    {
        return _searchController;
    }
    public SearchResultPanel getPnlSearchResult()
    {
        return _pnlSearchResult;
    }
    public SearchResultController getSearchResultController()
    {
        return _searchResultController;
    }
    public ItemDetailController getItemDetailController()
    {
        return _itemDetailController;
    }
    /************************************** Seller *********************************************************/
    private SellerView _sellerView = new SellerView();
    private SellerController _sellerController;
    private SuperUserView _superUserView = new SuperUserView();
    private SuperUserController _superUserController;

    public SellerView getSellerView()
    {
        return _sellerView;
    }
    public SellerController getSellerController()
    {
        return _sellerController;
    }
    public SuperUserView getSuperUserView()
    {
        return _superUserView;
    }
    public SuperUserController getSuperUserController()
    {
        return _superUserController;
    }


    /************************************** Data Access *********************************************************/
    private Connection _connection;
    public Connection getConnection()
    {
        return _connection;
    }
    private RemoteDataAdapter _remote_dataAdapter;
    public RemoteDataAdapter getDataAdapter()
    {
        return _remote_dataAdapter;
    }
    private void initializeDatabase(Statement stmt) throws SQLException {
        // create the tables and insert sample data here!

        stmt.execute("create table Product1 (ProductID int PRIMARY KEY, ProductName char(30), Price double, Quantity double)");
        //    stmt.execute("create table Order (ProductID int PRIMARY KEY, ProductName char(30), Price double, Quantity double)");

    }

    /************************************** Main Application Constructor **********************************/
    private Application()
    {
        _remote_dataAdapter = new RemoteDataAdapter();
        _welcomeController = new WelcomeController(_welcomeView);
        _loginController = new LoginController(_loginScreenView, _remote_dataAdapter);
        _searchController = new SearchController(_searchView, _remote_dataAdapter);
        _searchResultController = new SearchResultController(_searchView, _pnlSearchResult, _searchView.getPnlItemDetail());
//        _sellerController = new SellerController(_sellerView, _remote_dataAdapter);
        _itemDetailController = new ItemDetailController(_searchView, _remote_dataAdapter);
//        _checkoutController = new CheckoutController(_checkoutView, _remote_dataAdapter);

    }

    /* Application main method */
    public static void main(String[] args)
    {
        Application.getInstance().getWelcomeView().setVisible(true);
    }
}

