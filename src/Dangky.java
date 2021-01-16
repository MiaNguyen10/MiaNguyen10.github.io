import javax.swing.*;

import java.awt.event.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.awt.*;


public class Dangky extends JFrame {
    //private final static String login = "Login.txt";
    // Swing component
    private Container container = new Container();
    JTextField username;
    JPasswordField password;
    
    public Dangky() {
        super("Register Form");
        init();
    }

    private void init() {
    	container = getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
       // this.getContentPane().setBackground(new Color(0x000000));
        //this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("D:/UEF1822/JavaTechnology/ApplicationJava/image/login.jpg")))));
        //===============render input section===============;
        JPanel inputSection = new JPanel(new FlowLayout(1, 0, 10));
        // userName
        JPanel userField = new JPanel(new FlowLayout(1, 10, 0));
        userField.add(new JLabel("User name:"));
        username = new JTextField(20);
        userField.add(username);

        // password     
        JPanel passField = new JPanel(new FlowLayout(1, 10, 0));
        passField.add(new JLabel("Password:"));
        password = new JPasswordField(20);
        passField.add(password);

        inputSection.add(userField);
        inputSection.add(passField);
        //===============render button section===============;
        JPanel buttonField = new JPanel(new FlowLayout(1, 20, 0));
        // confirm button
        JButton confirmButton = new JButton("Confirm");
        confirmButton.setBackground( new Color(0xCED16A));
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*String credential = username.getText() + "," + new String(password.getPassword());
                if (writeFile(credential)) {
                    JOptionPane.showMessageDialog(container, "Successfully create new user\nPlease Login again ", "Successful",
                            JOptionPane.PLAIN_MESSAGE);
                    setVisible(false);
                }
                else {
                    JOptionPane.showMessageDialog(container, "Cannot create new user", "Error",
                            JOptionPane.CANCEL_OPTION);
                }*/
            	
            	try 
        		{
        			Class.forName("com.mysql.cj.jdbc.Driver");
        			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/game","root","10062000ha");
        			String query= "Insert into login(acc,pass) values(?,?)";
        			String query2= "Insert into player(acc,score) values(?,?)";
        			PreparedStatement pst = conn.prepareStatement(query);
        			pst.setString(1, username.getText());
        			pst.setString(2, password.getText().toString());
        			
        			PreparedStatement pst2 = conn.prepareStatement(query2);
        			pst2.setString(1, username.getText());
        			pst2.setInt(2, '0');
        			pst.executeUpdate();
        			//conn.close();
        			JOptionPane.showMessageDialog(null,"Insert Successfully!" );
        			
        			setVisible(false);
        			
        		} catch (Exception e2) {e2.getStackTrace();}
            	
         }
            	
            
        });
        // Cancel Button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBackground( new Color(0xCED16A));
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        buttonField.add(confirmButton);
        buttonField.add(cancelButton);

        // Add to container
        container.add(inputSection);
        container.add(buttonField);

        renderWindow();
    }

    private void renderWindow() {
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }
    
    
}
