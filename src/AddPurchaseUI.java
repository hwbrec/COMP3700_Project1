import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class AddPurchaseUI {
    public JFrame view;

    public JButton btnAdd = new JButton("Add");
    public JButton btnCancel = new JButton("Cancel");

    public JTextField txtPurchaseID = new JTextField(10);
    public JTextField txtCustomerID = new JTextField(10);
    public JTextField txtProductID = new JTextField(10);
    public JTextField txtQuantity = new JTextField(10);

    public JLabel labPrice = new JLabel("Product Price: ");
    public JLabel labDate = new JLabel("Date of Purchase: ");

    public JLabel labCustomerName = new JLabel("Customer Name: ");
    public JLabel labProductName = new JLabel("Product Name: ");

    public JLabel labCost = new JLabel("Cost: $0.00 ");
    public JLabel labTax = new JLabel("Tax: $0.00");
    public JLabel labTotalCost = new JLabel("Total Cost: $0.00");

    ProductModel product;
    CustomerModel customer;
    
    private static DecimalFormat df = new DecimalFormat("0.00");
    
    private void process() {
               String s = txtProductID.getText();
               String y = txtCustomerID.getText();

               if (s.length() == 0) {
                  labProductName.setText("Product Name: [not specified!]");
                  return;
               }
               if (y.length() == 0) {
                  labCustomerName.setText("Customer Name: [not specified!]");
                  return;
               }

               System.out.println("ProductID = " + s);
               System.out.println("CustomerID = " + y);

               int id;
               int custId;

               try {
                  id = Integer.parseInt(s);

               } catch (NumberFormatException ex) {
                  JOptionPane.showMessageDialog(null,
                           "Error: Invalid ProductID", "Error Message",
                           JOptionPane.ERROR_MESSAGE);
                  return;
               }
                
               try {
                  custId = Integer.parseInt(y);

               } catch (NumberFormatException ex) {
                  JOptionPane.showMessageDialog(null,
                           "Error: Invalid CustomerID", "Error Message",
                           JOptionPane.ERROR_MESSAGE);
                  return;
               }

               product = StoreManager.getInstance().getDataAdapter().loadProduct(id);
               customer = StoreManager.getInstance().getDataAdapter().loadCustomer(custId);
                
               if (product == null) {
                  JOptionPane.showMessageDialog(null,
                           "Error: No product with id = " + id + " in store!", "Error Message",
                           JOptionPane.ERROR_MESSAGE);
                           labProductName.setText("Product Name: ");

                  return;
               }
               if (customer == null) {
                  JOptionPane.showMessageDialog(null,
                           "Error: No customer with id = " + id + " in store!", "Error Message",
                           JOptionPane.ERROR_MESSAGE);
                           labProductName.setText("Customer Name: ");

                  return;
               }
 
               labCustomerName.setText("Customer Name: " + customer.mName);
               labProductName.setText("Product Name: " + product.mName);
               labPrice.setText("Product Price: " + "$" + df.format(product.mPrice));
               labCost.setText("Cost: " + "$" + df.format((product.mPrice * Integer.parseInt(txtQuantity.getText()))));
               labTax.setText("Tax: " + "$" + (df.format((product.mPrice * Integer.parseInt(txtQuantity.getText())) * 0.04)));
               labTotalCost.setText("Total Cost: " + "$" + (df.format((product.mPrice * Integer.parseInt(txtQuantity.getText())) + (product.mPrice * Integer.parseInt(txtQuantity.getText())) * 0.04)));
                
            }

    public AddPurchaseUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Add Purchase");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel line = new JPanel(new FlowLayout());
        line.add(new JLabel("PurchaseID "));
        line.add(txtPurchaseID);
        line.add(labDate);
        view.getContentPane().add(line);
        
        line = new JPanel(new FlowLayout());
        line.add(new JLabel("CustomerID "));
        line.add(txtCustomerID);
        line.add(labCustomerName);
        view.getContentPane().add(line);
 
        line = new JPanel(new FlowLayout());
        line.add(new JLabel("ProductID "));
        line.add(txtProductID);
        line.add(labProductName);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("Quantity "));
        line.add(txtQuantity);
        line.add(labPrice);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(labCost);
        line.add(labTax);
        line.add(labTotalCost);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(btnAdd);
        line.add(btnCancel);
        view.getContentPane().add(line);
        
        btnAdd.addActionListener(new AddButtonListener());
        
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                view.dispose();
            }
        });
        
        /*txtCustomerID.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                process();
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                process();
            }

            private void process() {
               String s = txtProductID.getText();
               String y = txtCustomerID.getText();

               if (s.length() == 0) {
                  labProductName.setText("Product Name: [not specified!]");
                  return;
               }
               if (y.length() == 0) {
                  labCustomerName.setText("Customer Name: [not specified!]");
                  return;
               }

               System.out.println("ProductID = " + s);
               System.out.println("CustomerID = " + y);

               int id;
               int custId;

               try {
                  id = Integer.parseInt(s);

               } catch (NumberFormatException ex) {
                  JOptionPane.showMessageDialog(null,
                           "Error: Invalid ProductID", "Error Message",
                           JOptionPane.ERROR_MESSAGE);
                  return;
               }
                
               try {
                  custId = Integer.parseInt(y);

               } catch (NumberFormatException ex) {
                  JOptionPane.showMessageDialog(null,
                           "Error: Invalid CustomerID", "Error Message",
                           JOptionPane.ERROR_MESSAGE);
                  return;
               }

               product = StoreManager.getInstance().getDataAdapter().loadProduct(id);
               customer = StoreManager.getInstance().getDataAdapter().loadCustomer(custId);
                
               if (product == null) {
                  JOptionPane.showMessageDialog(null,
                           "Error: No product with id = " + id + " in store!", "Error Message",
                           JOptionPane.ERROR_MESSAGE);
                           labProductName.setText("Product Name: ");

                  return;
               }
               if (customer == null) {
                  JOptionPane.showMessageDialog(null,
                           "Error: No customer with id = " + id + " in store!", "Error Message",
                           JOptionPane.ERROR_MESSAGE);
                           labProductName.setText("Customer Name: ");

                  return;
               }
 
               labCustomerName.setText("Customer Name: " + customer.mName);
               labProductName.setText("Product Name: " + product.mName);
               labPrice.setText("Product Price: " + product.mPrice);
               labCost.setText("Cost: " + (product.mPrice * Integer.parseInt(txtQuantity.getText())));
               labTax.setText("Tax: " + (df.format((product.mPrice * Integer.parseInt(txtQuantity.getText())) * 0.04)));
               labTotalCost.setText("Total Cost: " + (df.format((product.mPrice * Integer.parseInt(txtQuantity.getText())) + (product.mPrice * Integer.parseInt(txtQuantity.getText())) * 0.04)));
            }

        });*/
      }
      
      class AddButtonListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
               PurchaseModel purchase = new PurchaseModel();

               String purchaseId = txtPurchaseID.getText();
               if (purchaseId.length() == 0) {
                  JOptionPane.showMessageDialog(null, "PurchaseID cannot be null!");
                  return;
               }

               try {
                  purchase.mPurchaseID = Integer.parseInt(purchaseId);
               } catch (NumberFormatException e) {
                  JOptionPane.showMessageDialog(null, "PurchaseID is invalid!");
                  return;
               }

               String customerId = txtCustomerID.getText();
               if (customerId.length() == 0) {
                  JOptionPane.showMessageDialog(null, "CustomerID cannot be empty!");
                  return;
               }
            
               try {
                  purchase.mCustomerID = Integer.parseInt(customerId);
               } catch (NumberFormatException e) {
                  JOptionPane.showMessageDialog(null, "CustomerID is invalid!");
                  return;
               }

               String productId = txtProductID.getText();
               if (productId.length() == 0) {
                  JOptionPane.showMessageDialog(null, "ProductID cannot be empty!");
                  return;
               }
            
               try {
                  purchase.mProductID = Integer.parseInt(productId);
               } catch (NumberFormatException e) {
                  JOptionPane.showMessageDialog(null, "ProductID is invalid!");
                  return;
               }

               String quantity = txtQuantity.getText();
               if (quantity.length() == 0) {
                  JOptionPane.showMessageDialog(null, "Quantity cannot be empty!");
                  return;
               }
            
               try {
                  purchase.mQuantity = Integer.parseInt(quantity);
               } catch (NumberFormatException e) {
                  JOptionPane.showMessageDialog(null, "Quantity is invalid!");
                  return;
               }
               
               int id;
               int custId;
               process();
               String s = txtProductID.getText();
               String y = txtCustomerID.getText();
               id = Integer.parseInt(s);
               custId = Integer.parseInt(y);
               product = StoreManager.getInstance().getDataAdapter().loadProduct(id);
               customer = StoreManager.getInstance().getDataAdapter().loadCustomer(custId);
               TXTReceiptBuilder receipt = new TXTReceiptBuilder();
               
               switch (StoreManager.getInstance().getDataAdapter().savePurchase(purchase)) {
                  case SQLiteDataAdapter.PURCHASE_DUPLICATE_ERROR:
                     JOptionPane.showMessageDialog(null, "Purchase NOT added successfully! Duplicate purchase ID!");
                     break;
                  default:
                     JOptionPane.showMessageDialog(null, "Purchase added successfully!" + purchase + "\nPress ok to print receipt.");
                     receipt.toString("", customer, product, purchase, "");
               }
         }
        
        
//        txtCustomerID.getDocument().addDocumentListener(new DocumentListener() {
//            public void changedUpdate(DocumentEvent e) {
//                process();
//            }
//
//            public void removeUpdate(DocumentEvent e) {
//                process();
//            }
//            public void insertUpdate(DocumentEvent e) {
//                process();
//            }
//
//            private void process() {
//                String s = txtCustomerID.getText();
//
//                if (s.length() == 0) {
//                    labCustomerName.setText("Customer Name: [not specified!]");
//                    return;
//                }
//
//                System.out.println("CustomerID = " + s);
//
//                try {
//                    int id = Integer.parseInt(s);
//
//                } catch (NumberFormatException ex) {
//                    JOptionPane.showMessageDialog(null,
//                            "Error: Please enter number bigger than 0", "Error Message",
//                            JOptionPane.ERROR_MESSAGE);
//                }
//
//            }
//        });

    }

    public void run() {

        labDate.setText("Date of purchase: " + Calendar.getInstance().getTime());
        view.setVisible(true);
    }
}
