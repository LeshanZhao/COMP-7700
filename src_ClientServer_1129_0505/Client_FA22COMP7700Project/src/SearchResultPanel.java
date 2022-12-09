import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

public class SearchResultPanel extends JPanel
{
    private SearchView _searchView;

    private SearchResultController _searchResultController;
    private List<Product> _productResultList;
    private JPanel _pnlUponScrollPane;
    private JScrollPane _scrollPaneResults;

    public JPanel getPnlUponScrollPane()
    {
        return  _pnlUponScrollPane;
    }
    private JButton _btnAddToCart;
    private JButton _btnBuyNow;
    private JButton _btnViewDetail = new JButton("View Detail");


    public JButton getBtnAddToCart() { return _btnAddToCart; }
    public JButton getBtnBuyNow() { return _btnBuyNow; }

    public JButton getBtnViewDetail()
    {
        return _btnViewDetail;
    }
    public void setBtnViewDetail(JButton btnViewDetail)
    {
        _btnViewDetail = btnViewDetail;
    }

     public SearchResultPanel(SearchView searchView)
    {
        // Initialization
        this._searchView = searchView;
        /* ================= This is the Panel Containing all the searching results ================= */
        this.setBackground(Color.lightGray);
        this.setBorder(new EmptyBorder(0, 10, 0, 10));
        this.setBounds(60, 110, 900, 550);
        this.setBorder(BorderFactory.createTitledBorder("Search Results"));
        this.setVisible(false);
        this.setLayout(null);

        // Create a Scroll Pane to display all product items.
        // (In Swing, an Extra panel is required for ScrollPane to include multiple components)
        _scrollPaneResults = new JScrollPane();
        _scrollPaneResults.setBounds(15, 20, 835, 500);
//        ScrollPane_Results.setBorder(BorderFactory.createTitledBorder("ScrollPane (displaying for debug)"));
        this.add(_scrollPaneResults, BorderLayout.CENTER);

        // Create the extra panel to contain all the sub-panels for each product item
        _pnlUponScrollPane = new JPanel();
        _pnlUponScrollPane.setLayout(new GridLayout(searchView.getItemsCount(), 1, 10, 10));
        _pnlUponScrollPane.setBackground(Color.lightGray);
        _scrollPaneResults.setViewportView(_pnlUponScrollPane);
        _scrollPaneResults.setVerticalScrollBar(new JScrollBar());
    }


    public void LoadSearchResultPanel(List<Product> productResultList)
    {
        _productResultList = productResultList;
        _pnlUponScrollPane = new JPanel();
        _pnlUponScrollPane.setLayout(new GridLayout(_searchView.getItemsCount(), 1, 10, 10));
        _pnlUponScrollPane.setBackground(Color.lightGray);
        _scrollPaneResults.setViewportView(_pnlUponScrollPane);
        _scrollPaneResults.setVerticalScrollBar(new JScrollBar());

        for (Product p: productResultList)
        {

            JPanel subPanelItemPreview = new JPanel();
            subPanelItemPreview.setLayout(null);
            subPanelItemPreview.setPreferredSize(new Dimension(600,190));
//            subPanelItemPreview.setBorder(BorderFactory.createTitledBorder(p.getProductName()));  // for debug only

            BufferedImage img = p.getProductImage();
            img = p.getProductImage();
            JLabel img_Item =new JLabel(new ImageIcon(img));
            img_Item.setBounds(20, 20, 160, 160);
            subPanelItemPreview.add(img_Item);

            // Display Product Name
            JLabel lblProductName = new JLabel(p.getProductName());
            lblProductName.setBounds(210, 20, 500, 60);
            lblProductName.setFont(new Font("", Font.PLAIN, 16));
            subPanelItemPreview.add(lblProductName);

            // Display Price in $
            JLabel lblPrice = new JLabel(String.valueOf(p.getPrice()));
            lblPrice.setBounds(850, 20, 500, 60);
            lblPrice.setFont(new Font("", Font.PLAIN, 16));
            subPanelItemPreview.add(lblPrice);

            // Display "Buy Now" Button (Event on button clicked not implemented)
            _btnBuyNow = new JButton("Buy Now");
            _btnBuyNow.setBounds(680, 90, 100, 30);
            subPanelItemPreview.add(_btnBuyNow);
            _searchView.getSearchResultPanel().getBtnBuyNow().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Button \"Buy Now\" for some item is clicked.");
                    String confirmationNumber = GetConfirmationNumber();
                    JOptionPane.showMessageDialog(null, "Your Order has been submitted.\r\n" +
                            "You bought a product \"" + p.getProductName() + "\" successfully.\r\n" +
                            "Your confirmation number is " + confirmationNumber + "\r\n" +
                            "(Payment part is being implemented)");
                }
            });

            // Display "Add to Cart" Button (Event on button clicked not implemented)
            _btnAddToCart = new JButton("Add to Cart");
            _btnAddToCart.setBounds(680, 130, 100, 30);
            subPanelItemPreview.add(_btnAddToCart);
            _searchView.getSearchResultPanel().getBtnAddToCart().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Button \"Add to Cart\" for some item is clicked.");
                    JOptionPane.showMessageDialog(null, "You have added a product \r\n " +
                            "     \"" + p.getProductName() + "\" \r\n" +
                            "to your shopping cart successfully.\r\n" +
                            "(Payment part is being implemented)");
                }
            });

            // Display "View Detail" Button and Set its name as product ID (Event: Show the Item Detail Page (a panel) )= new JButton("View Detail");
            _searchView.getSearchResultPanel().setBtnViewDetail(new JButton("View Detail"));
            _searchView.getSearchResultPanel().getBtnViewDetail().setBounds(210, 130, 100, 30);
            _searchView.getSearchResultPanel().getBtnViewDetail().setName(String.valueOf(p.getProductID()));

            /* ================= Action on button "View Detail" for this item clicked  ================= */
            _searchView.getSearchResultPanel().getBtnViewDetail().addActionListener(new ActionListener() {
                String itemID = _searchView.getSearchResultPanel().getBtnViewDetail().getName();
                @Override
                public void actionPerformed(ActionEvent e) {

                    System.out.println("button_ViewDetail.getName() is: " + itemID);
                    System.out.println("Button \"View Detail\" for some item is clicked.");

                    _searchView.getPnlItemDetail().setProductSelected(GetProductByID(Integer.parseInt(itemID)));
                    _searchView.getSearchResultPanel().setVisible(false);
                    _searchView.getPnlItemDetail().load_ItemDetail();
                    _searchView.getPnlItemDetail().setVisible(true);
                }

            });

            subPanelItemPreview.add( _searchView.getSearchResultPanel().getBtnViewDetail());
            _searchView.getSearchResultPanel().getPnlUponScrollPane().add(subPanelItemPreview);
        }
    }

    private Product GetProductByID(int selected_ID){
        Product selected_Product = null;
        for (Product p: _productResultList){
            if (p.getProductID() == selected_ID){
                selected_Product = p;
                return selected_Product;
            }
        }
        System.out.println("Something wrong when getting product by ID.");
        return selected_Product;
    }

    private String GetConfirmationNumber()
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