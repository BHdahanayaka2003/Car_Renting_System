package Car_Renting_System;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SignUpScreen extends JFrame implements ActionListener, FocusListener {

    JTextField nicText, nameText, contactNumberText, emailText, userNameText, passWordText;
    JButton createButton, backButton;

    SignUpScreen() {
        super("Signup Page");
        getContentPane().setBackground(Color.darkGray);

        //NIC NUMBER Label and text field
        JLabel nicNumber = new JLabel("NIC Number");
        nicNumber.setBounds(300, 30, 125, 20);
        nicNumber.setForeground(Color.YELLOW);
        nicNumber.setVisible(true);
        add(nicNumber);

        nicText = new JTextField();
        nicText.setBounds(400, 30, 170, 20);
        nicText.setVisible(true);
        nicText.addFocusListener(this);
        add(nicText);

        //Name label and textfield
        JLabel Name = new JLabel("Name");
        Name.setBounds(300, 70, 125, 20);
        Name.setForeground(Color.YELLOW);
        Name.setVisible(true);
        add(Name);

        nameText = new JTextField();
        nameText.setBounds(400, 70, 170, 20);
        nameText.setVisible(true);
        nameText.addFocusListener(this);
        add(nameText);

        //ContactNumber label and textfield
        JLabel ContactNumber = new JLabel("Contact Number");
        ContactNumber.setBounds(300, 110, 125, 20);
        ContactNumber.setForeground(Color.YELLOW);
        ContactNumber.setVisible(true);
        add(ContactNumber);

        contactNumberText = new JTextField();
        contactNumberText.setBounds(400, 110, 170, 20);
        contactNumberText.setVisible(true);
        contactNumberText.addFocusListener(this);
        add(contactNumberText);

        //Email label and textfield
        JLabel Email = new JLabel("Email Address");
        Email.setBounds(300, 150, 125, 20);
        Email.setForeground(Color.YELLOW);
        Email.setVisible(true);
        add(Email);

        emailText = new JTextField();
        emailText.setBounds(400, 150, 170, 20);
        emailText.setVisible(true);
        emailText.addFocusListener(this);
        add(emailText);

        //UserName label and textfield
        JLabel UName = new JLabel("User Name");
        UName.setBounds(300, 190, 125, 20);
        UName.setForeground(Color.YELLOW);
        UName.setVisible(true);
        add(UName);

        userNameText = new JTextField();
        userNameText.setBounds(400, 190, 170, 20);
        userNameText.setVisible(true);
        userNameText.addFocusListener(this);
        add(userNameText);

        //password label and textfield
        JLabel PWord = new JLabel("Password");
        PWord.setBounds(300, 230, 125, 20);
        PWord.setForeground(Color.YELLOW);
        PWord.setVisible(true);
        add(PWord);

        passWordText = new JTextField();
        passWordText.setBounds(400, 230, 170, 20);
        passWordText.setVisible(true);
        passWordText.addFocusListener(this);
        add(passWordText);

        createButton = new JButton("Create");
        createButton.setBackground(new Color(66, 127, 219));
        createButton.setForeground(Color.black);
        createButton.setBounds(300, 270, 100, 25);
        createButton.addActionListener(this);
        add(createButton);

        backButton = new JButton("Back");
        backButton.setBackground(new Color(66, 127, 219));
        backButton.setForeground(Color.black);
        backButton.setBounds(470, 270, 100, 25);
        backButton.addActionListener(this);
        add(backButton);

        ImageIcon profileOne = new ImageIcon(ClassLoader.getSystemResource("login.png"));
        Image profileTwo = profileOne.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon fprofileOne = new ImageIcon(profileTwo);
        JLabel profileLabel = new JLabel(fprofileOne);
        add(profileLabel);
        profileLabel.setBounds(5, 3, 250, 250);

        setSize(600, 380);
        setLocation(500, 200);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createButton) {
            String nic = nicText.getText();
            String name = nameText.getText();
            String contact = contactNumberText.getText();
            String email = emailText.getText();
            String username = userNameText.getText();
            String password = passWordText.getText();

            try {
                // Establish database connection
                String url = "jdbc:mysql://localhost:3306/Car_Rental_System_DB";
                String dbUsername = "root";
                String dbPassword = "20030905";
                Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);

                // Create a prepared statement
                String query = "INSERT INTO Customer (Customer_NIC, Customer_Name, Contact_No, Email, Username, Customer_Password) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, nic);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, contact);
                preparedStatement.setString(4, email);
                preparedStatement.setString(5, username);
                preparedStatement.setString(6, password);

                // Execute the statement
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "User created successfully!");

                    // Close resources
                    preparedStatement.close();
                    connection.close();

                    // Close SignUpScreen and open HomeScreen
                    dispose(); // Close SignUpScreen
                    new HomeScreen();
                    return; // Exit actionPerformed method to prevent further execution
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to create user.");
                }

                // Close resources
                preparedStatement.close();
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Failed to create user: " + ex.getMessage());
            }
        } else if (e.getSource() == backButton) {
            setVisible(false);
            new HomeScreen();
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        ((JTextField) e.getSource()).selectAll();
    }

    @Override
    public void focusLost(FocusEvent e) {}

    public static void main(String[] args) {
        new SignUpScreen();
    }
}
