import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import managers.ProductManager;
import managers.OrderManager;
import common.Product;
import common.Order;

public class frmOrderCustomer extends JFrame {
    private JTextField txtName, txtPhone;
    private JTextArea txtProductList, txtSelectedProducts;
    private JButton btnAddProduct, btnSubmitOrder;
    private ProductManager pm;
    private OrderManager om;
    private double totalPrice = 0;
    private String selectedProducts = "";

    public frmOrderCustomer() {
        setTitle("🛒 Place Your Order");
        setSize(850, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        pm = new ProductManager();
        om = new OrderManager();

        // 🧩 اطلاعات مشتری
        JPanel customerPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        customerPanel.setBorder(BorderFactory.createTitledBorder("Customer Information"));
        txtName = new JTextField();
        txtPhone = new JTextField();
        customerPanel.add(new JLabel("Full Name:"));
        customerPanel.add(txtName);
        customerPanel.add(new JLabel("Phone:"));
        customerPanel.add(txtPhone);

        // 🧩 لیست محصولات
        txtProductList = new JTextArea(15, 40);
        txtProductList.setEditable(false);
        txtProductList.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        JScrollPane productScroll = new JScrollPane(txtProductList);
        productScroll.setBorder(BorderFactory.createTitledBorder("Available Products"));

        // 🧩 انتخاب محصولات
        txtSelectedProducts = new JTextArea(10, 40);
        txtSelectedProducts.setEditable(false);
        txtSelectedProducts.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        JScrollPane selectedScroll = new JScrollPane(txtSelectedProducts);
        selectedScroll.setBorder(BorderFactory.createTitledBorder("Selected Products"));

        // 🧩 دکمه‌ها
        btnAddProduct = new JButton("➕ Add Product to Order");
        btnSubmitOrder = new JButton("✅ Submit Order");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(btnAddProduct);
        buttonPanel.add(btnSubmitOrder);

        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        centerPanel.add(productScroll);
        centerPanel.add(selectedScroll);

        add(customerPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // 🔥 اکشن‌ها
        refreshProductList();

        btnAddProduct.addActionListener(e -> {
            try {
                int rowNumber = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter product row number to add:"));
                Product p = pm.SelectAll()[rowNumber];
                selectedProducts += p.getName() + " x1 - " + p.getPrice() + " , ";
                totalPrice += p.getPrice();
                txtSelectedProducts.setText(selectedProducts + "\n\nTotal: " + totalPrice);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "❌ Error adding product!");
            }
        });

        btnSubmitOrder.addActionListener(e -> {
            try {
                int id = om.SelectCount();
                String customerName = txtName.getText();
                String phone = txtPhone.getText();
                Order o = new Order(id, customerName, phone, selectedProducts, totalPrice);
                om.Insert(o);
                JOptionPane.showMessageDialog(this, "✅ Order submitted successfully!");
                clearForm();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "❌ Error submitting order!");
            }
        });

        setVisible(true);
    }

    private void refreshProductList() {
        Product[] products = pm.SelectAll();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < products.length; i++) {
            Product p = products[i];
            s.append(i).append(". ")
                    .append(p.getName())
                    .append(" | 💰 ").append(p.getPrice())
                    .append(" | 📦 Stock: ").append(p.getStock())
                    .append("\n\n");
        }
        txtProductList.setText(s.toString());
    }

    private void clearForm() {
        txtName.setText("");
        txtPhone.setText("");
        txtSelectedProducts.setText("");
        selectedProducts = "";
        totalPrice = 0;
    }

    public static void main(String[] args) {
        new frmOrderCustomer();
    }
}
