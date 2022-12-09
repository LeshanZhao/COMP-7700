import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class SearchController implements ActionListener
{

    private DataAdapter _dataAdapter;
    private RemoteDataAdapter _remote_dataAdapter;
    private SearchView _searchView;
    private List<Product> _productResultList;
    public List<Product> getProductResultList()
    {
        return _productResultList;
    }

    public  SearchController(SearchView searchView, RemoteDataAdapter remote_dataAdapter)
    {
        _remote_dataAdapter = remote_dataAdapter;
        _searchView = searchView;

        _searchView.getPnlSearchInputBox().getBtnSearch().addActionListener(this);
        _searchView.getPnlSearchInputBox().getBtnBack().addActionListener(this);
        _searchView.getSearchResultPanel().getBtnViewDetail().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == _searchView.getPnlSearchInputBox().getBtnSearch())
        {
            try
            {
                String category = _searchView.getPnlSearchInputBox().getComboBoxCategory().getSelectedItem().toString();
                String searchString = _searchView.getPnlSearchInputBox().getTxtSearchDescription();
                //Types categoryType = _dataAdapter.GetTypesByTypeValue(category);
                _remote_dataAdapter.connect();
                _productResultList = _remote_dataAdapter.GetAllProductByCategoryAndSearchString(category, searchString);
//                LoadSearchResultPanel(_productResultList);
                if (_productResultList.size() == 0){
                    JOptionPane.showMessageDialog(null, "No item was found, please try other keywords");
                }
                else {
                    JOptionPane.showMessageDialog(null, _productResultList.size() + " item(s) was (were) found.");
                    _searchView.getSearchResultPanel().LoadSearchResultPanel(_productResultList);
                }

                Application.getInstance().getSearchView().getSearchResultPanel().setVisible(true);
            }
            catch(SQLException sqlException)
            {
                System.out.println("Search Controller - btnSearch - Error occurred while getting category and searchString");
                System.out.println("SQLException Message " + sqlException.getMessage());
                System.out.println("SQLException Stacktrace " + sqlException.getStackTrace());
            }
            catch(Exception e1)
            {
                System.out.println("remote dataAdaptor - Error occurred while fetching remote data");
                System.out.println("Exception Message " + e1.getMessage());
                System.out.println("Exception Stacktrace " + e1.getStackTrace());
            }
        }
        if(e.getSource() == _searchView.getPnlSearchInputBox().getBtnBack())
        {
            _searchView.setVisible(false);
            //Application.getInstance().getPriorView().setVisible(true);
        }
        if(e.getSource() == _searchView.getSearchResultPanel().getBtnViewDetail())
        {
            System.out.println("Button \"View Detail\" for some item is clicked.");
            System.out.println(_searchView.getSearchResultPanel().getBtnViewDetail().getName());
            _searchView.getSearchResultPanel().setVisible(false);
            _searchView.getPnlItemDetail().setVisible(true);
            _searchView.getPnlItemDetail().getPnlDetailPayment().setVisible(true);
        }
    }


}
