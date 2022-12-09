public class WebWelcomeView {

    public static String getHTMLString_ForDefaultRequest() {
        String response =  "<html><body>" +
                "<h1>Welcome to the Online Shopping Website!</h1>" +
                "Author: 7700 OnlineShoppingSystem Team<br/>";

        String img_name = "imgs/OnlineShoppingLogo.jpg";
        response += "<img src=\""
                + "https://github.com/LeshanZhao/7700Imgs/blob/main/"
                + img_name + "?raw=true"
                + "\" alt=\"image database currently not running, please try again later\"  style=\"width:300px;height:240px;\">";
        response += "<h3>Please login first.</h3>" +
                "Login as:";
        response += "<form action=\"UserLogin\">\n" +

                "<input type=\"radio\" id=\"customer\" name=\"UserRole\" value=\"Customer\">" +
                "<label for=\"customer\">Customer</label>" +
                "</br>" +

                "<input type=\"radio\" id=\"seller\" name=\"UserRole\" value=\"Seller\">" +
                "<label for=\"seller\">Seller</label>" +
                "</br>" +

                "<input type=\"radio\" id=\"register\" name=\"UserRole\" value=\"Register\">" +
                "<label for=\"register\">Register</label>" +
                "</br>" +

                "<input type=\"radio\" id=\"guest\" name=\"UserRole\" value=\"Guest\">" +
                "<label for=\"guest\">Guest</label>" +
                "</br>" +

                "<input type=\"submit\" value=\"confirm\">" +
                "</form> ";

        response = response + "</body></html>";

        return response;
    }
}
