import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDetailController implements ActionListener
{

    private SearchView _searchView;
    private DataAdapter _dataAdapter;
    private ArrayList<Product> _selectedProductList = new ArrayList<Product>();
    private ArrayList<Orders> _orderList = new ArrayList<Orders>();

    public ItemDetailController(SearchView searchView, DataAdapter dataAdapter)
    {
        _searchView = searchView;
        _dataAdapter = dataAdapter;

        _searchView.getPnlItemDetail().getBtnBack().addActionListener(this);
        _searchView.getPnlItemDetail().getButtonDetailBuy().addActionListener(this);
        _searchView.getPnlItemDetail().getButtonDetailShoppingCart().addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == _searchView.getPnlItemDetail().getBtnBack())
        {
            _searchView.getPnlItemDetail().setVisible(false);
            //_searchView.getPnlItemDetail().ClearAllComponents();
            _searchView.getPnlItemDetail().removeAll();
            _searchView.getSearchResultPanel().setVisible(true);
        }
        else if(e.getSource() == _searchView.getPnlItemDetail().getButtonDetailBuy())
        {
            System.out.println("inside buy now button action listener");
            Product p = null;
            String productIDString = _searchView.getPnlItemDetail().getButtonDetailBuy().getName();
            int productID = Integer.parseInt(productIDString);
            try
            {
                p = _dataAdapter.GetProductByID(productID);
            }
            catch(SQLException sqlException)
            {
                System.out.println("Item Detail Button BuyNow: Error obtaining Product by ID");
            }
            if(p != null)
            {
                CheckoutView checkoutView = Application.getInstance().getCheckoutView();
                checkoutView.Load_Product(p);
                checkoutView.setVisible(true);
            }
            //CheckoutProcess
        }
        else if(e.getSource() == _searchView.getPnlItemDetail().getButtonDetailShoppingCart())
        {
            _searchView.getPnlItemDetail().setVisible(false);

            //JOptionPane.showMessageDialog(null,"This is function is being implemented.");
            Application.getInstance().getShoppingCartView().setVisible(true);
            return;
        }
        else if(e.getSource() == _searchView.getPnlItemDetail().getBtnContinueShopping())
        {
            JOptionPane.showMessageDialog(null,"This is function is being implemented.");
            return;
        }
        else if(e.getSource() == _searchView.getPnlItemDetail().getBtnFinishAndPay())
        {
            //CheckoutProcess
            Application.getInstance().getCheckoutView().setVisible(true);
        }
    }
}
