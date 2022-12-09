import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginController implements ActionListener
{
    //private SearchView _searchView = new SearchView();
    private DataAdapter _dataAdapter;
    private LoginScreenView _loginScreenView;

    public LoginController(LoginScreenView loginScreenView, DataAdapter dataAdapter)
    {
       _dataAdapter = dataAdapter;
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

                Users loginUser = _dataAdapter.GetUserWithUsernamePassword(username, password);
                try
                {
                    UserRole loginUserRole = _dataAdapter.GetUserRoleWithUserRoleID(loginUser.getUserRoleID());
                    Application.getInstance().setCurrentUser(loginUser);

                    _loginScreenView.setVisible(false);
                    if (loginUserRole.getRoleName().contains("SuperUser"))
                    {
                        _loginScreenView.setVisible(false);
                        //open SuperUser View
                        //Application.getInstance().getSuperUserView().setVisible(true);
                        Application.getInstance().getSearchView().setVisible(true);
                    } else if (loginUserRole.getRoleName().contains("Buyer"))
                    {
                        //open buyerView
                        Application.getInstance().setCurrentUser(loginUser);
                        _loginScreenView.setVisible(false);
                        Application.getInstance().getSearchView().setVisible(true);
                    } else if (loginUserRole.getRoleName().contains("Seller"))
                    {
                        //open sellerView
                        Application.getInstance().setCurrentUser(loginUser);
                        _loginScreenView.setVisible(false);
                        System.out.println("In the seller button section");
                        //Application.getInstance().getSellerView().setVisible(true);
                    } else
                    {
                        Application.getInstance().setCurrentUser(loginUser);
                        //open Searchview
                    }
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "Username and/or password is invalid");
                    _loginScreenView.getTxtUserName().transferFocus();
                }
            }
            catch(SQLException sqlException)
            {
                JOptionPane.showMessageDialog(null, "Username and/or password is invalid.");
                System.out.println("btnLogin - Username and/or password is invalid");
            }
        }
        if(e.getSource() == _loginScreenView.getBtnBack())
        {
            _loginScreenView.setVisible(false);
            Application.getInstance().getWelcomeView().setVisible(true);
        }
    }
}
//            Application.getInstance().setCurrentUser(loginUser);
//            this.loginScreen.setVisible(false);
//            Application.getInstance().getMainScreen().setVisible(true);