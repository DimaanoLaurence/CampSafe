package CampSafe;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Frame extends JFrame implements ActionListener {

    JButton button;
    JButton button1;
    JButton button2;
    JTable table; // Declare JTable

    Frame() {

        // Button
        button = new JButton("Register");
        button.setBounds(30, 450, 110, 20);
        button.setFont(new Font("Verdana", Font.CENTER_BASELINE, 15));
        button.setFocusable(false);
        button.addActionListener(this);

        button1 = new JButton("View");
        button1.setFont(new Font("Verdana", Font.CENTER_BASELINE, 15));
        button1.setBounds(185, 450, 110, 20);
        button1.setFocusable(false);
        button1.addActionListener(this);

        button2 = new JButton("Exit");
        button2.setFont(new Font("Verdana", Font.CENTER_BASELINE, 15));
        button2.setBounds(340, 450, 110, 20);
        button2.setFocusable(false);
        button2.addActionListener(this);

        JLabel label1 = new JLabel();
        label1.setSize(400, 400);
        ImageIcon logo = new ImageIcon("logonew.png");
        label1.setIcon(logo);

       
 // creating frame and modifying frame

        this.add(button);
        this.add(button1);
        this.add(button2);
        this.add(label1);

        this.setVisible(true);
        this.setSize(500, 600);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("CampSafe");
        ImageIcon logo1 = new ImageIcon("1logo.png");
        this.setIconImage(logo1.getImage());

        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Check if the source of the action is the "Register" button
        if (e.getSource() == button) {
            // Open a new instance of the Register class
            new Register();
            
            // Close the current frame
            this.dispose();
        }
        // Check if the source of the action is the "Search" button
        else if (e.getSource() == button1) {
            // Open a new instance of the Search class
            new Search();
            
            // Close the current frame
            this.dispose();
        }
        // Check if the source of the action is the "Exit" button
        else if (e.getSource() == button2) {
            // Display a confirmation dialog
            int option = JOptionPane.showConfirmDialog(this, "Are you sure?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
            
            // Check if the user clicked "Yes" in the confirmation dialog
            if (option == JOptionPane.YES_OPTION) {
                // Close the current frame
                this.dispose();
            }
        }
    }
}
