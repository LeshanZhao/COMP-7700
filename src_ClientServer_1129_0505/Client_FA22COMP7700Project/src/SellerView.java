import javax.swing.*;

public class SellerView extends JFrame
{
    private DataAdapter.EditMode _editMode;

    public DataAdapter.EditMode getEditMode()
    {
        return _editMode;
    }

    public SellerView()
    {
        this.setBounds(50, 5, 1000, 500);
        this.setVisible(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel sellerPanel = new JPanel();
        JPanel searchPanel = new JPanel();
    }
}
