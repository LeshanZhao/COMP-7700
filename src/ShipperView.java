import javax.swing.*;
import java.awt.*;

public class ShipperView extends JFrame
{

    private JPanel _pnlShipping = new JPanel();
    private JLabel _lblTitle = new JLabel("Shipping Maintenance");


    public ShipperView()
    {

        this.getContentPane().add(BorderLayout.NORTH,_lblTitle);
    }
}
