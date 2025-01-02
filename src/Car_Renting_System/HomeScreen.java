package Car_Renting_System;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HomeScreen extends JFrame implements ActionListener {
    JTextField userText, passwordText;
    JButton loginButton, cancelButton, signupButton;

    HomeScreen() {
        super("Home Screen");
        getContentPane().setBackground(Color.darkGray);

        JLabel username = new JLabel("Username");
        username.setBounds(300, 60, 100, 20);
        username.setForeground(Color.YELLOW); // Set color to yellow
        add(username);

        userText = new JTextField();
        userText.setBounds(400, 60, 150, 20);
        add(userText);

        JLabel password = new JLabel("Password");
        password.setBounds(300, 100, 100, 20);
        password.setForeground(Color.YELLOW);
        add(password);

        passwordText = new JPasswordField(); // Change to JPasswordField for password input
        passwordText.setBounds(400, 100, 150, 20);
        add(passwordText);

        loginButton = new JButton("Login");
        loginButton.setBounds(330, 180, 100, 20);
        loginButton.addActionListener(this);
        add(loginButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(460, 180, 100, 20);
        cancelButton.addActionListener(this);
        add(cancelButton);

        signupButton = new JButton("Signup");
        signupButton.setBounds(400, 215, 100, 20);
        signupButton.addActionListener(this);
        add(signupButton);

        ImageIcon profileOne = new ImageIcon(ClassLoader.getSystemResource("home.jpg"));
        Image profileTwo = profileOne.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon fprofileOne = new ImageIcon(profileTwo);
        JLabel profileLabel = new JLabel(fprofileOne);
        add(profileLabel);
        profileLabel.setBounds(5, 5, 250, 250);

        setSize(640, 300);
        setLocation(400, 200);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = userText.getText();
            String password = passwordText.getText();

            try {
                String url = "jdbc:mysql://localhost:3306/Car_Rental_System_DB";
                String dbUsername = "root";
                String dbPassword = "20030905";
                Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);

                String query = "SELECT * FROM Customer WHERE Username = ? AND Customer_Password = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    setVisible(false);
                    new BookingScreen();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password.");
                }

                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error occurred: " + ex.getMessage());
            }


            //Check username and password are both empty
            if (username.isEmpty() && password.isEmpty())
            {
                JOptionPane.showMessageDialog(null,"Please Enter User Name And Password");
                return;
            }
            //Check username Empty
            if (username.isEmpty())
            {
                JOptionPane.showMessageDialog(null,"Please Enter Username");
                return;
            }
            //Check password Empty
            if (password.isEmpty())
            {
                JOptionPane.showMessageDialog(null,"Please Enter password");
                return;
            }


        } else if (e.getSource() == signupButton) {
            setVisible(false);
            new SignUpScreen();
        } else if (e.getSource() == cancelButton) {
            userText.setText("");
            passwordText.setText("");
        }
    }

    public static void main(String[] args) {
        new HomeScreen();
    }
}
