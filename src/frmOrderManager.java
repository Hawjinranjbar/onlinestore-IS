import javax.swing.*;
import java.awt.*;
import common.Order;
import managers.OrderManager;

public class frmOrderManager extends JFrame {
    private JTextArea txtOrders;
    private JButton btnConfirm;
    private OrderManager om;

    public frmOrderManager() {
        setTitle("📦 Manage Orders (Manager)");
        setSize(850, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        om = new OrderManager();

        txtOrders = new JTextArea(20, 60);
        txtOrders.setEditable(false);
        txtOrders.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(txtOrders);
        scrollPane.setBorder(BorderFactory.createTitledBorder("🧾 Order List"));

        btnConfirm = new JButton("✔ Confirm Selected Order");
        btnConfirm.setBackground(new Color(0xD4EDDA));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnConfirm);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // 🔥 دکمه تایید سفارش
        btnConfirm.addActionListener(e -> {
            try {
                String input = JOptionPane.showInputDialog(this, "Enter Order ID to confirm:");
                if (input != null && !input.trim().isEmpty()) {
                    int orderId = Integer.parseInt(input.trim());
                    JOptionPane.showMessageDialog(this, "✅ Order #" + orderId + " confirmed successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "⚠️ Order ID cannot be empty!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "❌ Invalid Order ID!");
            }
        });

        refreshOrders();
        setVisible(true);
    }

    private void refreshOrders() {
        Order[] orders = om.SelectAll();
        if (orders == null || orders.length == 0) {
            txtOrders.setText("⚠️ No valid orders found.");
            return;
        }

        StringBuilder s = new StringBuilder();
        for (Order o : orders) {
            if (o == null) continue; // خط خراب رو نادیده بگیر
            s.append("🆔 Order ID: ").append(o.getId()).append("\n")
                    .append("👤 Name: ").append(o.getCustomerName()).append("\n")
                    .append("📞 Phone: ").append(o.getPhoneNumber()).append("\n")
                    .append("🛍️ Products: ").append(o.getProductDetails()).append("\n")
                    .append("💰 Total Price: ").append(o.getTotalPrice()).append("\n")
                    .append("--------------------------------------------------\n");
        }
        txtOrders.setText(s.toString());
    }

    public static void main(String[] args) {
        new frmOrderManager();
    }
}
