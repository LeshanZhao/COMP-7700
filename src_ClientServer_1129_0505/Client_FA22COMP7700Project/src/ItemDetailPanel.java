import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class ItemDetailPanel extends JPanel
{
    private SearchView _searchView;
    private Product _productSelected;
    private JPanel _pnlDetailPayment = new JPanel();
    private JButton _btnBack = new JButton("Back");
    private JButton _buttonDetailBuy = new JButton("Buy Now");
    private JButton _buttonDetailShoppingCart= new JButton("Add to Cart");
    private JLabel _lblDetailName;
    private JLabel _lblDetailPrice;
    private JLabel _lblImgDetail;
    private JLabel _lblQuantity;
    private JTextPane _txtDescriptionPane;
    private JPanel _pnlMoreDetail = new JPanel();
    private JLabel _lblDescription = new JLabel();

    private JButton _btnFinishAndPay = new JButton("Finish and Pay");
    private JButton _btnContinueShopping = new JButton("Continue Shopping");



    public Product getProductSelected()
    {
        return _productSelected;
    }
    public void setProductSelected(Product productSelected)
    {
        _productSelected = productSelected;
    }
    public JButton getBtnBack()
    {
        return _btnBack;
    }
    public JPanel getPnlDetailPayment()
    {
        return _pnlDetailPayment;
    }
    public JButton getButtonDetailBuy()
    {
        return _buttonDetailBuy;
    }
    public JButton getButtonDetailShoppingCart()
    {
        return _buttonDetailShoppingCart;
    }
    public JLabel getLblDetailName()
    {
        return _lblDetailName;
    }
    public JLabel getLblDetailPrice()
    {
        return _lblDetailPrice;
    }

    public JLabel getLblQuantity()
    {
        return _lblQuantity;
    }
    public JLabel getLblImgDetail()
    {
        return _lblImgDetail;
    }
    public JTextPane getTxtDescription()
    {
        return _txtDescriptionPane;
    }
    public void setTxtDescriptionPane(JTextPane txtDescriptionPane)
    {
        _txtDescriptionPane = txtDescriptionPane;
    }
    public JButton getBtnContinueShopping()
    {
        return _btnContinueShopping;
    }
    public JButton getBtnFinishAndPay()
    {
        return _btnFinishAndPay;
    }

    public ItemDetailPanel(SearchView searchView)
    {
        _searchView = searchView;
        _searchView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _searchView.setTitle("Product Detail Information Screen");
        /* ================= Detail Information Page for the first Item ================= */
        setBounds(80, 130, 820, 560);

        setVisible(false);
        setLayout(null);


        // add image
        _lblImgDetail =new JLabel();
        _lblImgDetail.setBounds(20, 60, 280, 280);
        this.add(_lblImgDetail);

        // add "Back" Button
        _btnBack.setBounds(20,20, 80, 30);
        this.add(_btnBack);

        // add "buy now" Button
        _buttonDetailBuy = new JButton("Buy Now");
        _buttonDetailBuy.setBounds(720, 20, 100, 30);
        this.add(_buttonDetailBuy);

        // add item name
        _lblDetailName = new JLabel();
        _lblDetailName.setBounds(330, 60, 500, 30);
        _lblDetailName.setFont(new Font("", Font.PLAIN, 16));
        this.add(_lblDetailName);

        // add item price
        _lblDetailPrice = new JLabel();
        _lblDetailPrice.setBounds(730, 300, 100, 30);
        _lblDetailPrice.setFont(new Font("", Font.PLAIN, 16));
        this.add(_lblDetailPrice);

        // add number of item in stock
        _lblQuantity = new JLabel();
        _lblQuantity.setBounds(330, 300, 200, 30);
        _lblQuantity.setFont(new Font("", Font.PLAIN, 16));
        _lblQuantity.setForeground(Color.green);
        this.add(_lblQuantity);


        // Add a new container for other information (a sub panel)
        JPanel panel_MoreDetail = new JPanel();
        panel_MoreDetail.setBorder(BorderFactory.createTitledBorder("More Details:"));
        panel_MoreDetail.setLayout(null);
        panel_MoreDetail.setBounds(10, 350, 810, 200);
        this.add(panel_MoreDetail);

        // Add label "Description: "
        JLabel label_Description = new JLabel();
        label_Description.setBounds(20, 10, 200, 30);
        label_Description.setFont(new Font("", Font.BOLD, 20));
        label_Description.setText("Description:");
        panel_MoreDetail.add(label_Description);

        // add description
        _txtDescriptionPane = new JTextPane();
        _txtDescriptionPane.setBounds(20, 40, 760, 120);
        _txtDescriptionPane.setFont(new Font("", Font.PLAIN, 16));
        _txtDescriptionPane.setEditable(false);
        panel_MoreDetail.add(_txtDescriptionPane);

        // Add label "Comments: "
        JLabel label_Comments = new JLabel();
        label_Comments.setBounds(20, 160, 200, 30);
        label_Comments.setFont(new Font("", Font.BOLD, 20));
        label_Comments.setText("Comments (0):");

        panel_MoreDetail.add(label_Comments);


//
//        /* ================= Action on button "Back" clicked ================= */
//        _btnBack.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("Button \"Back\" for some item is clicked.");
//                searchView.getSearchResultPanel().setVisible(true);
//                searchView.getPnlItemDetail().setVisible(false);
//            }
//        });

    }

    public void load_ItemDetail(){
        // Load the information from data
        String item_Name =  String.valueOf(_productSelected.getProductName());
        String item_Price = String.valueOf(_productSelected.getPrice());
        String item_Amount_inStock = String.valueOf(_productSelected.getQuantityOnHand());
        String item_Description =String.valueOf(_productSelected.getProductDescription());
        String item_Comments = String.valueOf("");        // not included yet

        BufferedImage item_Detail_Img = _productSelected.get_productDetailImage();

        // Put the information of item into Detail View
        _lblDetailName.setText(item_Name);
        _lblQuantity.setText(item_Amount_inStock + " in stock");
        _lblDetailPrice.setText("$ " + item_Price);
        _txtDescriptionPane.setText(item_Description);

        // set detailed image
        _lblImgDetail.setIcon(new ImageIcon(item_Detail_Img));
    }
    
    public void LoadItemDetail(Product p)
    {
        BufferedImage img = p.getProductImage();
        _lblImgDetail = new JLabel(new ImageIcon(img));
        _lblImgDetail.setBounds(20, 60, 280, 280);
        add(_lblImgDetail);

        // add "Back" Button
        _btnBack.setBounds(20, 20, 80, 30);
        add(_btnBack);  //need ActionListener

        // add item name
        _lblDetailName = new JLabel();
        _lblDetailName.setText(p.getProductName());
        _lblDetailName.setBounds(20, 60, 500, 30);
        _lblDetailName.setFont(new Font("", Font.PLAIN, 16));
        add(_lblDetailName);

        // add item price
        _lblDetailPrice = new JLabel();
        String price = String.valueOf(p.getPrice());
        _lblDetailPrice.setText("$" + price);
        _lblDetailPrice.setBounds(730, 300, 100, 30);
        _lblDetailPrice.setFont(new Font("", Font.PLAIN, 16));
        add(_lblDetailPrice);

        // add number of item in stock
        _lblQuantity = new JLabel();
        _lblQuantity.setText(String.valueOf(p.getQuantityOnHand()));
        _lblQuantity.setBounds(330, 300, 200, 30);
        _lblQuantity.setFont(new Font("", Font.PLAIN, 16));
        _lblQuantity.setForeground(Color.green);
        add(_lblQuantity);

        // Add a new container for other information (a sub pnl)

        _pnlMoreDetail.setBorder(BorderFactory.createTitledBorder("More Details:"));
        _pnlMoreDetail.setLayout(null);
        _pnlMoreDetail.setBounds(10, 350, 1010, 500);
        _pnlMoreDetail.setAutoscrolls(true);
        add(_pnlMoreDetail);

        // Add label "Description: "

        _lblDescription.setText("Description: ");
        _lblDescription.setBounds(15, 5, 200, 30);
        _lblDescription.setFont(new Font("", Font.BOLD, 14));
        _lblDescription.setLabelFor(_txtDescriptionPane);
        _pnlMoreDetail.add(_lblDescription);

        // add description
        _txtDescriptionPane = new JTextPane();
        _txtDescriptionPane.setText(p.getProductDescription());
        _txtDescriptionPane.setBounds(20, 25, 800, 900);
        _txtDescriptionPane.setFont(new Font("", Font.PLAIN, 14));
        _txtDescriptionPane.setEditable(false);
        _txtDescriptionPane.setAutoscrolls(true);
        _pnlMoreDetail.add(_txtDescriptionPane);
        add(_pnlMoreDetail);

        /* ================= Panel for buy option in detail page ================ */

        _pnlDetailPayment.setBounds(600, 125, 150, 100);
        _pnlDetailPayment.setVisible(true);
        _pnlDetailPayment.setLayout(null);
        _pnlDetailPayment.setBorder(BorderFactory.createTitledBorder("Purchase Detail"));

        _buttonDetailBuy.setBounds(25, 25, 100, 30);
        _buttonDetailBuy.setName(String.valueOf(p.getProductID()));
        _pnlDetailPayment.add(_buttonDetailBuy);

        _buttonDetailShoppingCart.setBounds(25, 60, 100, 30);
        _buttonDetailShoppingCart.setName(String.valueOf(p.getProductID()));
        _buttonDetailShoppingCart.setEnabled(false);
        _pnlDetailPayment.add(_buttonDetailShoppingCart);

        add(_pnlDetailPayment);
        _searchView.add(this);
        setVisible(true);
    }

    public void ClearAllComponents()
    {
        _pnlDetailPayment.removeAll();
        _pnlMoreDetail = new JPanel();
        _lblImgDetail = new JLabel();
        add(_lblImgDetail);
        _lblDescription = new JLabel();
        add(_lblDescription);
        _lblDetailName = new JLabel();
        add(_lblDetailName);
        _lblDetailPrice = new JLabel();
        add(_lblDetailPrice);
        _lblQuantity = new JLabel();
        add(_lblQuantity);
        _txtDescriptionPane = new JTextPane();
        _pnlMoreDetail.add(_txtDescriptionPane);
        add(_pnlMoreDetail);
    }
}