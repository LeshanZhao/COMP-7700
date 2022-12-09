import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class LoginScreenView extends JFrame
{
    private DataAdapter _dataAdapter;
    private JPanel _pnlLoginView = new JPanel();
    private JLabel _lblTitle;
    private JTextField _txtUserName = new JTextField(12);
    private JPasswordField _txtPassword = new JPasswordField(12);
    private JButton _btnLogin = new JButton("Login");
    private JButton _btnBack = new JButton("Back");

    public JPanel getPnlLoginView()
    {
        return _pnlLoginView;
    }
    public JLabel getLblTitle()
    {
        return _lblTitle;
    }
    public JTextField getTxtUserName()
    {
        return _txtUserName;
    }
    public JPasswordField getTxtPassword()
    {
        return _txtPassword;
    }
    public JButton getBtnLogin() { return _btnLogin; }
    public JButton getBtnBack() {return _btnBack;}

    public LoginScreenView()
    {
        _pnlLoginView = new JPanel();
        this.setBounds(50, 5, 800, 400);
        this.setVisible(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        _lblTitle = new JLabel("Online Shopping System Login Screen");
        _lblTitle.setFont(new Font("Verdana", Font.BOLD, 32));
        _lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        _lblTitle.setHorizontalTextPosition(SwingConstants.CENTER);
        _lblTitle.setVerticalAlignment(SwingConstants.TOP);
        _lblTitle.setVerticalTextPosition(SwingConstants.TOP);
        _lblTitle.setForeground(Color.RED);
        _lblTitle.setBackground(Color.blue);
        _lblTitle.setVisible(true);

        JPanel pnlLogin = new JPanel();
        pnlLogin.setLayout(new GridLayout(0,1));

        JPanel pnlUsername = new JPanel();
        pnlUsername.setBounds(50,25,100,50);
        pnlUsername.add(new JLabel("Username: "));
        _txtUserName.setEditable(true);
        _txtUserName.setFont(new Font("Verdana", Font.PLAIN, 14));
        _txtUserName.setSize(55, 30);
        _txtUserName.setAlignmentX(Component.CENTER_ALIGNMENT);
        _txtUserName.setAlignmentY(Component.CENTER_ALIGNMENT);
        pnlUsername.add(_txtUserName);

        JPanel pnlPassword = new JPanel();
        pnlPassword.add(new JLabel("Password: "));
        pnlPassword.add(_txtPassword);
        _txtPassword.setEditable(true);
        _txtPassword.setFont(new Font("Verdana", Font.PLAIN, 14));
        _txtPassword.setSize(55, 30);
        pnlLogin.add(pnlUsername);
        pnlLogin.add(pnlPassword);


        JPanel pnlButtons = new JPanel();
        _btnLogin.setFont(new Font("Verdana", Font.BOLD, 12));
        _btnLogin.setMnemonic(KeyEvent.VK_ENTER);
        _btnBack.setFont(new Font("Verdana", Font.BOLD, 12));
        _btnBack.setMnemonic(KeyEvent.VK_ESCAPE);
        pnlButtons.add(_btnLogin);
        pnlButtons.add(_btnBack);

        this.getContentPane().add(BorderLayout.NORTH,_lblTitle);
        this.getContentPane().add(BorderLayout.CENTER, pnlLogin);
        this.getContentPane().add(BorderLayout.SOUTH, pnlButtons);
    }

}
