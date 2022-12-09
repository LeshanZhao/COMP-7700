import javax.naming.directory.SearchResult;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchResultController implements ActionListener
{

    private SearchResultPanel _pnlSearchResult;
    private SearchView _searchView;
    private ItemDetailPanel _pnlItemDetail;


    public SearchResultController(SearchView searchView, SearchResultPanel pnlSearchResult, ItemDetailPanel pnlItemDetail)
    {
        System.out.println("Search result controller is initialized.");
        _searchView = searchView;
        _pnlSearchResult = pnlSearchResult;
        _pnlItemDetail = pnlItemDetail;
    }
    /* ================= Action on button "View Detail" for the first item clicked  ================= */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == _searchView.getSearchResultPanel().getBtnViewDetail())
        {
            System.out.println("SRC - Button \"View Detail\" for some item is clicked.");
            System.out.println(_searchView.getSearchResultPanel().getBtnViewDetail().getName());
            _searchView.getSearchResultPanel().setVisible(false);
            _searchView.getSearchResultPanel().getBtnViewDetail().setVisible(true);
            _searchView.getPnlItemDetail().setVisible(true);

//            this.itemDetailPanel.panel_Detail1.setVisible(true);
//            this.searchView.panel_Detail1.panelDetailPayment1.setVisible(true);
        }

    }
}