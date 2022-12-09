import javax.swing.*;
import java.awt.*;

public class ShoppingCartView extends JFrame
{
    private JButton _btnBack = new JButton("Back");
    public JButton getBtnBack()
    {
        return _btnBack;
    }
    private JPanel _pnlButtons = new JPanel();
    public JPanel getPnlButtons()
    {
        return _pnlButtons;
    }


    public ShoppingCartView()
    {
        //JOptionPane.showMessageDialog(null, "Shopping Cart Feature to be implemented.");
        this.setBounds(50, 5, 800, 500);
        this.setVisible(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel pnlTitle = new JPanel();
        JLabel lblTitle = new JLabel();
        lblTitle = new JLabel("Online Shopping System Shopping Cart");
        lblTitle.setFont(new Font("Verdana", Font.BOLD, 32));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setHorizontalTextPosition(SwingConstants.CENTER);
        lblTitle.setVerticalAlignment(SwingConstants.TOP);
        lblTitle.setVerticalTextPosition(SwingConstants.TOP);
        lblTitle.setForeground(Color.RED);
        lblTitle.setVisible(true);
        pnlTitle.add(lblTitle);


        JPanel pnlLabelInfo = new JPanel();
        JLabel lblShoppingCartInfo1 = new JLabel();
        lblShoppingCartInfo1.setText("Shopping Cart feature to be implemented in the future release.");
        lblShoppingCartInfo1.setFont(new Font("Verdana",Font.BOLD,16));
        lblShoppingCartInfo1.setForeground(Color.RED);
        lblShoppingCartInfo1.setHorizontalAlignment(SwingConstants.CENTER);
        pnlLabelInfo.add(lblShoppingCartInfo1);

        JLabel lblShoppingCartInfo2 = new JLabel();
        lblShoppingCartInfo2.setText("Click the back button to return.");
        lblShoppingCartInfo2.setFont(new Font("Verdana",Font.BOLD,16));
        lblShoppingCartInfo2.setForeground(Color.RED);
        lblShoppingCartInfo2.setHorizontalAlignment(SwingConstants.CENTER);
        pnlLabelInfo.add(lblShoppingCartInfo2);
        this.getContentPane().add(BorderLayout.CENTER,pnlLabelInfo);

        _pnlButtons.add(_btnBack);
        this.getContentPane().add(BorderLayout.NORTH,pnlTitle);
        this.getContentPane().add(BorderLayout.CENTER,pnlLabelInfo);
        this.getContentPane().add(BorderLayout.SOUTH, _pnlButtons);

    }
}
