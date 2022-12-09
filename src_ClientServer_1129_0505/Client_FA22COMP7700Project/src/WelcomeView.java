import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static javax.imageio.ImageIO.read;

public class WelcomeView extends JFrame
{
    private WelcomeController _welcomeController;
    private JPanel _pnlWelcome = new JPanel();
    private JLabel _lblWelcomeSign;
    private JLabel _currentUser;
    private JButton _btnLogin =new JButton("Login");;
    private JButton _btnRegister = new JButton("Register");;
    private JButton _btnExit = new JButton("Exit");
    private JButton _btnGuest = new JButton("Guest");
    //private JButton _btnSeller = new JButton("Seller");
    public JButton getBtnLogin()
    {
        return _btnLogin;
    }
    public JButton getBtnRegister()
    {
        return _btnRegister;
    }
    public JButton getBtnExit()
    {
        return _btnExit;
    }
    public JButton getBtnGuest() {return _btnGuest;}
    /*public JButton getBtnSeller()
    {
        return _btnSeller;
    }*/
    public WelcomeController getWelcomeController()
    {
        return _welcomeController;
    }
    public WelcomeView()
    {
        this.setBounds(50, 5, 1000, 500);
        this.setVisible(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        _lblWelcomeSign = new JLabel("Welcome to Online Shopping System");
        _lblWelcomeSign.setFont(new Font("Verdana", Font.BOLD, 32));
        _lblWelcomeSign.setHorizontalAlignment(SwingConstants.CENTER);
        _lblWelcomeSign.setHorizontalTextPosition(SwingConstants.CENTER);
        _lblWelcomeSign.setVerticalAlignment(SwingConstants.CENTER);
        _lblWelcomeSign.setVerticalTextPosition(SwingConstants.CENTER);
        _lblWelcomeSign.setForeground(Color.RED);
        _lblWelcomeSign.setBackground(Color.blue);
        _lblWelcomeSign.setVisible(true);


        String courseInfo = "<html>" + "<br>" +
                "COMP 7700" + "<br>" +"<br>" +
                "Software Architecture" + "<br>" +"<br>" +
                "Fall 2022" + "<br>" +"<br>" +
                "Dr. Tung Nguyen" + "<br>" +"<br>" +
                "</html>";
        JPanel coursePanel = new JPanel();
        coursePanel.setBorder(BorderFactory.createTitledBorder("Course Info"));
        coursePanel.setLayout(new GridLayout(0,1));
        JLabel lblCourseInfo = new JLabel(courseInfo);
        lblCourseInfo.setFont(new Font("Verdana", Font.BOLD, 14));
        lblCourseInfo.setForeground(Color.BLUE);
        coursePanel.add(lblCourseInfo);


        JPanel imagePanel = new JPanel();
        imagePanel.setBorder(BorderFactory.createTitledBorder("Image"));
        try
        {
            //BufferedImage systemLogo = read(new File("C:\\Users\\djackson\\Pictures\\AmericanFlagEagle1.jpg"));
            BufferedImage systemLogo = read(new File("src/imgs/OnlineShoppingLogo.jpg"));
            JLabel lblSystemLogo = new JLabel(new ImageIcon(systemLogo));
            lblSystemLogo.setSize(200, 100);
            imagePanel.add(lblSystemLogo);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        String teamMembers = "<html>" +"<br>" +
                "LeShan Zhao" + "<br>" +"<br>" +
                "Ravindra Joshi" + "<br>" +"<br>" +
                "Mehnaz Tabassum" + "<br>" +"<br>" +
                "Donna Jackson" + "<br>" +"<br>" +
                "</html>";
        JPanel teamPanel = new JPanel();
        teamPanel.setBorder(BorderFactory.createTitledBorder("Team Members"));
        teamPanel.setLayout(new GridLayout(0,1));
        JLabel lblTeamMembers = new JLabel(teamMembers);
        lblTeamMembers.setFont(new Font("Verdana", Font.BOLD, 14));
        lblTeamMembers.setForeground(Color.GREEN);
        teamPanel.add(lblTeamMembers);

        JPanel btnPanel = new JPanel();

        _btnGuest.setFont(new Font("Verdana", Font.PLAIN, 12));
        _btnGuest.setBounds(20, 100, 100, 100);
        _btnGuest.setEnabled(true);

        /*_btnSeller.setFont(new Font("Verdana", Font.PLAIN, 12));
        _btnSeller.setBounds(50, 100, 100, 100);
        _btnSeller.setEnabled(true);*/

        _btnLogin.setFont(new Font("Verdana", Font.PLAIN, 12));
        _btnLogin.setBounds(80, 100, 100, 100);
        _btnLogin.setEnabled(true);

        _btnRegister.setFont(new Font("Verdana",Font.PLAIN, 12));
        _btnRegister.setBounds(110, 100, 100, 100);
        _btnRegister.setEnabled(true);

//        _btnGuest.setVisible(false);
//        _btnSeller.setVisible(false);
//        _btnLogin.setVisible(false);
//        _btnRegister.setVisible(false);

        _btnLogin.setVisible(true);
        _btnRegister.setVisible(true);
        _btnGuest.setVisible(true);
        //_btnSeller.setVisible(true);


        _btnExit.setFont(new Font("Verdana", Font.PLAIN, 12));
        _btnExit.setBounds(140, 100, 100, 100);
        _btnExit.setVisible(true);
        _btnExit.setEnabled(true);

        btnPanel.add(_btnGuest);
        //btnPanel.add(_btnSeller);
        btnPanel.add(_btnLogin);
        btnPanel.add(_btnRegister);
        btnPanel.add(_btnExit);

        this.getContentPane().add(BorderLayout.NORTH,_lblWelcomeSign);
        this.getContentPane().add(BorderLayout.WEST, coursePanel);
        this.getContentPane().add(BorderLayout.CENTER, imagePanel);
        this.getContentPane().add(BorderLayout.EAST, teamPanel);
        this.getContentPane().add(BorderLayout.SOUTH, btnPanel);
    }
}
