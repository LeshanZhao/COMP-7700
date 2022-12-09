import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ItemDetailPanel extends JPanel
{
    private Product _productSelected;
    public Product getProductSelected()
    {
        return _productSelected;
    }
    public void setProductSelected(Product productSelected)
    {
        _productSelected = productSelected;
    }
    private JButton _btnBack = new JButton("Back");
    public JButton getBtnBack()
    {
        return _btnBack;
    }
    private JPanel _pnlDetailPayment = new JPanel();

    public JPanel getPnlDetailPayment()
    {
        return _pnlDetailPayment;
    }

    private JButton _buttonDetailBuy = new JButton("Buy Now");
    public JButton getButtonDetailBuy()
    {
        return _buttonDetailBuy;
    }
    private JButton _buttonDetailShoppingCart= new JButton("Add to Cart");
    public JButton getButtonDetailShoppingCart()
    {
        return _buttonDetailShoppingCart;
    }

    private JLabel _lblDetailName;

    public JLabel getLblDetailName()
    {
        return _lblDetailName;
    }
    private JLabel _lblDetailPrice;
    public JLabel getLblDetailPrice()
    {
        return _lblDetailPrice;
    }
    private JLabel _lblQuantity;
    public JLabel getLblQuantity()
    {
        return _lblQuantity;
    }
    private JLabel _lblImgDetail;
    public JLabel getLblImgDetail()
    {
        return _lblImgDetail;
    }
    private JTextPane _txtDescriptionPane;
    public JTextPane getTxtDescription()
    {
        return _txtDescriptionPane;
    }
    public void setTxtDescriptionPane(JTextPane txtDescriptionPane)
    {
        _txtDescriptionPane = txtDescriptionPane;
    }

    private JButton _btnContinueShopping = new JButton("Continue Shopping");
    public JButton getBtnContinueShopping()
    {
        return _btnContinueShopping;
    }
    private JButton _btnFinishAndPay = new JButton("Finish and Pay");
    public JButton getBtnFinishAndPay()
    {
        return _btnFinishAndPay;
    }
    private SearchView _searchView;
    private JPanel _pnlMoreDetail = new JPanel();
    private JLabel _lblDescription = new JLabel();

    public ItemDetailPanel(SearchView searchView)
    {
        _searchView = searchView;
        _searchView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _searchView.setTitle("Product Detail Information Screen");
        /* ================= Detail Information Page for the first Item ================= */
        setBounds(80, 130, 820, 560);

        setVisible(false);
        setLayout(null);
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