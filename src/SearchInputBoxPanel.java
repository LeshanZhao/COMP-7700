import javax.swing.*;

public class SearchInputBoxPanel extends JPanel
{
    String[] Categories = {"All", "Books", "Clothing", "Electronics", "Food", "Grocery", "Household", "Kids", "Medicine", "Outdoors", "Pet Supplies", "Sports", "Others"};
    private JComboBox<String> _cboCategory = new JComboBox<>(Categories);
    private JTextField _txtSearchDescription = new JTextField(30);
    private JLabel _lblSsearchResultHint = new JLabel();
    private JButton _btnSearch = new JButton("Search");
    private JButton _btnBack = new JButton("Back");

    public JComboBox<String> getComboBoxCategory()
    {
        return _cboCategory;
    }
    public void setComboBoxCategory(JComboBox<String> cboCategory)
    {
        _cboCategory = cboCategory;
    }
    public JLabel getResultHint() { return _lblSsearchResultHint; }
    public String getTxtSearchDescription() {return _txtSearchDescription.getText(); }
    public void clearTxtSearchDescription()
    {
        _txtSearchDescription.setText("");
    }

    public void ResetSearchInputBoxPanel()
    {
        getComboBoxCategory().setSelectedIndex(0);
        clearTxtSearchDescription();
        getComboBoxCategory().setFocusable(true);
    }
    public String getSelectedCategory()
    {
        return _cboCategory.getItemAt(_cboCategory.getSelectedIndex());
    }
    public JButton getBtnSearch()
    {
        return _btnSearch;
    }
    public JButton getBtnBack()
    {
        return _btnBack;
    }
    public SearchInputBoxPanel()
    {
        // this.setBorder(BorderFactory.createTitledBorder("panelSearchBorder"));
        this.setLayout(null);
        this.setBounds(0, 0, 960, 110);

        _cboCategory.setBounds(80, 50, 100, 30);
        this.add(_cboCategory);

        _txtSearchDescription.setBounds(180, 50, 600, 30);
        this.add(_txtSearchDescription);

        _btnSearch.setBounds(800, 50, 100, 30);
        this.add(_btnSearch);

        _lblSsearchResultHint.setBounds(90, 50, 380, 80);
        this.add(_lblSsearchResultHint);
    }
}