public class WebProductDetailView {

    public static String getHTMLString_ForProductDetailPage(Product product_toViewDetail) {
        String response =  "<html><body>";

        response += "<h1>Welcome to the Online Shopping Website!</h1>";

        response += "<button onclick=\"history.back()\">Go Back</button> &emsp;&emsp;&emsp;";

        response += "Your are looking at product that you selected: </br>";
        response += "<b>Product Name:</b> <span style=\"color: blue;\">" + product_toViewDetail.getProductName() + "</span></br>";

        String img_name = "imgs/broom1Detail.jpg";
        if (product_toViewDetail.getProductID() == 1001){
            img_name = "imgs/broom1Detail.jpg";
        } else if (product_toViewDetail.getProductID() == 1002) {
            img_name = "imgs/broom2Detail.jpg";
        } else if (product_toViewDetail.getProductID() == 1003) {
            img_name = "imgs/broom3Detail.jpg";
            product_toViewDetail.setProductDescription("With a weather-resistant fiberglass handle with cushion grips, multi-sweep bristles, " +
                    "the Quickie commercial-grade Multi-Surface Push Broom was built for powerful cleaning. " +
                    "It was designed specifically with soft outer fibers and stiff inner fibers to sweep a wide range of debris. " +
                    "This push broom is ideal for use in garages, basements and on sidewalks for sand, mulch, grass clipping and drywall." +
                    "</br>&emsp;&emsp;-Durable dual fibers for maximum sweeping power" +
                    "</br>&emsp;&emsp;-Built-in scraper for tougher debris" +
                    "</br>&emsp;&emsp;-24 in. heavy-duty resin block with a diamond plate design that won't warp, rot or crack under normal usage" +
                    "</br>&emsp;&emsp;-Steel brace system for added strength and security" +
                    "</br>&emsp;&emsp;-Cushion end and mid grip for comfort");
        }


        response += "<img src=\""
                + "https://github.com/LeshanZhao/7700Imgs/blob/main/"
                + img_name + "?raw=true"
                + "\" alt=\"image database currently not running, please try again later\"  style=\"width:360px;height:360px;border:3px solid black\">";

        response += "</br> <b style=\"color:green\">" + product_toViewDetail.getQuantityOnHand() + " in stock</b>&emsp;&emsp;&emsp;&emsp;&emsp;";
        response += "<b>$ " + product_toViewDetail.getPrice() + "</b> </br></br>";
        response += "<b>Description:</b> </br>" +
                "<p style=\"color: blue;\">" + product_toViewDetail.getProductDescription() + "</p></br>";
        response += "<b>Comments:</b> <span style=\"color: blue;\">(0)</span></br>";


        String confirm_Number = StoreWebServer.GetConfirmationNumber();
        response += "</br><button onclick=\"myFunction()\">Buy Now</button>";
        response += "<script>\n" +
                "function myFunction() {\n" +
                "   alert('"
                + "Your Order has been submitted. Confirmation number is " + confirm_Number
                + "')" +
                "}\n" +
                "</script>";
        response += "</br></br></br>";

        response = response + "</body></html>";

        return response;
    }
}
