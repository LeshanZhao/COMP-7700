import java.util.ArrayList;

public class WebSearchProductView {

    public static String getHTMLString_ForSearchPage() {

        String response =  "<html><body>" +
                "<h1>Welcome to the Online Shopping Website!</h1>";

        String img_name = "imgs/OnlineShoppingLogo.jpg";
        response += "<img src=\""
                + "https://github.com/LeshanZhao/7700Imgs/blob/main/"
                + img_name + "?raw=true"
                + "\" alt=\"image database currently not running, please try again later\"  style=\"width:200px;height:150px;\">";

        response += "<p style=\"color:Tomato;\">Please specify the Category and describe the product you want at below.</p><br/>";

        response += construct_Category_optionBox("Household", "");

        response += "</body></html>";

        return response;
    }

    public static String getHTMLString_getSearchResults(String Category, String Description, ArrayList<Product> result_Products) {

        String response =  "<html><body>" +
                "<h1>Welcome to the Online Shopping Website!</h1>";

        response += construct_Category_optionBox(Category, Description);

        response += "Your are searching for products with category= -" + Category + "- and Keyword= -" + Description + "-.";

        if (result_Products.size() == 0) {
            System.out.println("No products found with this Category and Description! Please Try different keywords.");
            response += "<p style=\"color:Tomato;\">No Products Found with this category and description! </br>Please try different keywords or category.</p>";
        }
        else {
            System.out.println(result_Products.size() + " items are found from your search.");
            response += "<p style=\"color:Tomato;\">" + result_Products.size() + " items are found from your search </p>";
            response += construct_results_products_list(result_Products);
        }

        response += "</body></html>";
        return response;

    }


    private static String construct_results_products_list(ArrayList<Product> result_Products_List) {
        String result_Products = "";
        result_Products += "<h3> Products Information</h3>";
        for (Product product: result_Products_List) {
            String img_name = "broom1.jpg";
            String detail_img_name = "broom1Detail.jpg";

            if (product.getProductID() == 1001){
                img_name = "broom1.jpg";
                detail_img_name = "broom1Detail.jpg";
            }
            else if (product.getProductID() == 1002){
                img_name = "broom2.jpg";
                detail_img_name = "broom2Detail.jpg";
            }
            else if (product.getProductID() == 1003) {
                img_name = "broom3.jpg";
                detail_img_name = "broom3Detail.jpg";
            }

            result_Products += "<table style=\"width:70%\" style = \"background-color:DodgerBlue;\" border = \"3\">\n";

            result_Products += "<tr><td> Product ID </td>";
            result_Products += "<td> " +
                    "<form action=\"productDetail\">"
                    + "      <input type=\"radio\" id=\"productID\" name=\"id\" value=\"" + product.getProductID() + "\" checked style=\"visibility:hidden\">"
                    + "      <label for=\"productID\">"+ product.getProductID() + "</label>" +
                                            "&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;" +
                                            "&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"

                    + "      <input type=\"submit\"  value=\"View Detail\" />\n"
                    + "</form>" + " </td></tr>";


            result_Products += "<tr><td> Product Name </td>";
            result_Products += "<td> " + product.getProductName() + " </td></tr>";


            result_Products += "<tr><td> Price </td>";
            result_Products += "<td> " + product.getPrice() + " </td></tr>";

            result_Products += "<tr><td> Quantity In Stock </td>";
            result_Products += "<td> " + product.getQuantityOnHand() + " </td></tr>";

            result_Products += "<tr><td> Preview </td>";
            result_Products += "<td> " +
                    "<img src=\"" + "https://github.com/LeshanZhao/7700Imgs/blob/main/" + img_name + "?raw=true" + "\" alt=\"img database currently not running, please try again later\"  style=\"width:128px;height:128px;\">\n"
                    + " </td></tr>";


            String confirm_Number = StoreWebServer.GetConfirmationNumber();
            result_Products += "<tr><td></td>";
            result_Products += "<td> "
                    + "      <input type=\"button\" onclick=\"myFunction()\" value=\"Buy Now\" />\n"
                    + " </td></tr>";
            result_Products += "</table>";
            result_Products += "<br/>";

            result_Products += "<script>\n" +
                    "function myFunction() {\n" +
                    "   alert('"
                    + "Your Order has been submitted. Confirmation number is " + confirm_Number
                    + "')" +
                    "}\n" +
                    "</script>\n";

        }
        return result_Products;
    }

    private static String construct_Category_optionBox(String selected_category, String Description){
        String[] categories = {"All", "Books", "Clothing", "Electronics", "Food",
                "Grocery", "Household", "Kids", "Medicine", "Outdoors",
                "Pet Supplies", "Sports", "Others"};

        ArrayList<String> selected = new ArrayList<>();
        for (int i=0; i< categories.length; i++){
            if (selected_category.equals(categories[i])){
                selected.add(" selected");
            }
            else {
                selected.add("");
            }
        }

        String OptionBox = "";
        OptionBox += " <form action=\"search\" style=\"background-color:LightGray;\">\n" +
                "      <label for=\"category\">Choose a category:</label>\n" +
                "      <select name=\"category\" id=\"category\">\n";

        for (int i=0; i< categories.length; i++){
            OptionBox +=
                "        <option " +
                        "value=\""+ categories[i] + "\"" + selected.get(i) + ">" +
                        categories[i] +
                        "</option>\n";
        }

        OptionBox +=
                "      </select><br/>" +
                "      Describe what you want:" +
                "      <input type=\"text\" name=\"description\" value=\"" + Description + "\">\n" +
                "      <input type=\"submit\" value=\"New Search\" />\n" +
                "</form>";

        return OptionBox;
    }


}
