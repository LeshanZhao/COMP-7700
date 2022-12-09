
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ProductMaintenanceView extends JFrame
{
    private ArrayList<Product> _productList = new ArrayList<Product>();
    private JPanel _pnlProduct;
    private ButtonGroup _grpEditMode;
    private JRadioButton _rdoView;
    private JRadioButton _rdoAdd;
    private JRadioButton _rdoUpdate;
    private JRadioButton _rdoDelete;
    private JTextField _txtProductID;
    private JLabel _lblProductID;
    private JTextField _txtProductName;
    private JLabel _lblProductName;
    private JTextArea _txtaProductDescription;
    private JLabel _lblProductDescription;
    private JTextField _txtQuantity;
    private JTextField _txtPrice;
    private JLabel _lblQuantity;
    private JLabel _lblPrice;
    private JCheckBox _chbxActive;
    private JTextField _txtDateAdded;
    private JTextField _txtDateModified;
    private JLabel _lblDateAdded;
    private JLabel _lblDateModified;
    private JButton _btnProductOptions;
    private JButton _btnSave;
    private JButton _btnLoad;
    private JButton _btnLoadAll;
    private JScrollPane _scpnProductDescription;
    private JButton _btnSaveAll;
    private JComboBox _cbxProductName;
    private JComboBox _cbxProductID;
    private JLabel _lblProductImage;


    public JButton getBtnLoadAll()
    {
        return _btnLoadAll;
    }

    public JButton getBtnLoad()
    {
        return _btnLoad;
    }

    public JButton getBtnSave()
    {
        return _btnSave;
    }

    public JButton getBtnProductOptions()
    {
        return _btnProductOptions;
    }

    public JButton getBtnSaveAll()
    {
        return _btnSaveAll;
    }

    public ButtonGroup getGrpEditMode()
    {
        return _grpEditMode;
    }

    public JRadioButton getRdoView()
    {
        return _rdoView;
    }

    public JRadioButton getRdoAdd()
    {
        return _rdoAdd;
    }

    public JRadioButton getRdoUpdate()
    {
        return _rdoUpdate;
    }

    public JRadioButton getRdoDelete()
    {
        return _rdoDelete;
    }

    public JTextField getTxtProductID()
    {
        return _txtProductID;
    }

    public JTextField getTxtProductName()
    {
        return _txtProductName;
    }

    public JTextArea getTxtaProductDescription()
    {
        return _txtaProductDescription;
    }

    public JTextField getTxtQuantity()
    {
        return _txtQuantity;
    }

    public JTextField getTxtPrice()
    {
        return _txtPrice;
    }

    public JCheckBox getChbxActive()
    {
        return _chbxActive;
    }

    public JTextField getTxtDateAdded()
    {
        return _txtDateAdded;
    }

    public JTextField getTxtDateModified()
    {
        return _txtDateModified;
    }

    public JComboBox cbxProductName()
    {
        return _cbxProductName;
    }

    public ProductMaintenanceView()
    {
/*        this.setContentPane(_pnlProduct);
        this.setTitle("Product Maintenance View");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 300);
        setVisible(false);*/


    }
}