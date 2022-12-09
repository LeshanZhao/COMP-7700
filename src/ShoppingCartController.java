import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShoppingCartController implements ActionListener
{
    private DataAdapter _dataAdapter;
    private ShoppingCartView _shoppingCartView;

    public ShoppingCartController(DataAdapter dataAdapter, ShoppingCartView shoppingCartView)
    {
        _dataAdapter = dataAdapter;
        _shoppingCartView = shoppingCartView;

        _shoppingCartView.getBtnBack().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == _shoppingCartView.getBtnBack())
        {
            System.out.println("Shopping Cart View go back button action listener");
            _shoppingCartView.setVisible(false);
            Application.getInstance().getPnlSearchResult().setVisible(true);
        }
    }
}
