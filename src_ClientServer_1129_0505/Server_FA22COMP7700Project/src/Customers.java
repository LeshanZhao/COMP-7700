import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.net.InetSocketAddress;

@Path("/users")
public class Customers {

    @GET
    public String getAllUsers() {
        return "list of users";
    }

    // Manage customer details
    @POST
    public void manageCustomers(@QueryParam("method") String method,
                                MultivaluedMap<String, String> formParams) {
        if (method.equals("create")) {
            //create new customer
        } else if (method.equals("update")) {
            // update an existing customer
        } else if (method.equals("delete")) {
            // delete a customer
        }
    }
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8500), 0);
        HttpContext context = server.createContext("/");

        System.out.println("WebServer is listening at port " + 8500);
        server.start();
    }
}
