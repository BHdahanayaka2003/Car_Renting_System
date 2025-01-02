package Car_Renting_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class FeedBack extends JFrame implements ActionListener {

    JTextField feedbacktext,feedbackIDtext,rideIDtext;
    JButton sumbitBtn,backBtn;

    FeedBack(){

        super("Feedback Page");
        getContentPane().setBackground(Color.darkGray);

        JLabel FeedbackLabel = new JLabel("FeedBack");
        FeedbackLabel.setFont(new Font("Times New Roman", Font.BOLD, 24)); // Set font size and style
        FeedbackLabel.setForeground(Color.YELLOW); // Set color to yellow
        FeedbackLabel.setBounds(50, 10, 300, 40); // Set bounds for animation
        add(FeedbackLabel);

        animateLabel(FeedbackLabel); // Call method to animate the label


        //ADD Feedback ID and Text Field
        JLabel feedbackID = new JLabel("Feedback ID");
        feedbackID.setBounds(50, 65, 100, 20);
        feedbackID.setForeground(Color.YELLOW); // Set color to yellow
        add(feedbackID);

        feedbackIDtext = new JTextField();
        feedbackIDtext.setBounds(150, 65, 150, 20);
        add(feedbackIDtext);


        //ADD RideID and Text Field
        JLabel rideId = new JLabel("Ride ID");
        rideId.setBounds(50, 105, 100, 20);
        rideId.setForeground(Color.YELLOW); // Set color to yellow
        add(rideId);

        rideIDtext = new JTextField();
        rideIDtext.setBounds(150, 105, 150, 20);
        add(rideIDtext);

        //ADD RideID and Text Field
        JLabel Feedback = new JLabel("Your Feedback");
        Feedback.setBounds(50, 150, 100, 20);
        Feedback.setForeground(Color.YELLOW); // Set color to yellow
        add(Feedback);

        feedbacktext = new JTextField();
        feedbacktext.setBounds(150, 150, 350, 50);
        add(feedbacktext);


        //Add to SubmitBtn

        sumbitBtn = new JButton("Submit");
        sumbitBtn.setBackground(new Color(66, 127, 219));
        sumbitBtn.setForeground(Color.black);
        sumbitBtn.setBounds(250, 230, 150, 25);
        sumbitBtn.addActionListener(this);
        add(sumbitBtn);

        //Add to BackBtn

        backBtn = new JButton("Back");
        backBtn.setBackground(new Color(66, 127, 219));
        backBtn.setForeground(Color.black);
        backBtn.setBounds(250, 270, 150, 25);
        backBtn.addActionListener(this);
        add(backBtn);


        setSize(600, 380);
        setLocation(500, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == sumbitBtn) {
            String Fid = feedbackIDtext.getText();
            String Rid = rideIDtext.getText();
            String Feedback = feedbacktext.getText();

            try {
                // Establish database connection
                String url = "jdbc:mysql://localhost:3306/Car_Rental_System_DB";
                String dbUsername = "root";
                String dbPassword = "20030905";
                Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);

                // Create a prepared statement
                String query = "INSERT INTO Feedback (Feedback_ID,F_Description,Ride_ID) VALUES(?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, Fid);
                preparedStatement.setString(2, Feedback);
                preparedStatement.setString(3, Rid);

                // Execute the statement
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Feedback Details insert successfully!");

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
                ex.printStackTrace(); ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Failed to Insert Data: " + ex.getMessage());
            }
        } else if (e.getSource()==backBtn) {
            setVisible(false);
            new PaymentScreen();
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
        new FeedBack();
    }
}
