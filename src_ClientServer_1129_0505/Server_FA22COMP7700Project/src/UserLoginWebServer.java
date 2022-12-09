import java.io.IOException;
import java.util.HashMap;

import com.sun.net.httpserver.HttpExchange;


public class UserLoginWebServer extends StoreWebServer{

    static void handle_UserLoginRequest(HttpExchange exchange) throws IOException {
        System.out.println("============= Request in / context - User Login!!! =============");

        String response = "";

        if (exchange.getRequestURI().getQuery() != null) {
            HashMap<String, String> params = parseQuery(exchange);
            if (params.get("userrole").equals("Register")){
                System.out.println("Register Login!!!");
                handle_Register_LoginRequest(exchange, response);
            }
            else if (params.get("userrole").equals("Seller")){
                handle_Seller_LoginRequest(exchange, response);
            }
            else if (params.get("userrole").equals("Guest")){
                handle_Guest_LoginRequest(exchange, response);
            }
            else if (params.get("userrole").equals("Customer")){
                handle_Customer_LoginRequest(exchange, response);
            }
        }
    }

    public static void handle_Register_LoginRequest(HttpExchange exchange, String response) throws IOException {
        System.out.println("============= Request in / context - Register Login!!! =============");
        WebDataAdapter sqlDataAdaptor = new WebDataAdapter();
        sqlDataAdaptor.connect();
        response += WebRegisterLoginView.getHTMLString_ForRegisterLoginRequest(sqlDataAdaptor);
        sqlDataAdaptor.disconnect();

        writeResponse(exchange, response.length(), response);
    }

    public static void handle_Seller_LoginRequest(HttpExchange exchange, String response) throws IOException {
        System.out.println("============= Request in / context - Seller Login!!! =============");
        WebDataAdapter sqlDataAdaptor = new WebDataAdapter();
        sqlDataAdaptor.connect();
        response += WebSellerLoginView.getHTMLString_ForSellerLoginRequest(sqlDataAdaptor);
        sqlDataAdaptor.disconnect();

        writeResponse(exchange, response.length(), response);
    }

    public static void handle_Customer_LoginRequest(HttpExchange exchange, String response) throws IOException {
        System.out.println("============= Request in / context - Customer Login!!! =============");
        WebDataAdapter sqlDataAdaptor = new WebDataAdapter();
        sqlDataAdaptor.connect();
        response += WebCustomerLoginView.getHTMLString_ForCustomerLoginRequest(sqlDataAdaptor);
        sqlDataAdaptor.disconnect();

        writeResponse(exchange, response.length(), response);
    }

    public static void handle_Guest_LoginRequest(HttpExchange exchange, String response) throws IOException {
        System.out.println("============= Request in / context - Guest Login!!! =============");

        response += WebGuestLoginView.getHTMLString_ForGuestLoginRequest();

        writeResponse(exchange, response.length(), response);
    }
}
