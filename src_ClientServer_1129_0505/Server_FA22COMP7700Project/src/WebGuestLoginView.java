public class WebGuestLoginView {

    public static String getHTMLString_ForGuestLoginRequest() {

        String temp_username = "NewUser_" + StoreWebServer.GetConfirmationNumber().substring(0,4);
        String temp_password = StoreWebServer.GetConfirmationNumber();

        String response =  "<html><body>" +
                "<h1>Welcome to the Online Shopping Website!</h1>" +
                "Author: 7700 OnlineShoppingSystem Team<br/>" +
                "";

        String img_name = "imgs/OnlineShoppingLogo.jpg";
        response += "<img src=\""
                + "https://github.com/LeshanZhao/7700Imgs/blob/main/"
                + img_name + "?raw=true"
                + "\" alt=\"image database currently not running, please try again later\"  style=\"width:300px;height:240px;\">";

        response += "<h3>Logining in as Guest:</h3>";

        response += "<form action=\"search\"> " +
                "  <label for=\"username\">You are assigned a temporary username: </label>" +
                "  <input type=\"text\" id=\"username\" name=\"username\" value=" + temp_username +"></br>" +
                "  <label for=\"password\">Your temporary password:</label>" +
                "  <input type=\"text\" id=\"password\" name=\"password\" value=" + temp_password + "></br>" +
                "  <input type=\"submit\" value=\"login\" />" +
                "</form>";

        response += "<button onclick=\"history.back()\">Go Back</button>";



        response += "</body></html>";

        return response;
    }
}
