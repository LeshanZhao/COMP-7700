import javax.swing.*;
import java.awt.*;

public class RegisterViewScreen extends JFrame
{
    private JButton _btnBack = new JButton("Back");
    public JButton getBtnBack()
    {
        return _btnBack;
    }
    public RegisterViewScreen()
    {
        this.setBounds(50, 5, 1000, 500);
        this.setTitle("Online Shopping Account Registration");
        this.setFont(new Font("Verdana", Font.BOLD, 18));

        JPanel pnlNotImplemented = new JPanel();

        JLabel lblNotImplemented = new JLabel();
        lblNotImplemented.setText("This page is still under construction!");
        lblNotImplemented.setForeground(Color.RED);
        lblNotImplemented.setFont(new Font("Verdana", Font.BOLD, 18));
        pnlNotImplemented.add(lblNotImplemented);

        JPanel pnlButton = new JPanel();
        pnlButton.setBounds(110, 100, 100, 100);
        pnlButton.setFont(new Font("Verdana",Font.PLAIN, 14));
        pnlButton.add(_btnBack);

        this.getContentPane().add(BorderLayout.NORTH,pnlNotImplemented);
        this.getContentPane().add(BorderLayout.SOUTH,pnlNotImplemented);
    }
}
