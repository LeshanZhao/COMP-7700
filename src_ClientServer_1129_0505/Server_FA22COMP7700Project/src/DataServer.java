// Java implementation of Server side
// It contains two classes : Server and ClientHandler
// Save file as Server.java

import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


// Server class
public class DataServer
{
    private static String path = DataServer.class.getResource("").getPath();
    private static String database_url = "jdbc:sqlite:" + path + "../../../OnLineShoppingDb.sqlite";

    //    String url = "jdbc:sqlite:C:\\Users\\djackson\\Documents\\FA22COMP7700Project\\OnLineShoppingDb.sqlite";
    private static String database_url2 = "jdbc:sqlite:E:\\CS\\java\\7700\\newRepo4\\Server_FA22COMP7700Project\\OnLineShoppingDb.sqlite";
    private static Connection _connection = connect();

    private static void initializeDatabase(Statement stmt) throws SQLException {
        // create the tables and insert sample data here!

        stmt.execute("create table Product1 (ProductID int PRIMARY KEY, ProductName char(30), Price double, Quantity double)");
        //    stmt.execute("create table Order (ProductID int PRIMARY KEY, ProductName char(30), Price double, Quantity double)");

    }
    private static Connection connect(){
        try
        {
            Class.forName("org.sqlite.JDBC");
//            System.out.println(database_url);
//            System.out.println(database_url2);
//            System.out.println(path);
            _connection = DriverManager.getConnection(database_url);
            Statement stmt = _connection.createStatement();
            if (!stmt.executeQuery("select * from Product").next()) // product table does not exist
                initializeDatabase(stmt);
            return _connection;
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
        return null;
    }

    public static void main(String[] args) throws IOException, IOException {
        // server is listening on port 7700
        ServerSocket ss = new ServerSocket(7700);

        // running infinite loop for getting
        // client request

        System.out.println("Starting server program!!!");

        int nClients = 0;

        while (true)
        {
            Socket s = null;

            try
            {
                // socket object to receive incoming client requests
                s = ss.accept();

                nClients++;
                System.out.println("A new client is connected : " + s + " client number: " + nClients);

                // obtaining input and out streams
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                System.out.println("Assigning new thread for this client");

                // create a new thread object
                Thread t = new ClientHandler(s, dis, dos, _connection);

                // Invoking the start() method
                t.start();

            }
            catch (Exception e){
                s.close();
                e.printStackTrace();
            }
        }
    }
}

// ClientHandler class
class ClientHandler extends Thread
{
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;

    private Connection _connection;

    Gson gson = new Gson();

    DataAdapter sqlDataAdaptor;


    // Constructor
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos, Connection connection)
    {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
        this._connection = connection;
        sqlDataAdaptor = new DataAdapter(_connection);
    }

    @Override
    public void run()
    {

        String received;
        while (true) {
            try {
                // receive the answer from client
                received = dis.readUTF();
                System.out.println("Message from client " + received);

                RequestModel req = gson.fromJson(received, RequestModel.class);
                if (req.code == RequestModel.EXIT_REQUEST) {
                    System.out.println("Client " + this.s + " sends exit...");
                    System.out.println("Closing this connection.");
                    this.s.close();
                    System.out.println("Connection closed");
                    break;
                }

                ResponseModel res = new ResponseModel();
                ArrayList<ProductImageModel> search_product_Images = new ArrayList<ProductImageModel>();

                if (req.code == RequestModel.USER_REQUEST) {

                    System.out.println("The Client asks for user profile with username = " + req.username);
                    System.out.println("The Client asks for user profile with password = " + req.password);

                    Users loginUser = null;
                    try
                    {
                        //JOptionPane.showMessageDialog(null, "Login Screen View login button clicked");

                        loginUser = sqlDataAdaptor.GetUserWithUsernamePassword(req.username, req.password);
                    }
                    catch(SQLException sqlException)
                    {
                        System.out.println("btnLogin - Username and/or password is invalid");
                        sqlException.getStackTrace();
                    }

                    if (loginUser != null) {
                        res.code = ResponseModel.OK;
                        res.body = gson.toJson(loginUser);
                    }
                    else {
                        res.code = ResponseModel.DATA_NOT_FOUND;
                        res.body = "";
                    }
                }
                else if (req.code == RequestModel.USER_ROLE_REQUEST) {

                    System.out.println("The Client asks for user Role info with userrole ID = " + req.userroleID);

                    UserRole loginUserRole = null;
                    try
                    {
                        loginUserRole = sqlDataAdaptor.GetUserRoleWithUserRoleID(req.userroleID);
                    }
                    catch(SQLException sqlException)
                    {
                        System.out.println("btnLogin - UserRole ID is invalid");
                        sqlException.getStackTrace();
                    }

                    if (loginUserRole != null) {
                        res.code = ResponseModel.OK;
                        res.body = gson.toJson(loginUserRole);
                    }
                    else {
                        res.code = ResponseModel.DATA_NOT_FOUND;
                        res.body = "";
                    }
                }
                else if (req.code == RequestModel.SEARCH_REQUEST) {

                    System.out.println("The Client asks for a list of product \nwith Category = " + req.search_Category);
                    System.out.println("The Client asks for a list of product \nwith Description = " + req.search_Description);

                    ArrayList<Product> _productResultList = null;

                    try
                    {
                        _productResultList = sqlDataAdaptor.GetAllProductByCategoryAndSearchString(req.search_Category,
                                                                           req.search_Description);
                    }
                    catch(SQLException sqlException)
                    {
                        System.out.println("Search Controller - btnSearch - Error occurred while getting category and searchString");
                        System.out.println("SQLException Message " + sqlException.getMessage());
                        System.out.println("SQLException Stacktrace " + sqlException.getStackTrace());
                    }


                    if (_productResultList != null) {
                        ArrayList<ProductModel> _result_productModels_List = sqlDataAdaptor.cast_Products_ToProductModels(_productResultList);
                        search_product_Images = new ArrayList<ProductImageModel>();
                        sqlDataAdaptor.saveImages_FromProduct_ToProductImgModel(_productResultList, search_product_Images);

                        res.code = ResponseModel.OK;
                        res.body = gson.toJson(_result_productModels_List);


                    }
                    else {
                        res.code = ResponseModel.DATA_NOT_FOUND;
                        res.body = "";
                    }
                }
                else {
                    res.code = ResponseModel.UNKNOWN_REQUEST;
                    res.body = "";
                }


                String json = gson.toJson(res);
                System.out.println("JSON object of ResponseModel: " + json);
                dos.writeUTF(json);
                for (int nxt_prod=0; nxt_prod < search_product_Images.size(); nxt_prod++) {
                    ProductImageModel nxt_prod_Imgs = search_product_Images.get(nxt_prod);

                    System.out.println("Product Image ready to Send, length = " + nxt_prod_Imgs.get_product_Image().length);
                    dos.writeInt(nxt_prod_Imgs.get_product_Image().length);
                    dos.write(nxt_prod_Imgs.get_product_Image());

                    System.out.println("Product Detail Image ready to send, length = " + nxt_prod_Imgs.get_product_Detail_Image().length);
                    dos.writeInt(nxt_prod_Imgs.get_product_Detail_Image().length);
                    dos.write(nxt_prod_Imgs.get_product_Detail_Image());
                }
                System.out.println("DONE ========================");
                dos.flush();


            } catch (EOFException eofe) {
                System.out.println("End of file reached");
                break;
            }  catch(SocketException se){
                se.printStackTrace();
                System.out.println("Socket Exception. A client is halted without closing socket.");
                System.out.println("Socket closed and server continue to listen.");
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try
        {
            // closing resources
            this.dis.close();
            this.dos.close();

        } catch(IOException e){
            e.printStackTrace();
        }

    }
}
