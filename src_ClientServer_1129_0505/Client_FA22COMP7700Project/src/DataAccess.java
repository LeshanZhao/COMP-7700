import java.sql.SQLException;
import java.util.ArrayList;

public interface DataAccess {
    void connect();



    ProductModel load_Product(String productID);

//    ArrayList<ProductModel> search_Product(String search_Category,
//                                           String search_Description,
//                                           ArrayList<ProductImageModel> search_Result_Images);

    Users GetUserWithUsernamePassword(String username, String password) throws Exception;

    ArrayList<Product> GetAllProductByCategoryAndSearchString
            (String category,
             String searchString) throws Exception;


    boolean SaveOrder(Orders orders);
    void saveProduct(Product product);
    ArrayList<Shipper> GetAllShippers();
    int GetNextOrdersDetailID();

    ShipperMethod GetShipperMethodByShipperIDAndMethod(int shipperID, String shipperMethod);
    int GetNextOrderID();

    ContactInfo GetShippingContactInfoByUser(int currentUserID);
}
