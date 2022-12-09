import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class ItemDetailController implements ActionListener
{

    private SearchView _searchView;
    private DataAccess _remote_dataAdapter;
    private ArrayList<Product> _selectedProductList = new ArrayList<Product>();
    private ArrayList<Orders> _orderList = new ArrayList<Orders>();

    public ItemDetailController(SearchView searchView, RemoteDataAdapter remoteDataAdapter)
    {
        _searchView = searchView;
        _remote_dataAdapter = remoteDataAdapter;

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
//            _searchView.getPnlItemDetail().removeAll();
            _searchView.getSearchResultPanel().setVisible(true);
        }
        else if(e.getSource() == _searchView.getPnlItemDetail().getButtonDetailBuy())
        {
            System.out.println("inside buy now button action listener");
            Product p = _searchView.getPnlItemDetail().getProductSelected();
//            String productIDString = _searchView.getPnlItemDetail().getButtonDetailBuy().getName();
//            int productID = Integer.parseInt(productIDString);
//            try
//            {
//                p = _remote_dataAdapter.GetProductByID(productID);
//            }
//            catch(SQLException sqlException)
//            {
//                System.out.println("Item Detail Button BuyNow: Error obtaining Product by ID");
//            }
            if(p != null)
            {
                System.out.println("buy now clicked for the product with id = " + p.getProductID());

                String confirmationNumber = GetConfirmationNumber();
                JOptionPane.showMessageDialog(null, "Your Order has been submitted.\r\n" +
                        "You bought a product \"" + p.getProductName() + "\" successfully.\r\n" +
                        "Your confirmation number is " + confirmationNumber + "\r\n" +
                        "(Payment part is being implemented)");


            }
            //CheckoutProcess
        }
        else if(e.getSource() == _searchView.getPnlItemDetail().getButtonDetailShoppingCart())
        {
            _searchView.getPnlItemDetail().setVisible(false);

            JOptionPane.showMessageDialog(null,"The payment function is being implemented.");
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
