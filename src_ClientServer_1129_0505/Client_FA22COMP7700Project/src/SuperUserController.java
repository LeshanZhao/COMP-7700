import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SuperUserController implements ActionListener
{
    private DataAdapter _dataAdapter;
    private SuperUserView _superUserView;

    public SuperUserController(SuperUserView superUserView, DataAdapter dataAdapter)
    {
        _superUserView = superUserView;
        _dataAdapter = dataAdapter;

        _superUserView.getBtnBuyer().addActionListener(this);
        _superUserView.getBtnProductMaintenance().addActionListener(this);
        _superUserView.getBtnSeller().addActionListener(this);
        _superUserView.getBtnShipperMaintenance().addActionListener(this);
        _superUserView.getBtnSupplierMaintenance().addActionListener(this);

    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == _superUserView.getBtnBuyer())
        {
            _superUserView.setVisible(false);
            //open Buyer View
            Application.getInstance().getSearchView().setVisible(true);
        }
        else if(e.getSource() == _superUserView.getBtnSeller())
        {
            _superUserView.setVisible(false);
            //open Seller View
            Application.getInstance().getSellerView().setVisible(true);
        }
        else if(e.getSource() == _superUserView.getBtnProductMaintenance())
        {
            _superUserView.setVisible(false);
            //open Product Maintenance View
            Application.getInstance().getProductMaintenanceView().setVisible(true);
        }
        else if(e.getSource() == _superUserView.getBtnShipperMaintenance())
        {
            _superUserView.setVisible(false);
            //open Product Maintenance View
            //Application.getInstance().getShipperView().setVisible(true);
        }
    }
}
