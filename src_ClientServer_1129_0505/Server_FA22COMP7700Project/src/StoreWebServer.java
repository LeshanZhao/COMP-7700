
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Random;

public class StoreWebServer {

    private static final String PATH = StoreWebServer.class.getResource("").getPath();
    private static final String database_url = "jdbc:sqlite:" + PATH + "../../../OnLineShoppingDb.sqlite";
    private static final int PORT = 7701;


    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
        HttpContext context = server.createContext("/");
        context.setHandler(StoreWebServer::handle_DefaultRequest);

        context = server.createContext("/UserLogin");
        context.setHandler(UserLoginWebServer::handle_UserLoginRequest);

        context = server.createContext("/customers");
        context.setHandler(StoreWebServer::handleCustomersRequest);

        context = server.createContext("/search");
        context.setHandler(SearchProductWebServer::handle_SearchRequest);

        context = server.createContext("/productDetail");
        context.setHandler(ProductWebServer::handle_ViewDetail);

        context = server.createContext("/customers/search");
        context.setHandler(SearchProductWebServer::handle_getSearchPageRequest);

        context = server.createContext("/guests/search");
        context.setHandler(SearchProductWebServer::handle_getSearchPageRequest);

        context = server.createContext("/registers");
        context.setHandler(StoreWebServer::handleRegisterRequests);

        context = server.createContext("/sellers");
        context.setHandler(StoreWebServer::handleSellersRequests);


        context = server.createContext("/testPort");
        context.setHandler(StoreWebServer::handle_TestPort_Request);

        System.out.println("WebServer is listening at port " + PORT);
        server.start();
    }

    private static void handle_DefaultRequest(HttpExchange exchange) throws IOException {
        System.out.println("Request in / context - Welcome!!!");

        String response = WebWelcomeView.getHTMLString_ForDefaultRequest();
        writeResponse(exchange, response.length(), response);
    }

    private static void handleSellersRequests(HttpExchange exchange) throws IOException {
        System.out.println("Request in / context - Sellers!!!");
        System.out.println("This component is under construction.");
        String response = "This component is under construction.";
        writeResponse(exchange, response.length(), response);
    }
    private static void handleCustomersRequest(HttpExchange exchange) {
    }
    private static void handleRegisterRequests(HttpExchange exchange) throws IOException {
        System.out.println("Request in / context - Registers!!!");
        System.out.println("This component is under construction.");
        String response = "This component is under construction.";
        writeResponse(exchange, response.length(), response);
    }

    private static void handle_TestPort_Request(HttpExchange exchange) throws IOException {
        String response = "<html><body><h1>This Port is Available!</h1> <br/>";
        response += " The Port number is " + PORT;
        response += "</body></html>";
        System.out.println("Someone is accessing at port: " + PORT);
        int length = response.length();

        exchange.sendResponseHeaders(200, length);//sb code and length
        OutputStream os = exchange.getResponseBody();

        os.write(response.getBytes());
        os.close();
    }

    static void writeResponse(HttpExchange exchange, int length, String s) throws IOException {
        exchange.sendResponseHeaders(200, length);//sb code and length
        OutputStream os = exchange.getResponseBody();
        os.write(s.getBytes());
        os.close();
        System.out.println("Response " + s);
    }

    static HashMap<String, String> parseQuery(HttpExchange exchange) {
        HashMap<String, String> params = new HashMap<>();
        for (String st : exchange.getRequestURI().getQuery().split("&")) {
            System.out.println(st);
            int p = st.indexOf("=");
            String key = st.substring(0, p).toLowerCase();
            String value = st.substring(p + 1);
            System.out.println(key + " ---> " + value);
            params.put(key, value);
        }
        return params;
    }

    static String GetConfirmationNumber()
    {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }
}

