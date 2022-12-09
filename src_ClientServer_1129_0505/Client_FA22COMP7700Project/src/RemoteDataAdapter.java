import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class RemoteDataAdapter implements DataAccess {
    Gson gson = new Gson();
    Socket s = null;
    DataInputStream dis = null;
    DataOutputStream dos = null;
    @Override
    public void connect() {
        try {
            s = new Socket("18.217.155.95", 7700);
//            s = new Socket("localhost", 7700);
//            s = new Socket("192.168.208.1", 7700);
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private ArrayList<Product> cast_ProductModels_ToProducts(ArrayList<ProductModel> received_ProductModels){
        ArrayList<Product> received_Products = new ArrayList<Product>();
        for (ProductModel pm: received_ProductModels){
            Product p = new Product();
            p.setProductID(pm.getProductID());
            p.setProductName(pm.getProductName());
            p.setProductDescription(pm.getProductDescription());
            p.setQuantityOnHand(pm.getQuantityOnHand());
            p.setPrice(pm.getPrice());
            p.setDateAdded(pm.getDateAdded());
            p.setActive(pm.getActive());
            p.setDateModified(pm.getDateModified());
            p.setSupplierID(pm.getSupplierID());
//            p.setProductDetailsList(pm.getProductDetailsList());
            p.setProductSupplier(pm.getProductSupplier());

            received_Products.add(p);
        }

        return received_Products;
    }


    private void saveImages_FromProductImgModel_ToProduct(ArrayList<ProductImageModel> received_ProductImages,
                                                          ArrayList<Product> received_Products){
        // Load Product Images (image and detail image)

        for (int i=0; i < received_Products.size(); i++) {
            BufferedImage nxt_Img = null;
            try {
                InputStream is = new ByteArrayInputStream(received_ProductImages.get(i).product_Image);
                nxt_Img = ImageIO.read(is);
                received_Products.get(i).setProductImage(nxt_Img);
            } catch (IOException e1){
                e1.printStackTrace();
                System.out.println( "unable to read product image from bytes format");
            }

            // Load Detail Image
            BufferedImage nxt_Detail_Img = null;
            try {
                InputStream is = new ByteArrayInputStream(received_ProductImages.get(i).product_Detail_Image);
                nxt_Detail_Img = ImageIO.read(is);
                received_Products.get(i).set_productDetailImage(nxt_Detail_Img); // no detailed image for product?
            } catch (IOException e2){
                e2.printStackTrace();
                System.out.println( "unable to read product detail image from bytes format");
            }

        }
    }

    @Override
    public ArrayList<Product> GetAllProductByCategoryAndSearchString
                            (String search_Category,
                             String search_Description)
                                    throws Exception
    {
        RequestModel req = new RequestModel();

        req.code = req.SEARCH_REQUEST;
        req.search_Category = search_Category;
        req.search_Description = search_Description;

        String json = gson.toJson(req);
        try {
            dos.writeUTF(json);

            String received = dis.readUTF();
            System.out.println("======================= New Request ==========================");

            System.out.println("Server response:" + received);


            ResponseModel res = gson.fromJson(received, ResponseModel.class);


            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
                System.out.println("================== Fail 1 =====================");
                return null;
            }
            else {        // this is a JSON string for a product information
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not find a product with that ID!");
                    System.out.println("================== Fail 2 =====================");
                    return null;
                }
                else {
                    ArrayList<ProductModel> model_SearchResults = gson.fromJson(res.body, new TypeToken<ArrayList<ProductModel>>(){}.getType());
                    System.out.println("Search Result size = " + model_SearchResults.size());
                    ArrayList<Product> product_SearchResults = cast_ProductModels_ToProducts(model_SearchResults);
                    System.out.println("Product Search Result size = " + product_SearchResults.size());
                    System.out.println("Received a list of ProductModel objects and casted them to Products");
                    for (Product p: product_SearchResults){
                        System.out.println("Product ID = " + p.getProductID());
                        System.out.println("Product name = " + p.getProductName());
                    }

                    int results_count = model_SearchResults.size();
                    System.out.println("Results found: " + results_count);
                    ArrayList<ProductImageModel> search_Result_Images = new ArrayList<ProductImageModel>();

                    for (int nxt_result = 0; nxt_result < results_count; nxt_result++){

                        ProductImageModel next_Product_Images = new ProductImageModel();
                        System.out.println("=================== thie line is runed 2 ====================");

                        // read image for the next product
                        int img_length = dis.readInt();
                        System.out.println(img_length);
                        next_Product_Images.product_Image = dis.readNBytes(img_length);

                        // read detail image for the next product
                        int detail_img_length = dis.readInt();
                        next_Product_Images.product_Detail_Image = dis.readNBytes(detail_img_length);

                        search_Result_Images.add(next_Product_Images);
                        System.out.println("=================== thie line is runed 1 ====================");

                    }

                    saveImages_FromProductImgModel_ToProduct(search_Result_Images,
                                                             product_SearchResults);

                    System.out.println("=================== Result downloading done ====================");
                    return product_SearchResults; // found this product and return!!!
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public Users GetUserWithUsernamePassword(String username, String password) throws Exception {
        RequestModel req = new RequestModel();

        req.code = req.USER_REQUEST;
        req.username = username;
        req.password = password;

        String json_req = gson.toJson(req);
        try {
            dos.writeUTF(json_req);

            String received = dis.readUTF();
            System.out.println("======================= New Request ==========================");

            System.out.println("Server response:" + received);


            ResponseModel res = gson.fromJson(received, ResponseModel.class);


            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
                System.out.println("================== Fail 1 =====================");
                return null;
            }
            else {        // this is a JSON string for a product information
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not find a user with that username/password!");
                    System.out.println("================== Fail 2 =====================");
                    return null;
                }
                else {
                    Users login_User = gson.fromJson(res.body, Users.class);
                    System.out.println("Receiving a User Profile object");
                    System.out.println("User ID = " + login_User.getUserID());
                    System.out.println("User First name = " + login_User.getFirstName());
                    System.out.println("User Role ID = " + login_User.getUserRoleID());

                    System.out.println("=================== User Profile downloading done ====================");
                    return login_User; // found this user and return!!!
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public UserRole GetUserRoleWithUserRoleID(int userRoleID) throws Exception
    {
        RequestModel req = new RequestModel();

        req.code = req.USER_ROLE_REQUEST;
        req.userroleID = userRoleID;


        String json_req = gson.toJson(req);
        try {
            dos.writeUTF(json_req);

            String received = dis.readUTF();
            System.out.println("======================= New Request ==========================");

            System.out.println("Server response:" + received);


            ResponseModel res = gson.fromJson(received, ResponseModel.class);


            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
                System.out.println("================== Fail 1 =====================");
                return null;
            }
            else {        // this is a JSON string for a userRole information
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not find a user role with that Userrole ID!");
                    System.out.println("================== Fail 2 =====================");
                    return null;
                }
                else {
                    UserRole login_UserRole = gson.fromJson(res.body, UserRole.class);
                    System.out.println("Receiving a User Role object");
                    System.out.println("User Role ID = " + login_UserRole.getRoleID());
                    System.out.println("User Role name = " + login_UserRole.getRoleName());

                    System.out.println("=================== User Role Info downloading done ====================");
                    return login_UserRole; // found this product and return!!!
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }



    @Override
    public ProductModel load_Product(String productID) {
        RequestModel req = new RequestModel();
        ArrayList<RequestModel> new_item = new ArrayList<RequestModel>();
        req.code = req.LOAD_PRODUCT_REQUEST;
        req.body = String.valueOf(productID);

        new_item.add(req);
        String json = gson.toJson(new_item);

//        String json = gson.toJson(req);
        try {
            dos.writeUTF(json);

            String received = dis.readUTF();

            System.out.println("Server response:" + received);

            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
                return null;
            }
            else {// this is a JSON string for a product information
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not find a product with that ID!");
                    return null;
                } else {
                    ProductModel model = gson.fromJson(res.body, ProductModel.class);
                    System.out.println("Receiving a ProductModel object");
//                    System.out.println("ProductID = " + model.product_ID);
//                    System.out.println("Product name = " + model.product_Name);
                    return model; // found this product and return!!!
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean SaveOrder(Orders orders){
        return true;
    }

    public ArrayList<Shipper> GetAllShippers(){
        return new ArrayList<>();
    }


    @Override
    public void saveProduct(Product product) {}

    @Override
    public int GetNextOrdersDetailID() {
        return 0;
    }

    @Override
    public ShipperMethod GetShipperMethodByShipperIDAndMethod(int shipperID, String shipperMethod){
        return new ShipperMethod();
    }

    @Override
    public int GetNextOrderID(){
        return 0;
    }

    public ContactInfo GetShippingContactInfoByUser(int currentUserID){
        return new ContactInfo();
    }
}
