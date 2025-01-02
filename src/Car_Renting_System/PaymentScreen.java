package Car_Renting_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class PaymentScreen extends JFrame implements ActionListener {

    JTextField paymentIdtext,paymentDatetext,cardNumbertext,amountText,nictext;

    JButton payNowBtn, backBtn, cancelBtn,feedbackBtn;

    PaymentScreen(){
        super("Payment Page");
        getContentPane().setBackground(Color.darkGray);

        JLabel PaymentLabel = new JLabel("Payment Details");
        PaymentLabel.setFont(new Font("Times New Roman", Font.BOLD, 24)); // Set font size and style
        PaymentLabel.setForeground(Color.YELLOW); // Set color to yellow
        PaymentLabel.setBounds(50, 10, 300, 40); // Set bounds for animation
        add(PaymentLabel);

        animateLabel(PaymentLabel); // Call method to animate the label

        //ADD Payment ID and Text Field
        JLabel PaymentId = new JLabel("Payment ID");
        PaymentId.setBounds(50, 65, 100, 20);
        PaymentId.setForeground(Color.YELLOW); // Set color to yellow
        add(PaymentId);

        paymentIdtext = new JTextField();
        paymentIdtext.setBounds(150, 65, 150, 20);
        add(paymentIdtext);

        //ADD Date and Text Field
        JLabel pNicNum = new JLabel("NIC Number");
        pNicNum.setBounds(50, 105, 100, 20);
        pNicNum.setForeground(Color.YELLOW); // Set color to yellow
        add(pNicNum);

        nictext = new JTextField();
        nictext.setBounds(150, 105, 150, 20);
        add(nictext);

        //ADD Card Number and textFiels
        JLabel CardNum = new JLabel("Card Number");
        CardNum.setBounds(50, 145, 100, 20);
        CardNum.setForeground(Color.yellow);
        add(CardNum);

        cardNumbertext = new JTextField();
        cardNumbertext.setBounds(150, 145, 150, 20);
        add(cardNumbertext);


        //ADD Amount label and textField
        JLabel amount = new JLabel("Amount");
        amount.setBounds(50, 185, 100, 20);
        amount.setForeground(Color.YELLOW); // Set color to yellow
        add(amount);

        amountText = new JTextField();
        amountText.setBounds(150, 185, 150, 20);
        add(amountText);

        //ADD Date label and textField
        JLabel rLocation = new JLabel("Date");
        rLocation.setBounds(50, 225, 100, 20);
        rLocation.setForeground(Color.YELLOW); // Set color to yellow
        add(rLocation);

        paymentDatetext = new JTextField();
        paymentDatetext.setBounds(150, 225, 150, 20);
        add(paymentDatetext);


        //Button ADD

        //Add to PayNowBtn
        payNowBtn= new JButton("Pay Now");
        payNowBtn.setBackground(new Color(66, 127, 219));
        payNowBtn.setForeground(Color.black);
        payNowBtn.setBounds(400, 65, 150, 25);
        payNowBtn.addActionListener(this);
        add(payNowBtn);

        //Add to FeedbackBtn
        feedbackBtn= new JButton("Add Feedback");
        feedbackBtn.setBackground(new Color(66, 127, 219));
        feedbackBtn.setForeground(Color.black);
        feedbackBtn.setBounds(400, 95, 150, 25);
        feedbackBtn.addActionListener(this);
        add(feedbackBtn);

        //Add to Cancel
        cancelBtn= new JButton("Cancel");
        cancelBtn.setBackground(new Color(66, 127, 219));
        cancelBtn.setForeground(Color.black);
        cancelBtn.setBounds(400, 125, 150, 25);
        cancelBtn.addActionListener(this);
        add(cancelBtn);


        //Add to Back
        backBtn= new JButton("Back");
        backBtn.setBackground(new Color(66, 127, 219));
        backBtn.setForeground(Color.black);
        backBtn.setBounds(400, 155, 150, 25);
        backBtn.addActionListener(this);
        add(backBtn);


        setSize(600, 380);
        setLocation(500, 200);
        setLayout(null);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == payNowBtn)
        {
            java.util.Date currentDate = new java.util.Date();
            java.sql.Date sqlData = new java.sql.Date(currentDate.getTime());
            String Pdate = sqlData.toString();


            String Pid = paymentIdtext.getText();
            //String Pdate = paymentDatetext.getText();
            String Cnum = cardNumbertext.getText();
            String Amount = amountText.getText();
            String NIC = nictext.getText();
            try {

                // Establish database connection
                String url = "jdbc:mysql://localhost:3306/Car_Rental_System_DB";
                String dbUsername = "root";
                String dbPassword = "20030905";
                Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);

                // Create a prepared statement
                String query = "INSERT INTO Payment (Payment_ID,Card_Number,Payment_Date,Amount,Customer_NIC) VALUES(?,?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, Pid);
                preparedStatement.setString(2, Cnum);
                preparedStatement.setDate(3, sqlData);
                preparedStatement.setString(4, Amount);
                preparedStatement.setString(5, NIC);


                // Execute the statement
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Payment Details insert successfully!");

                    // Close resources
                    preparedStatement.close();
                    connection.close();



                } else {
                    JOptionPane.showMessageDialog(null, "Failed to insert data.");
                }

                // Close resources
                preparedStatement.close();
                connection.close();

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Failed to Insert Data: " + ex.getMessage());
            }
        } else if (e.getSource()==backBtn) {
            setVisible(false);
            new BookingScreen();
        } else if (e.getSource()== feedbackBtn) {
            setVisible(false);
            new FeedBack();
        } else if (e.getSource()==cancelBtn) {

            paymentDatetext.setText("");
            paymentIdtext.setText("");
            cardNumbertext.setText("");
            amountText.setText("");
            nictext.setText("");

            //JTextField paymentIdtext,paymentDatetext,cardNumbertext,amountText,nictext;
        }
    }



    // Method to animate the label
    private void animateLabel(JLabel label) {
        Timer timer = new Timer(100, e -> {
            int x = label.getX();
            int y = label.getY();
            if (x < 250) {
                label.setLocation(x + 1, y);
            } else {
                ((Timer) e.getSource()).stop();
            }
        });
        timer.start();
    }


    public static void main (String[]args){
        new PaymentScreen();
    }
}
