import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterController implements ActionListener
{
    private RegisterViewScreen _registerViewScreen;
    private DataAdapter _dataAdapter;
    public RegisterController(RegisterViewScreen registerViewScreen, DataAdapter dataAdapter)
    {
        _registerViewScreen = registerViewScreen;
        _dataAdapter = dataAdapter;

        _registerViewScreen.getBtnBack().addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == _registerViewScreen.getBtnBack())
        {
            _registerViewScreen.setVisible(false);
            Application.getInstance().getLoginScreenView().setVisible(true);
        }
    }
}