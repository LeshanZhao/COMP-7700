import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CheckoutView extends JFrame
{
    private DataAdapter _dataAdapter = null;
    private Product _product = null;
    public Product getProduct()
    {
        return _product;
    }
    public void setProduct(Product product)
    {
        _product = product;
    }
    private Users _currentUser = null;
    private JButton _btnFinishAndPay = new JButton("Finish and Pay");

    private DefaultTableModel _items = new DefaultTableModel(); // store information for the table!

    private JTable _tblItems = new JTable(_items); // null, new String[]{"ProductID", "Product Name", "Price", "Quantity", "Cost"});

    private JLabel _labTotal = new JLabel();
    private JPanel productPanel = new JPanel();
    public JButton getBtnFinishAndPay()
    {
        return _btnFinishAndPay;
    }

    public JLabel getLabTotal()
    {
        return _labTotal;
    }

    private ShipperMethod _shipperMethod;
    public ShipperMethod getShipperMethod()
    {
        return _shipperMethod;
    }
    private Shipper _shipper;
    public Shipper getShipper()
    {
        return _shipper;
    }
    private Orders _order = new Orders();;
    private OrderDetails _orderDetail = null;
    public Orders getOrder()
    {
        return _order;
    }
    public OrderDetails getOrderDetail()
    {
        return _orderDetail;
    }
    public void setOrderDetail(OrderDetails orderDetail)
    {
        _orderDetail = orderDetail;
    }


    private double orderTotal = 0.0;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    public CheckoutView()
    {
        this.setTitle("Online Shopping Buyer Checkout Screen");
        setBounds(180,5,980,680);
        setLayout(null);

    }
    public void Load_Product(Product p)
    {
        _currentUser = Application.getInstance().getCurrectUser();
        _dataAdapter = Application.getInstance().getDataAdapter();
        this.setProduct(p);
        if(p == null)
        {
            System.out.println("Product is null");
            this.setVisible(false);
            return;
        }

        this.setTitle("Welcome to the Shopping Checkout");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setSize(1000, 600);

        JPanel pnlProductInfo = new JPanel();
        pnlProductInfo.setSize(400,100);
        pnlProductInfo.setLayout(new GridLayout(2,4));

        JLabel lblCurrentUsersName = new JLabel(_currentUser.getFirstName() + " " + _currentUser.getLastName());
        JLabel lblProductID = new JLabel(String.valueOf(p.getProductID()));
        JLabel lblProductName = new JLabel(p.getProductName());
        JLabel lblPrice = new JLabel("$ " + String.valueOf(p.getPrice()));

        pnlProductInfo.add(lblCurrentUsersName);
        pnlProductInfo.add(lblProductID);
        pnlProductInfo.add(lblProductName);
        pnlProductInfo.add(lblPrice);

        JComboBox<String> cmboShippers = new JComboBox<String>();
        ArrayList<Shipper> shipperList = _dataAdapter.GetAllShippers();
        for(Shipper s : shipperList)
        {
            cmboShippers.addItem(s.getShipperName());
        }
        pnlProductInfo.add(cmboShippers);

        int shipperSelected = cmboShippers.getSelectedIndex();
        _shipper = shipperList.get(shipperSelected);

        String[] methods = {"Standard", "Next Day Air", "Two-Day Shipping" };
        JComboBox<String> cmboShipperMethod = new JComboBox(methods);
        pnlProductInfo.add(cmboShipperMethod);
        this.getContentPane().add(pnlProductInfo);
        int selectedMethod = cmboShipperMethod.getSelectedIndex();
        String smethod = methods[selectedMethod];
        _shipperMethod = _dataAdapter.GetShipperMethodByShipperIDAndMethod(_shipper.getShipperID(), smethod);

        _items.addColumn("Product ID");
        _items.addColumn("Name");
        _items.addColumn("Price");
        _items.addColumn("Quantity");
        _items.addColumn("Line Total");

        JPanel pnlOrder = new JPanel();
        pnlOrder.setPreferredSize(new Dimension(600, 450));
        pnlOrder.setLayout(new BoxLayout(pnlOrder, BoxLayout.PAGE_AXIS));

        _tblItems.setBounds(0, 0, 400, 350);
        pnlOrder.add(_tblItems.getTableHeader());
        pnlOrder.add(_tblItems);
        //pnlOrder.add(_labTotal);
        _tblItems.setFillsViewportHeight(true);
        this.getContentPane().add(pnlOrder);
        addProduct(p);

        JPanel pnlOrderTotal = new JPanel();
        pnlOrderTotal.setBounds(275,300,200,100);
        _labTotal.setText("Order Total: $ " + String.valueOf(df.format(_order.getTotalAmount())));
        pnlOrderTotal.add(_labTotal);
        this.getContentPane().add(pnlOrderTotal);

        JPanel pnlButton = new JPanel();
        pnlButton.setPreferredSize(new Dimension(400, 100));
        pnlButton.add(_btnFinishAndPay);
        this.getContentPane().add(pnlButton);
    }

    public void addRow(Object[] row)
    {
        _items.addRow(row);              // add a row to list of item!
        _items.fireTableDataChanged();
    }
    private void addProduct(Product p)
    {
//       int id = _product.getProductID();

        _product = p;
        int quantity = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter quantity: "));

        if (quantity < 1 || quantity > _product.getQuantityOnHand())
        {
            String errorMessage = "Invalid Quantity! Choose between 1 and " + String.valueOf(_product.getQuantityOnHand());
            JOptionPane.showMessageDialog(null, errorMessage);
            return;
        }

        Users currentUser = Application.getInstance().getCurrectUser();
        int currentUserID = currentUser.getUserID();
        int newOrderID = _dataAdapter.GetNextOrderID();
        String currentDate = GetCurrentDate();
        _order.setOrderID(newOrderID);
        _order.setCustomerID(currentUserID);
        _order.setOrderDate(currentDate);
        _order.setDateAdded(currentDate);
        _order.setDateModified(currentDate);
        String currentUserName = currentUser.getFirstName() + " " + currentUser.getLastName();

        try
        {
            _order.setShippingAddressID(_dataAdapter.GetShippingContactInfoByUser(currentUserID).getContactInfoID());
        }
        catch(SQLException sqlException)
        {
            JOptionPane.showMessageDialog(null,"Cannot obtain shipping address for " + currentUserName);
        }

        _order.setSubtotal(quantity * _product.getPrice());

        OrderDetails line = new OrderDetails();
        line.setOrderDetailsID(_dataAdapter.GetNextOrdersDetailID());
        line.setOrderID(newOrderID);
        line.setProductID(_product.getProductID());
        line.setQuantity(quantity);
        line.setLineTotal(quantity * _product.getPrice());
        line.setDateAdded(GetCurrentDate());
        line.setDateModified(GetCurrentDate());

        Object[] row = new Object[5];
        row[0] = line.getProductID();
        row[1] = _product.getProductName();
        row[2] = _product.getPrice();
        row[3] = line.getQuantity();
        row[4] = line.getLineTotal();

        this.addRow(row);

        double salesTaxAmount = _order.getSubtotal() * .1; //10% sales tax assumed
        _order.setSalesTax(salesTaxAmount);
        _order.setShippingAmount(_shipperMethod.getMethodCost());
        _order.setStatusID(2001); //2001 status means submitted
        _order.setShipperID(_shipper.getShipperID());

        double total = _order.getSubtotal() + _order.getSalesTax() + _order.getShippingAmount();

        _order.setTotalAmount(total);
        _product.setQuantityOnHand(_product.getQuantityOnHand() - quantity); // update new quantity!!
        _dataAdapter.saveProduct(_product); // and store this product back right away!!!
        _order.setTotalAmount(total);
        _order.addOrderDetails(line);

        this.getLabTotal().setText("Total: $" + _order.getTotalAmount());
        this.invalidate();
    }
    private String GetCurrentDate()
    {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTimeFormatter.format(now);
    }
}
