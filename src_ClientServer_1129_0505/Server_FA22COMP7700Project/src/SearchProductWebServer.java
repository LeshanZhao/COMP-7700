import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SearchProductWebServer extends StoreWebServer{

    static void handle_getSearchPageRequest(HttpExchange exchange) throws IOException {
        System.out.println("Request in / context - load search page (customer/search or guest/search or /search )!!!");
        String response = "";
        response += WebSearchProductView.getHTMLString_ForSearchPage();
        writeResponse(exchange, response.length(), response);
    }

    static void handle_SearchRequest(HttpExchange exchange) throws IOException{
        System.out.println("Request in / context - search with category and description!!!");

        String response = "";
        HashMap<String, String> params = null;

        if (exchange.getRequestURI().getQuery() == null){
            response += WebSearchProductView.getHTMLString_ForSearchPage();
            writeResponse(exchange, response.length(), response);
            return;
        }
        else {
            params = parseQuery(exchange);
            if (params.containsKey("username")) {
                response += WebSearchProductView.getHTMLString_ForSearchPage();
                writeResponse(exchange, response.length(), response);
                return;
            }
        }

        String Category = params.get("category");
        String Description = params.get("description");



        ArrayList<Product> result_Products = null;

        try {
            WebDataAdapter sqlDataAdapter = new WebDataAdapter();
            sqlDataAdapter.connect();
            result_Products = sqlDataAdapter.GetAllProductByCategoryAndSearchString(Category, Description);
            sqlDataAdapter.disconnect();
        }
        catch (Exception e) {
            System.out.println("Something Wrong when trying to get product by category and search string");
            e.printStackTrace();
        }

        response += WebSearchProductView.getHTMLString_getSearchResults(Category, Description, result_Products);

        writeResponse(exchange, response.length(), response);

    }

}
