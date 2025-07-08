import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class MainMenuPage extends JFrame implements ActionListener {
    JButton depositBtn, withdrawBtn, balanceBtn, logoutBtn;
    public MainMenuPage() {
        setTitle("Smart Bank - Main Menu");
        setSize(300, 250);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        depositBtn = new JButton("Deposit");
        depositBtn.setBounds(90, 30, 120, 30);
        depositBtn.addActionListener(this);
        add(depositBtn);
        withdrawBtn = new JButton("Withdraw");
        withdrawBtn.setBounds(90, 70, 120, 30);
        withdrawBtn.addActionListener(this);
        add(withdrawBtn);
        balanceBtn = new JButton("Check Balance");
        balanceBtn.setBounds(90, 110, 120, 30);
        balanceBtn.addActionListener(this);
        add(balanceBtn);
        logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(90, 150, 120, 30);
        logoutBtn.addActionListener(this);
        add(logoutBtn);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == depositBtn) {
            new DepositPage();
            dispose();
        } else if (e.getSource() == withdrawBtn) {
            new WithdrawPage();
            dispose();
        } else if (e.getSource() == balanceBtn) {
            checkBalance();
        } else if (e.getSource() == logoutBtn) {
            JOptionPane.showMessageDialog(this, "Logged out.");
            new LoginPage();
            dispose();
        }
    }
    private void checkBalance() {
        String accNo = JOptionPane.showInputDialog(this, "Enter your account number:");
        if (accNo == null || accNo.trim().isEmpty()) {
            return; // user pressed cancel
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader("accounts.dat"));
            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(accNo)) {
                    String name = parts[2];
                    String balance = parts[3];
                    JOptionPane.showMessageDialog(this, " Name: " + name + "\n Balance: ₹" + balance);
                    found = true;
                    break;
                }
            }
            br.close();
            if (!found) {
                JOptionPane.showMessageDialog(this, "Account not found!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "⚠ Error checking balance.");
        }
    }
    public static void main(String[] args) {
        new MainMenuPage();
    }
}
