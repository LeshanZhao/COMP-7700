import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeController implements ActionListener
{
//    DataAccess _remote_dataAdapter;
    WelcomeView _welcomeView;


    public WelcomeController(WelcomeView welcomeView)
    {
        //System.out.println("Started Welcome Controller Constructor");
        _welcomeView = welcomeView;
//        _remote_dataAdapter = remote_dataAdapter;
        //System.out.println("adding action listeners to the buttons");
        _welcomeView.getBtnLogin().addActionListener( this);
        _welcomeView.getBtnRegister().addActionListener(this);
        _welcomeView.getBtnExit().addActionListener(this);
        //Application.getInstance().setPriorView(_welcomeView);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == _welcomeView.getBtnLogin())
        {
            _welcomeView.setVisible(false);
            Application.getInstance().getLoginScreenView().setVisible(true);;
        }
        if(e.getSource() == _welcomeView.getBtnRegister())
        {
            _welcomeView.setVisible(false);
            Application.getInstance().getRegisterView().setVisible(true);
        }
        if(e.getSource() == _welcomeView.getBtnGuest())
        {
            _welcomeView.setVisible(false);
            Application.getInstance().getSearchView().setVisible(true);
        }
        if(e.getSource() == _welcomeView.getBtnExit())
        {
            System.exit(0);
        }
    }
}
