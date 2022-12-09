import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class CheckoutController implements ActionListener
{
    private Product _product = null;
    private DataAdapter _dataAdapter;
    private CheckoutView _checkoutView;


    public  CheckoutController(CheckoutView checkoutView, DataAdapter dataAdapter)
    {
        _dataAdapter = dataAdapter;
        _checkoutView = checkoutView;
        _product = _checkoutView.getProduct();

        _checkoutView.getBtnFinishAndPay().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {   Shipper shipper = _checkoutView.getShipper();
        ShipperMethod shipperMethod = _checkoutView.getShipperMethod();
        if (e.getSource() == _checkoutView.getBtnFinishAndPay())
        {
            if(saveOrder())
            {
                _checkoutView.setVisible(false);
                Application.getInstance().getSearchView().setVisible(true);
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Error saving Order.  Review order and try again.");
            }
        }
    }
    private boolean saveOrder()
    {
        if(_dataAdapter.SaveOrder(_checkoutView.getOrder()))
        {
            String confirmationNumber = GetConfirmationNumber();
            JOptionPane.showMessageDialog(null, "Your Order has been submitted.\r\n\r\n Confirmation number is " + confirmationNumber);
            return true;
        }
        return false;
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
