import javax.swing.*;
import java.awt.*;

public class SuperUserView extends JFrame
{

    private JPanel _pnlSuperUser = new JPanel();
    private JLabel _lblSuperUser = new JLabel("Super User Dashboard");
    private JButton _btnBuyer = new JButton("Buyer view");
    private JButton _btnSeller = new JButton("Seller View");
    private JButton _btnProductMaintenance = new JButton("Product Maintenance View");
    private JButton _btnSupplierMaintenance = new JButton("Supplier Maintenance View");
    private JButton _btnShipperMaintenance = new JButton("Shipper Maintenance View");

    public JButton getBtnBuyer()
    {
        return _btnBuyer;
    }
    public JButton getBtnSeller()
    {
        return _btnSeller;
    }
    public JButton getBtnProductMaintenance()
    {
        return _btnProductMaintenance;
    }
    public JButton getBtnShipperMaintenance()
    {
        return _btnShipperMaintenance;
    }
    public JButton getBtnSupplierMaintenance()
    {
        return _btnSupplierMaintenance;
    }

    public SuperUserView()
    {
        this.setTitle("Online Shopping Super User Dashboard");
        this.setBounds(180, 5, 980, 680);
        this.setLayout(null);
        this.setVisible(false);

        JPanel pnlButtons = new JPanel();
        pnlButtons.add(_btnBuyer);
        pnlButtons.add(_btnSeller);
        pnlButtons.add(_btnProductMaintenance);
        pnlButtons.add(_btnProductMaintenance);
        pnlButtons.add(_btnSupplierMaintenance);
        pnlButtons.add(_btnShipperMaintenance);




    }

}
