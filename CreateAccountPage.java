import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class CreateAccountPage extends JFrame implements ActionListener {
    JTextField nameField, accField, passField, balanceField;
    JButton createBtn;
    public CreateAccountPage() {
        setTitle("Smart Bank - Create Account");
        setSize(400, 300);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setBounds(30, 30, 100, 25);
        add(nameLabel);
        nameField = new JTextField();
        nameField.setBounds(140, 30, 200, 25);
        add(nameField);
        JLabel accLabel = new JLabel("Account No:");
        accLabel.setBounds(30, 70, 100, 25);
        add(accLabel);
        accField = new JTextField();
        accField.setBounds(140, 70, 200, 25);
        add(accField);
        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(30, 110, 100, 25);
        add(passLabel); 
        passField = new JTextField();
        passField.setBounds(140, 110, 200, 25);
        add(passField);
        JLabel balanceLabel = new JLabel("Initial Balance:");
        balanceLabel.setBounds(30, 150, 100, 25);
        add(balanceLabel);
        balanceField = new JTextField();
        balanceField.setBounds(140, 150, 200, 25);
        add(balanceField);
        createBtn = new JButton("Create Account");
        createBtn.setBounds(120, 200, 150, 30);
        createBtn.addActionListener(this);
        add(createBtn);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        String name = nameField.getText();
        String accNo = accField.getText();
        String password = passField.getText();
        String balance = balanceField.getText();
        try {
            FileWriter fw = new FileWriter("accounts.dat", true); // append mode
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(accNo + "," + password + "," + name + "," + balance);
            bw.newLine();
            bw.close();
            JOptionPane.showMessageDialog(this, "Account Created Successfully!");
            dispose(); // close window
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error saving account!");
        }
    }
    public static void main(String[] args) {
        new CreateAccountPage();
    }
}
