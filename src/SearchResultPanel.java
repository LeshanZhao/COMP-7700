import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SearchResultPanel extends JPanel
{

    private JPanel _pnlUponScrollPane = new JPanel();

    public JPanel getPnlUponScrollPane()
    {
        return  _pnlUponScrollPane;
    }
    private JButton _btnViewDetail = new JButton("View Detail");
    public JButton getBtnViewDetail()
    {
        return _btnViewDetail;
    }
    public void setBtnViewDetail(JButton btnViewDetail)
    {
        _btnViewDetail = btnViewDetail;
    }
    private SearchView _searchView;

    private SearchResultController _searchResultController;

     public SearchResultPanel(SearchView searchView)
    {
        // Initialization
        _searchView = searchView;
        /* ================= This is the Panel Containing all the searching results ================= */
        this.setBackground(Color.lightGray);
        this.setBorder(new EmptyBorder(0, 10, 0, 10));
        this.setBounds(60, 110, 900, 900);
        this.setBorder(BorderFactory.createTitledBorder("Search Results"));
        this.setVisible(false);
        _searchView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        // Create a Scroll Pane to display all product items.
        // (In Swing, an Extra panel is required for ScrollPane to include multiple components)
        JScrollPane scrollPaneResults = new JScrollPane();
        scrollPaneResults.setBounds(15, 20, 875, 875);
        //scrollPaneResults.setBorder(BorderFactory.createTitledBorder("ScrollPane (displaying for debug)"));
        this.add(scrollPaneResults, BorderLayout.CENTER);
        // Create the extra panel to contain all the sub-panels for each product item

        _pnlUponScrollPane.setLayout(new GridLayout(searchView.getItemsCount(), 1, 10, 10));
        _pnlUponScrollPane.setBackground(Color.lightGray);
        //_pnlUponScrollPane.setBorder(BorderFactory.createTitledBorder("Panel for subPanels Area (displaying for debug)"));
        scrollPaneResults.setViewportView(_pnlUponScrollPane);
        scrollPaneResults.setVerticalScrollBar(new JScrollBar());
    }
}