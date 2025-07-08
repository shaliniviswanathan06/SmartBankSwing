import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class WithdrawPage extends JFrame implements ActionListener {
    JTextField accField, amountField;
    JButton withdrawBtn;

    public WithdrawPage() {
        setTitle("Smart Bank - Withdraw");
        setSize(350, 220);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel accLabel = new JLabel("Account No:");
        accLabel.setBounds(30, 30, 100, 25);
        add(accLabel);

        accField = new JTextField();
        accField.setBounds(140, 30, 150, 25);
        add(accField);

        JLabel amtLabel = new JLabel("Withdraw Amount:");
        amtLabel.setBounds(30, 70, 120, 25);
        add(amtLabel);

        amountField = new JTextField();
        amountField.setBounds(140, 70, 150, 25);
        add(amountField);

        withdrawBtn = new JButton("Withdraw");
        withdrawBtn.setBounds(110, 120, 120, 30);
        withdrawBtn.addActionListener(this);
        add(withdrawBtn);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String accNo = accField.getText();
        String amtStr = amountField.getText();

        try {
            File inputFile = new File("accounts.dat");
            File tempFile = new File("temp.dat");

            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

            String line;
            boolean found = false;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(accNo)) {
                    double currentBal = Double.parseDouble(parts[3]);
                    double withdrawAmt = Double.parseDouble(amtStr);

                    if (withdrawAmt > currentBal) {
                        JOptionPane.showMessageDialog(this, "❌ Insufficient Balance!");
                        br.close();
                        bw.close();
                        tempFile.delete();
                        return;
                    }

                    double newBal = currentBal - withdrawAmt;

                    bw.write(parts[0] + "," + parts[1] + "," + parts[2] + "," + newBal);
                    bw.newLine();
                    found = true;
                } else {
                    bw.write(line);
                    bw.newLine();
                }
            }

            br.close();
            bw.close();

            inputFile.delete();
            tempFile.renameTo(inputFile);

            if (found) {
                JOptionPane.showMessageDialog(this, "✅ Withdrawal successful!");
            } else {
                JOptionPane.showMessageDialog(this, "❌ Account not found!");
            }

            dispose();
            new MainMenuPage();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "⚠ Error during withdrawal.");
        }
    }

    public static void main(String[] args) {
        new WithdrawPage();
    }
}