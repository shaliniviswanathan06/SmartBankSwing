import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class LoginPage extends JFrame implements ActionListener {
    JTextField accField;
    JPasswordField passField;
    JButton loginBtn;

    public LoginPage() {
        setTitle("Smart Bank - Login");
        setSize(300, 200);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel accLabel = new JLabel("Account No:");
        accLabel.setBounds(30, 30, 100, 25);
        add(accLabel);

        accField = new JTextField();
        accField.setBounds(130, 30, 120, 25);
        add(accField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(30, 70, 100, 25);
        add(passLabel);

        passField = new JPasswordField();
        passField.setBounds(130, 70, 120, 25);
        add(passField);

        loginBtn = new JButton("Login");
        loginBtn.setBounds(100, 110, 80, 30);
        loginBtn.addActionListener(this);
        add(loginBtn);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String accNo = accField.getText();
        String password = new String(passField.getPassword());

        boolean found = false;

        try {
            BufferedReader br = new BufferedReader(new FileReader("accounts.dat"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[0].equals(accNo) && parts[1].equals(password)) {
                    found = true;
                    break;
                }
            }
            br.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "❌ Error reading file.");
        }

        if (found) {
            JOptionPane.showMessageDialog(this, "✅ Login Successful!");
            dispose();
            new MainMenuPage(); // Open menu
        } else {
            JOptionPane.showMessageDialog(this, "❌ Invalid Account No or Password");
        }
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}