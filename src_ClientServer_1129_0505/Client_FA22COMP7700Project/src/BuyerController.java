import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuyerController implements ActionListener
{

    private DataAdapter _dataAdapter;
    private BuyerView _buyerView;

    public  BuyerController(BuyerView buyerView, DataAdapter dataAdapter)
    {
        _dataAdapter = dataAdapter;
        _buyerView = buyerView;

//        _buyerView.getBtnSearch().addActionListener(this);
//        _buyerView.getBtnBack().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }
}
