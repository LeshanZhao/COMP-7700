
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class SearchView extends JFrame
{
    private  Vector _itemsInfo = new Vector();
    private  Vector _itemsImgs = new Vector();
    private  int _itemsCount = 0;
    private SearchInputBoxPanel _pnlSearchInputBox = new SearchInputBoxPanel();
    public SearchInputBoxPanel getPnlSearchInputBox()
    {
        return _pnlSearchInputBox;
    }
    private static SearchResultPanel _pnlSearchResult;
    public SearchResultPanel getSearchResultPanel()
    {
        return  _pnlSearchResult;
    }
    private ItemDetailPanel _pnlDetail = new ItemDetailPanel(this);
    public ItemDetailPanel getPnlItemDetail()
    {
        return  _pnlDetail;
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
    public SearchView()
    {
        this.setTitle("Online Shopping Product Search");
        this.setBounds(180, 5, 980, 680);
        this.setVisible(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        _pnlSearchResult = new SearchResultPanel(this);
//        controller_searchResult = new SearchResultController(this, panel_Result, panel_Detail);

        /* ================= Add all panels to the frame ================= */
        this.add(_pnlSearchInputBox);
        this.add(_pnlSearchResult);
        this.add(_pnlDetail);
        this.add(_pnlDetail.getPnlDetailPayment());
    }
}
