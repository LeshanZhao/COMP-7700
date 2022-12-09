import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ProductWebServer extends StoreWebServer{
    ArrayList<Product> result_Products_List = null;
    public static void handle_ViewDetail(HttpExchange exchange) throws IOException {

        String response = "";
        HashMap<String, String> params = parseQuery(exchange);
        int productID = Integer.parseInt(params.get("id"));
        Product product_toViewDetail = null;
        WebDataAdapter sqlDataAdaptor = new WebDataAdapter();
        sqlDataAdaptor.connect();
        try {
            product_toViewDetail = sqlDataAdaptor.GetProductByID(productID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sqlDataAdaptor.disconnect();
        response += WebProductDetailView.getHTMLString_ForProductDetailPage(product_toViewDetail);
        writeResponse(exchange, response.length(),response);
    }
}
