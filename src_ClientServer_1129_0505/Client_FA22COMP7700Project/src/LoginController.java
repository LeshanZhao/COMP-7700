import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginController implements ActionListener
{
    //private SearchView _searchView = new SearchView();
    private RemoteDataAdapter _remote_dataAdapter;
    private LoginScreenView _loginScreenView;

    public LoginController(LoginScreenView loginScreenView, RemoteDataAdapter remote_dataAdapter)
    {
       _remote_dataAdapter = remote_dataAdapter;
       _loginScreenView = loginScreenView;

       _loginScreenView.getBtnLogin().addActionListener(this);
       _loginScreenView.getBtnBack().addActionListener(this);
       //Application.getInstance().setPriorView(_loginScreenView);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == _loginScreenView.getBtnLogin())
        {
            try
            {
                //JOptionPane.showMessageDialog(null, "Login Screen View login button clicked");
                String username = _loginScreenView.getTxtUserName().getText().trim();
                String password = String.valueOf(_loginScreenView.getTxtPassword().getPassword());

                _remote_dataAdapter.connect();
                Users loginUser = _remote_dataAdapter.GetUserWithUsernamePassword(username, password);

                System.out.println("--------------- login user fetched successfully -----------");

                _remote_dataAdapter.connect();
                System.out.println("--------------- second connection constructed successfully -----------");

                UserRole loginUserRole = _remote_dataAdapter.GetUserRoleWithUserRoleID(loginUser.getUserRoleID());
                System.out.println("--------------- login user role fetched successfully -----------");

                Application.getInstance().setCurrentUser(loginUser);
                _loginScreenView.setVisible(false);
                if(loginUserRole.getRoleName().contains("SuperUser") )
                {
                    _loginScreenView.setVisible(false);
                    //open SuperUser View
                    //Application.getInstance().getSuperUserView().setVisible(true);
                    Application.getInstance().getSearchView().setVisible(true);
                }
                else if(loginUserRole.getRoleName().contains("Buyer"))
                {
                    //open buyerView
                    Application.getInstance().setCurrentUser(loginUser);
                    _loginScreenView.setVisible(false);
                    Application.getInstance().getSearchView().setVisible(true);
                }
                else if(loginUserRole.getRoleName().contains("Seller"))
                {
                    //open sellerView
                    Application.getInstance().setCurrentUser(loginUser);
                    _loginScreenView.setVisible(false);
                    System.out.println("In the seller button section");
                    //Application.getInstance().getSellerView().setVisible(true);
                }
                else
                {
                    Application.getInstance().setCurrentUser(loginUser);
                    //open Searchview
                }
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Something is not ready yet.");
                System.out.println("btnLogin - something is not ready yet");
                _loginScreenView.setVisible(true);
                ex.printStackTrace();
            }
        }
        if(e.getSource() == _loginScreenView.getBtnBack())
        {
            _loginScreenView.setVisible(false);
            //Application.getInstance().getWelcomeView().setVisible(true);
        }
    }
}
//            Application.getInstance().setCurrentUser(loginUser);
//            this.loginScreen.setVisible(false);
//            Application.getInstance().getMainScreen().setVisible(true);