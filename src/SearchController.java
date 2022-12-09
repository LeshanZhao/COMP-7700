import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class SearchController implements ActionListener
{
    private DataAdapter _dataAdapter;
    private SearchView _searchView;
    private ArrayList<Product> _productResultList;
    public ArrayList<Product> getProductResultList()
    {
        return _productResultList;
    }

    public  SearchController(SearchView searchView, DataAdapter dataAdapter)
    {
        _dataAdapter = dataAdapter;
        _searchView = searchView;

        _searchView.getPnlSearchInputBox().getBtnSearch().addActionListener(this);
        _searchView.getPnlSearchInputBox().getBtnBack().addActionListener(this);
        _searchView.getSearchResultPanel().getBtnViewDetail().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        if(e.getSource() == _searchView.getPnlSearchInputBox().getBtnSearch())
        {
            try
            {
                _productResultList = new ArrayList<Product>();
                String searchString = "";
                String category = _searchView.getPnlSearchInputBox().getComboBoxCategory().getSelectedItem().toString();
                if(category.equals("All"))
                {
                    _productResultList = _dataAdapter.GetAllProducts();
                }
                else if(_searchView.getPnlSearchInputBox().getTxtSearchDescription().isEmpty())
                {
                    _productResultList = _dataAdapter.GetAllProductByCategory(category);
                }
                else
                {
                    searchString = _searchView.getPnlSearchInputBox().getTxtSearchDescription();
                    _productResultList = _dataAdapter.GetAllProductByCategoryAndSearchString(category, searchString);
                }
                //Types categoryType = _dataAdapter.GetTypesByTypeValue(category);
                if(_productResultList.size() > 0)
                {
                    LoadSearchResultPanel(_productResultList);
                    Application.getInstance().getSearchView().getSearchResultPanel().setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "No Products found.");
                }

            }
            catch(SQLException sqlException)
            {
                System.out.println("Search Controller - btnSearch - Error occurred while getting category and searchString");
                System.out.println("SQLException Message " + sqlException.getMessage());
                System.out.println("SQLException Stacktrace " + sqlException.getStackTrace());
            }
        }
        else if(e.getSource() == _searchView.getPnlSearchInputBox().getBtnBack())
        {
            _searchView.setVisible(false);
            //Application.getInstance().getPriorView().setVisible(true);
        }
        else if(e.getSource() == _searchView.getSearchResultPanel().getBtnViewDetail())
        {
            System.out.println("Search view Button \"View Detail\" for some item is clicked.");
            System.out.println(_searchView.getSearchResultPanel().getBtnViewDetail().getName());
            _searchView.getSearchResultPanel().setVisible(false);
            _searchView.getPnlItemDetail().setVisible(true);
            _searchView.getPnlItemDetail().getPnlDetailPayment().setVisible(true);

            String searchName = _searchView.getSearchResultPanel().getBtnViewDetail().getName();
        }
    }

    private void LoadSearchResultPanel(List<Product> productResultList) throws SQLException
    {
        int count = 0;
        for (Product p: productResultList)
        {
            String prodID = String.valueOf(p.getProductID());
            JPanel subPanelItemPreview = new JPanel();
            subPanelItemPreview.setLayout(null);
            subPanelItemPreview.setPreferredSize(new Dimension(600,250));
            subPanelItemPreview.setName(prodID);
            subPanelItemPreview.setBorder(BorderFactory.createTitledBorder(p.getProductName()));

            BufferedImage img = p.getProductImage();
            JLabel img_Item =new JLabel(new ImageIcon(img));
            img_Item.setBounds(20, 20, 75, 150);
            subPanelItemPreview.add(img_Item);

            JLabel lblProductID = new JLabel(prodID);
            lblProductID.setBounds(110, 20, 100, 20);
            lblProductID.setFont(new Font("Verdana", Font.PLAIN, 16));
            subPanelItemPreview.add(lblProductID);

            // Display Product Name
            JLabel lblProductName = new JLabel(p.getProductName());
            lblProductName.setBounds(210, 20, 500, 60);
            lblProductName.setFont(new Font("Verdana", Font.PLAIN, 16));
            subPanelItemPreview.add(lblProductName);

            // Display Price in $
            JLabel lblPrice = new JLabel(String.valueOf(p.getPrice()));
            lblPrice.setBounds(850, 20, 500, 60);
            lblPrice.setFont(new Font("Verdana", Font.PLAIN, 16));
            subPanelItemPreview.add(lblPrice);

            // Display "Buy Now" Button (Event on button clicked not implemented)
            //JButton btnBuyNow = new JButton("Buy Now");

            JButton btnBuyNow = new JButton("Buy Now");
            btnBuyNow.setBounds(680, 90, 100, 30);
            btnBuyNow.setFont(new Font("Verdana", Font.BOLD, 12));
            btnBuyNow.setName(prodID);
            subPanelItemPreview.add(btnBuyNow);
            btnBuyNow.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Application.getInstance().getCheckoutView().setProduct(p);
                    _searchView.setVisible(false);
                    Application.getInstance().getCheckoutView().setVisible(true);
                }
            });

            // Display "Add to Cart" Button (Event on button clicked not implemented)
            //JButton btnAddToCart = new JButton("Add to Cart");
            JButton btnAddToCart = new JButton("Add to Cart");
            btnAddToCart.setBounds(680, 130, 100, 30);
            btnAddToCart.setFont(new Font("Verdana", Font.PLAIN, 12));
            btnAddToCart.setName(String.valueOf(p.getProductID()));
            btnAddToCart.setEnabled(false);
            subPanelItemPreview.add(btnAddToCart);

            // Display "View Detail" Button and Set its name as product ID (Event: Show the Item Detail Page (a panel) )= new JButton("View Detail");
            _searchView.getSearchResultPanel().setBtnViewDetail(new JButton("View Detail"));
            _searchView.getSearchResultPanel().getBtnViewDetail().setBounds(210, 130, 100, 30);
            _searchView.getSearchResultPanel().getBtnViewDetail().setName(String.valueOf(p.getProductID()));
            _searchView.getSearchResultPanel().getBtnViewDetail().addActionListener(this);

            /* ================= Action on button "View Detail" for this item clicked  ================= */
            _searchView.getSearchResultPanel().getBtnViewDetail().addActionListener(new ActionListener()
            {
                String itemID = _searchView.getSearchResultPanel().getBtnViewDetail().getName();
                @Override
                public void actionPerformed(ActionEvent e)
                {

                    System.out.println("button_ViewDetail.getName() is: " + itemID);
                    System.out.println("Button \"View Detail\" for some item is clicked.");
                    Product p = null;
                    try
                    {
                        p = _dataAdapter.GetProductByID(Integer.parseInt(itemID));
                    } catch (SQLException ex)
                    {
                        System.out.println("SQL Exception. Getting Product by product_ID.");
                    }
                    _searchView.getPnlItemDetail().setProductSelected(p);
                    _searchView.getSearchResultPanel().setVisible(false);
                    _searchView.getPnlItemDetail().LoadItemDetail(p);
                    _searchView.getPnlItemDetail().setVisible(true);
                }

            });

            subPanelItemPreview.add( _searchView.getSearchResultPanel().getBtnViewDetail());
            _searchView.getSearchResultPanel().getPnlUponScrollPane().add(subPanelItemPreview);
        }
    }
}
