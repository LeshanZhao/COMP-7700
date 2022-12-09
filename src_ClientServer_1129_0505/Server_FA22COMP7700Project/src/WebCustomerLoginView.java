public class WebCustomerLoginView {

    public static String getHTMLString_ForCustomerLoginRequest(WebDataAdapter sqlDataAdaptor) {
        String response =  "<html><body>" +
                "<h1>Welcome to the Online Shopping Website!</h1>" +
                "Author: 7700 OnlineShoppingSystem Team<br/>" +
                "";

        String img_name = "imgs/OnlineShoppingLogo.jpg";
        response += "<img src=\""
                + "https://github.com/LeshanZhao/7700Imgs/blob/main/"
                + img_name + "?raw=true"
                + "\" alt=\"image database currently not running, please try again later\"  style=\"width:300px;height:240px;\">";

        response += "<h3>Logining in as Customer:</h3>";

        response += "<form action=\"search\"> " +
                "  <label for=\"username\">Your Username:</label>" +
                "  <input type=\"text\" id=\"username\" name=\"username\" value=tnguyen>" +
                "  <label for=\"password\">&emsp; &emsp; Your Password:</label>" +
                "  <input type=\"password\" id=\"password\" name=\"password\" value=password>" +
                "  <input type=\"submit\" value=\"login\" />" +
                "</form>";

        response += "(Hint: Password for users lzhao, jravindra, mtabassum, donnapjackson2022 are \"password\" currently.)" +
                    "<br/>";
        response += "(We also prepared a user access for Prof. Nguyen, the username is \"tnguyen\" and the password is also \"password\".)" +
                    "<br/><br/>";
        response += "<button onclick=\"history.back()\">Go Back</button>";



        response += "</body></html>";

        return response;
    }
}
