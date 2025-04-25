import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import common.Customer;
import managers.CustomerManager;

public class frmCustomer extends JFrame {
    private JTextField txtId, txtFullName, txtEmail, txtPhone;
    private JButton btnInsert, btnUpdate;
    private CustomerManager cm;

    public frmCustomer() {
        setTitle("👤 Customer Registration");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        cm = new CustomerManager();

        // 🧩 Panel ورودی
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 10, 30));
        inputPanel.setBackground(Color.WHITE);

        txtId = new JTextField();
        txtFullName = new JTextField();
        txtEmail = new JTextField();
        txtPhone = new JTextField();

        inputPanel.add(new JLabel("Customer ID:"));     inputPanel.add(txtId);
        inputPanel.add(new JLabel("Full Name:"));        inputPanel.add(txtFullName);
        inputPanel.add(new JLabel("Email:"));            inputPanel.add(txtEmail);
        inputPanel.add(new JLabel("Phone:"));            inputPanel.add(txtPhone);

        // 🎯 دکمه‌ها
        btnInsert = new JButton("➕ Register Customer");
        btnUpdate = new JButton("✏️ Update Information");

        btnInsert.setBackground(new Color(0xD4EDDA));
        btnUpdate.setBackground(new Color(0xFFF3CD));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        buttonPanel.add(btnInsert);
        buttonPanel.add(btnUpdate);

        // اضافه کردن پنل‌ها
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // ✅ اکشن‌ها
        btnInsert.addActionListener(e -> {
            try {
                Customer c = getCustomerFromInput();
                cm.Insert(c);
                JOptionPane.showMessageDialog(this, "✅ Customer registered successfully!");
                clearFields();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "❌ Registration error.");
            }
        });

        btnUpdate.addActionListener(e -> {
            try {
                int row = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter your record number to update:"));
                Customer c = getCustomerFromInput();
                cm.Update(c, row);
                JOptionPane.showMessageDialog(this, "✅ Customer info updated!");
                clearFields();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "❌ Update error.");
            }
        });

        setVisible(true);
    }

    private Customer getCustomerFromInput() {
        int id = Integer.parseInt(txtId.getText());
        String name = txtFullName.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        return new Customer(id, name, email, phone);
    }

    private void clearFields() {
        txtId.setText("");
        txtFullName.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
    }

    public static void main(String[] args) {
        new frmCustomer();
    }
}
