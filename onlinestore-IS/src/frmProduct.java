import javax.swing.*;
import java.awt.event.*;
import common.Product;
import managers.ProductManager;

public class frmProduct extends JFrame {
    private JTextField txtId, txtName, txtPrice, txtStock;
    private JButton btnInsert;
    private JTextArea txtList;

    public frmProduct() {
        setTitle("مدیریت محصولات 🛒");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblId = new JLabel("کد محصول:");
        lblId.setBounds(20, 20, 100, 25);
        add(lblId);

        txtId = new JTextField();
        txtId.setBounds(130, 20, 200, 25);
        add(txtId);

        JLabel lblName = new JLabel("نام محصول:");
        lblName.setBounds(20, 60, 100, 25);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(130, 60, 200, 25);
        add(txtName);

        JLabel lblPrice = new JLabel("قیمت:");
        lblPrice.setBounds(20, 100, 100, 25);
        add(lblPrice);

        txtPrice = new JTextField();
        txtPrice.setBounds(130, 100, 200, 25);
        add(txtPrice);

        JLabel lblStock = new JLabel("موجودی:");
        lblStock.setBounds(20, 140, 100, 25);
        add(lblStock);

        txtStock = new JTextField();
        txtStock.setBounds(130, 140, 200, 25);
        add(txtStock);

        btnInsert = new JButton("➕ ثبت محصول");
        btnInsert.setBounds(130, 180, 200, 30);
        add(btnInsert);

        txtList = new JTextArea();
        txtList.setBounds(20, 230, 340, 120);
        txtList.setEditable(false);
        add(txtList);

        // 🎯 عملکرد دکمه
        btnInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(txtId.getText());
                    String name = txtName.getText();
                    double price = Double.parseDouble(txtPrice.getText());
                    int stock = Integer.parseInt(txtStock.getText());

                    Product p = new Product(id, name, price, stock);
                    ProductManager pm = new ProductManager();
                    pm.Insert(p);

                    // نمایش لیست محصولات
                    Product[] products = pm.SelectAll();
                    String s = "";
                    for (Product prod : products) {
                        s += prod.toString() + "\n";
                    }
                    txtList.setText(s);

                    JOptionPane.showMessageDialog(null, "✅ محصول ثبت شد!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "❌ خطا در ورود اطلاعات");
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new frmProduct();
    }
}
