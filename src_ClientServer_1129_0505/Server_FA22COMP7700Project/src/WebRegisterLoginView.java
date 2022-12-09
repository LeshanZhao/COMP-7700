public class WebRegisterLoginView {
    public static String getHTMLString_ForRegisterLoginRequest(WebDataAdapter sqlDataAdaptor) {
        String response =  "<html><body>" +
                "<h1>Welcome to the Online Shopping Website!</h1>" +
                "Author: 7700 OnlineShoppingSystem Team<br/>" +
                "";

        String img_name = "imgs/OnlineShoppingLogo.jpg";
        response += "<img src=\""
                + "https://github.com/LeshanZhao/7700Imgs/blob/main/"
                + img_name + "?raw=true"
                + "\" alt=\"image database currently not running, please try again later\"  style=\"width:300px;height:240px;\">";


        response += "</br>The page for register is under construction.</br>" +
                "Please try to login as a guest or a customer.</br></br>" +
                "<button onclick=\"history.back()\">Go Back</button>";

        response += "</body></html>";

        return response;
    }


}
