package Car_Renting_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;

public class BookingScreen extends JFrame implements ActionListener {

    JTextField nicNumtext, pickUpLocationtext, returnLocationtext, daytext, rideIDtext, numOfDaysText, totalText;

    Choice carModelChoice;

    JButton payNowBtn, backBtn, cancelBtn, calculatebtn;

    BookingScreen() {
        super("Booking Page");
        getContentPane().setBackground(Color.darkGray);

        JLabel bookingLabel = new JLabel("Booking Your Car");
        bookingLabel.setFont(new Font("Times New Roman", Font.BOLD, 24)); // Set font size and style
        bookingLabel.setForeground(Color.YELLOW); // Set color to yellow
        bookingLabel.setBounds(50, 10, 300, 40); // Set bounds for animation
        add(bookingLabel);

        animateLabel(bookingLabel); // Call method to animate the label


        //ADD NIC Number and Text Field
        JLabel BnicNum = new JLabel("NIC Number");
        BnicNum.setBounds(50, 65, 100, 20);
        BnicNum.setForeground(Color.YELLOW); // Set color to yellow
        add(BnicNum);

        nicNumtext = new JTextField();
        nicNumtext.setBounds(150, 65, 150, 20);
        add(nicNumtext);

        //ADD Ride ID and Text Field
        JLabel rideID = new JLabel("Ride ID");
        rideID.setBounds(50, 105, 100, 20);
        rideID.setForeground(Color.YELLOW); // Set color to yellow
        add(rideID);

        rideIDtext = new JTextField();
        rideIDtext.setBounds(150, 105, 150, 20);
        add(rideIDtext);

        //ADD Car Models Choices
        JLabel carModel = new JLabel("Car Model");
        carModel.setBounds(50, 145, 100, 20);
        carModel.setForeground(Color.yellow);
        add(carModel);

        carModelChoice = new Choice();
        carModelChoice.add("CHOOSE YOUR CAR");
        carModelChoice.add("Wagon r Stingray");
        carModelChoice.add("Wagon r FZ");
        carModelChoice.add("Premio 260 G Superior ");
        carModelChoice.add("Premio G");
        carModelChoice.add("Aqua G");
        carModelChoice.add("Aqua X");
        carModelChoice.add("Vitz Safety");
        carModelChoice.setBounds(150, 145, 150, 20);
        add(carModelChoice);

        //ADD pickuplocation label and textField
        JLabel pLocation = new JLabel("PickUp Location");
        pLocation.setBounds(50, 185, 100, 20);
        pLocation.setForeground(Color.YELLOW); // Set color to yellow
        add(pLocation);

        pickUpLocationtext = new JTextField();
        pickUpLocationtext.setBounds(150, 185, 150, 20);
        add(pickUpLocationtext);


        //ADD returnLocation label and textField
        JLabel rLocation = new JLabel("Return Location");
        rLocation.setBounds(50, 225, 100, 20);
        rLocation.setForeground(Color.YELLOW); // Set color to yellow
        add(rLocation);

        returnLocationtext = new JTextField();
        returnLocationtext.setBounds(150, 225, 150, 20);
        add(returnLocationtext);

        //ADD Number of Days label and textField
        JLabel numDates = new JLabel("Number Of Days");
        numDates.setBounds(50, 265, 100, 20);
        numDates.setForeground(Color.YELLOW); // Set color to yellow
        add(numDates);

        numOfDaysText = new JTextField();
        numOfDaysText.setBounds(150, 265, 150, 20);
        add(numOfDaysText);

        //ADD Number of Days label and textField
        JLabel toDay = new JLabel(" Date");
        toDay.setBounds(50, 305, 100, 20);
        toDay.setForeground(Color.YELLOW); // Set color to yellow
        add(toDay);

        daytext = new JTextField();
        daytext.setBounds(150, 305, 150, 20);
        add(daytext);

        //ADD Total and it's TextField
        JLabel total = new JLabel(" Total");
        total.setBounds(400, 70, 100, 20);
        total.setFont(new Font("Calibri", Font.BOLD, 20)); // Set font size and style
        total.setForeground(Color.YELLOW); // Set color to yellow
        add(total);

        totalText = new JTextField();
        totalText.setBounds(400, 100, 150, 40);
        add(totalText);


        //ADD to Buttons

        //Add to PaynowBtn

        payNowBtn = new JButton("Pay Now");
        payNowBtn.setBackground(new Color(66, 127, 219));
        payNowBtn.setForeground(Color.black);
        payNowBtn.setBounds(400, 230, 150, 25);
        payNowBtn.addActionListener(this);
        add(payNowBtn);

        //Add to CalculateBtn
        calculatebtn = new JButton("Calculate");
        calculatebtn.setBackground(new Color(66, 127, 219));
        calculatebtn.setForeground(Color.black);
        calculatebtn.setBounds(400, 200, 150, 25);
        calculatebtn.addActionListener(this);
        add(calculatebtn);


        //Add CancelBtn
        cancelBtn = new JButton("Cancel");
        cancelBtn.setBackground(new Color(66, 127, 219));
        cancelBtn.setForeground(Color.black);
        cancelBtn.setBounds(400, 260, 150, 25);
        cancelBtn.addActionListener(this);
        add(cancelBtn);

        //Add BackBtn
        backBtn = new JButton("Back");
        backBtn.setBackground(new Color(66, 127, 219));
        backBtn.setForeground(Color.black);
        backBtn.setBounds(400, 290, 150, 25);
        backBtn.addActionListener(this);
        add(backBtn);


        setSize(600, 380);
        setLocation(500, 200);
        setLayout(null);
        setVisible(true);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculatebtn) {

            java.util.Date currentDate = new java.util.Date();
            java.sql.Date sqlData = new java.sql.Date(currentDate.getTime());
            String Rday = sqlData.toString();


            //Database Connection
           String RID = rideIDtext.getText();
           //String Rday = daytext.getText();
           String Plocation = pickUpLocationtext.getText();
           String Rlocation = returnLocationtext.getText();
           String Total = totalText.getText();
           String NumOfDays = numOfDaysText.getText();
           String CusNIC = nicNumtext.getText();

           try {
               // Establish database connection
               String url = "jdbc:mysql://localhost:3306/Car_Rental_System_DB";
               String dbUsername = "root";
               String dbPassword = "20030905";
               Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);

               // Create a prepared statement
               String query = "INSERT INTO Ride (Ride_ID,Ride_Date,PickUp_Location,Return_Location,Total,Number_Of_Days,Customer_NIC) VALUES (?,?,?,?,?,?,?)";
               PreparedStatement preparedStatement = connection.prepareStatement(query);
               preparedStatement.setString(1, RID);
               preparedStatement.setDate(2, sqlData);
               preparedStatement.setString(3, Plocation);
               preparedStatement.setString(4, Rlocation);
               preparedStatement.setString(5, Total);
               preparedStatement.setString(6, NumOfDays);
               preparedStatement.setString(7, CusNIC);

               // Execute the statement
               int rowsAffected = preparedStatement.executeUpdate();
               if (rowsAffected > 0) {
                   JOptionPane.showMessageDialog(null, "Booking Details insert successfully!");

                   // Close resources
                   preparedStatement.close();
                   connection.close();

                   // Close BookingScreen and open PaymentScreen
                   //dispose(); // Close BookingScreen
                   //new PaymentScreen();
                   //return; // Exit actionPerformed method to prevent further execution
               } else {
                   JOptionPane.showMessageDialog(null, "Failed to insert data.");
               }

               // Close resources
               preparedStatement.close();
               connection.close();
           }catch (Exception ex) {
               ex.printStackTrace();
               JOptionPane.showMessageDialog(null, "Failed to Insert Data: " + ex.getMessage());
           }



           // Get the selected car model and number of days
            String selectedCarModel = carModelChoice.getSelectedItem();
            int numOfDays = Integer.parseInt(numOfDaysText.getText());

            // Calculate the total based on the selected car model and number of days
            int carPrice = 0;
            switch (selectedCarModel) {
                case "Wagon r Stingray":
                    carPrice = 35000;
                    break;
                case "Wagon r FZ":
                    carPrice = 45000;
                    break;
                case "Premio 260 G Superior":
                    carPrice = 55000;
                    break;
                case "Premio G":
                    carPrice = 65000;
                    break;
                case "Aqua G":
                    carPrice = 75000;
                    break;
                case "Aqua X":
                    carPrice = 85000;
                    break;
                case "Vitz Safety":
                    carPrice = 95000;
                    break;
                default:
                    // Handle default case or display an error message
                    break;
            }

            // Calculate the total cost
            double total = carPrice + (numOfDays * 500);
            double tax = total * 0.2;
            double totalAmount = total + tax;

            // Display the total in the totalText field
            totalText.setText(Double.toString(totalAmount));



            //Validation
            if (RID.isEmpty() && Rday.isEmpty() && Plocation.isEmpty() && Rlocation.isEmpty() && NumOfDays.isEmpty() && CusNIC.isEmpty())
            {
                JOptionPane.showMessageDialog(null,"Please Enter Your Details");

            }
            if (RID.isEmpty()){
                JOptionPane.showMessageDialog(null,"Please Enter Ride ID");

            }

            if (Plocation.isEmpty()){
                JOptionPane.showMessageDialog(null,"Please Enter Pickup Loction");

            }
            if (Rlocation.isEmpty()){
                JOptionPane.showMessageDialog(null,"Please Enter Return Location");

            }

            if (NumOfDays.isEmpty()){
                JOptionPane.showMessageDialog(null,"Please Enter Nimber Of Days");

            }
            if (CusNIC.isEmpty()){
                JOptionPane.showMessageDialog(null,"Please Enter NIC Number");

            }





        } else if (e.getSource() == backBtn) {
            setVisible(false);
            new HomeScreen();

        } else if (e.getSource() == cancelBtn) {
            nicNumtext.setText("");
            pickUpLocationtext.setText("");
            returnLocationtext.setText("");
            daytext.setText("");
            rideIDtext.setText("");
            numOfDaysText.setText("");
            totalText.setText("");
            //JTextField nicNumtext, pickUpLocationtext, returnLocationtext, datetext, rideIDtext, numOfDaysText, totalText;

        } else if (e.getSource() == payNowBtn) {
            setVisible(false);
            new PaymentScreen();
        }
    }

    public static void main(String[] args) {
        new BookingScreen();
    }
}
