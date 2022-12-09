import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductController implements ActionListener
{
    private ProductMaintenanceView _productMaintView;
    private DataAdapter _dataAdapter;
    private ArrayList<Product> _productList = new ArrayList<Product>();

    public ProductController(ProductMaintenanceView productMaintView, DataAdapter dataAdapter)
    {
        this._dataAdapter = dataAdapter;
        this._productMaintView = productMaintView;

        _productMaintView.getBtnLoadAll().addActionListener(this);
        _productMaintView.getBtnLoad().addActionListener(this);
        _productMaintView.getBtnSave().addActionListener(this);
        _productMaintView.getBtnSaveAll().addActionListener(this);
        _productMaintView.getBtnProductOptions().addActionListener(this);

        _productMaintView.getRdoAdd().addActionListener(this);
        _productMaintView.getRdoView().addActionListener(this);
        _productMaintView.getRdoUpdate().addActionListener(this);
        _productMaintView.getRdoDelete().addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == _productMaintView.getBtnLoad())
        {
            try
            {
                loadProduct();
            }
            catch (SQLException ex)
            {
                JOptionPane.showMessageDialog(null, "Load Product Error with Database.");
            }
        }
        else if(e.getSource() == _productMaintView.getBtnLoadAll())
        {
            try
            {
                loadAllProducts();
            }
            catch (SQLException ex)
            {
                JOptionPane.showMessageDialog(null, "Load Product List Error with Database.");
            }
        }
        else if(e.getSource() == _productMaintView.getBtnSave())
        {
            saveProduct();
        }
        else if(e.getSource() == _productMaintView.getBtnSaveAll())
        {
            saveAllProduct();
        }
        else if(e.getSource() == _productMaintView.getBtnProductOptions())
        {

        }
    }

    private void saveAllProduct()
    {
    }

    private void saveProduct()
    {
    }

    private void loadAllProducts() throws SQLException
    {
        _productList = _dataAdapter.GetAllProducts();
        if(_productList == null)
        {
            JOptionPane.showMessageDialog(null, "There were no products in the database!");
            return;
        }
        _productMaintView.getBtnSave().setEnabled(false) ; // figure out how to load the combo box here and move
    }                                                          // through the list with the next button

    private void loadProduct() throws SQLException
    {
        int productID = 0;
        try
        {
            productID = Integer.parseInt(_productMaintView.getTxtProductID().getText());
        }
        catch (NumberFormatException numberFormatException)
        {
            JOptionPane.showMessageDialog(null, "Invalid product ID! Please provide a valid product ID!");
            return;
        }

        Product product = _dataAdapter.GetProductByID(productID);

        if (product == null)
        {
            JOptionPane.showMessageDialog(null, "This product ID does not exist in the database!");
            return;
        }

        FillProduct(product);
    }

    private void FillProduct(Product product)
    {
        _productMaintView.getTxtProductName().setText(product.getProductName());
        _productMaintView.getTxtaProductDescription().setText(product.getProductDescription());
        _productMaintView.getTxtQuantity().setText(String.valueOf(product.getQuantityOnHand()));
        _productMaintView.getTxtDateAdded().setText(product.getDateAdded());
        _productMaintView.getChbxActive().setSelected(product.getActive() == 1);
        _productMaintView.getTxtDateModified().setText(product.getDateModified());
        _productMaintView.getTxtPrice().setText(String.valueOf(product.getPrice()));

    }
}