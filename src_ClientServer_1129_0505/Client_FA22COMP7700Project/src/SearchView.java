
import javax.swing.*;
import java.util.Vector;

public class SearchView extends JFrame
{
    private  Vector _itemsInfo = new Vector();
    private  Vector _itemsImgs = new Vector();
    private  int _itemsCount = 0;
    private static SearchResultPanel _pnlSearchResult;
    private SearchInputBoxPanel _pnlSearchInputBox = new SearchInputBoxPanel();
    private ItemDetailPanel _pnlDetail;

    public SearchInputBoxPanel getPnlSearchInputBox()
    {
        return _pnlSearchInputBox;
    }
    public SearchResultPanel getSearchResultPanel()
    {
        return  _pnlSearchResult;
    }
    //    private ItemDetailPanel _pnlDetail;
    //    public ItemDetailPanel getPnlItemDetail()
    //    {
    //        return  _pnlDetail;
    //    }
    public ItemDetailPanel getPnlItemDetail()
    {
        return _pnlDetail;
    }

    public Vector getItemsInfo()
    {
        return _itemsInfo;
    }
    public void setItemsInfo(Vector itemsInfo)
    {
        _itemsInfo = itemsInfo;
    }

    public Vector getItemsImgs()
    {
        return _itemsImgs;
    }
    public void setItemsImgs(Vector itemsImgs)
    {
        _itemsImgs = itemsImgs;
    }
    public int getItemsCount()
    {
        return _itemsCount;
    }

    public void setItemsCount(int itemsCount)
    {
        _itemsCount = itemsCount;
    }
    public SearchView(){
        this.setTitle("Online Shopping Product Search");
        this.setBounds(180, 5, 980, 680);
        this.setVisible(false);
        this.setLayout(null);

        /* ================= Panel for the searching input Box ================= */


        /* ================= Detail Information Page for the first Item ================= */
        _pnlDetail = new ItemDetailPanel(this);

        /* ================= Panel Containing all the searching results ================= */
        _pnlSearchResult = new SearchResultPanel(this);
//        controller_searchResult = new SearchResultController(this, panel_Result, panel_Detail);

        /* ================= Add all panels to the frame ================= */
        this.add(_pnlSearchInputBox);
        this.add(_pnlSearchResult);
        this.add(_pnlDetail);
        this.add(_pnlDetail.getPnlDetailPayment());
    }
}
