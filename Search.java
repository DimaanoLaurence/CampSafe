package CampSafe;

import java.awt.Color;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;

public class Search extends JFrame implements ActionListener {

    JButton b;
    JButton v;
    JButton s;
    JButton c;
    JTable table; // Declare JTable

    Search() {
    	JPanel guestPanel = new JPanel();
    	//guestPanel.setBackground(Color.gray);
    	guestPanel.setBounds(450, 40, 200, 50);
    	JLabel guest = new JLabel("GUEST HISTORY"); 
    	guest.setFont(new Font("Verdana", Font.BOLD, 20));
    	guestPanel.add(guest);
    	
        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, 1000, 420);
        mainPanel.setLayout(null);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(30, 50, 150, 300);
        buttonPanel.setLayout(null);

        // View All Button
        v = new JButton("View all");
        v.setFont(new Font("Verdana", Font.BOLD, 10));
        v.setBounds(10, 30, 100, 40);
        v.setFocusable(false);
        v.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/campsafe", "root", "");

 // Assuming you have a JTable named "table" in your class
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.setRowCount(0); // Clear existing rows

                    Statement stm = con.createStatement();
                    String query = "SELECT * FROM guests";

                    ResultSet rs = stm.executeQuery(query);
                    ResultSetMetaData rsmd = rs.getMetaData();

                    int cols = rsmd.getColumnCount();
                    String[] colName = new String[cols];
                    for (int i = 0; i < cols; i++)
                        colName[i] = rsmd.getColumnName(i + 1);
                    model.setColumnIdentifiers(colName);

                    String guestID;
					String name, contact_number, time_in, time_out, visit_date, office_to_visit;
                    while (rs.next()) {
                    	guestID = rs.getString(1);
                    	name = rs.getString(2);
                        contact_number = rs.getString(3);
                        time_in = rs.getString(4);
                        time_out = rs.getString(5);
                        visit_date = rs.getString(6);
                        office_to_visit = rs.getString(7);

                        String[] row = { guestID, name, contact_number, time_in, time_out, visit_date, office_to_visit };
                        model.addRow(row);
                    }
                    stm.close();
                    con.close();

                } catch (ClassNotFoundException | SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        
        buttonPanel.add(v);

// Search Button
        s = new JButton("Search");
        s.setFont(new Font("Verdana", Font.BOLD, 10));
        s.setBounds(10, 100, 100, 40);
        s.setFocusable(false);
        s.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputValue = JOptionPane.showInputDialog(null, "Please enter the Guest Name:", "Search", JOptionPane.QUESTION_MESSAGE);

                if (inputValue == null || inputValue.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a value!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {

// Open connection
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/campsafe", "root", "");

// For inserting data into the table
                        Statement stm = con.createStatement();

// Use PreparedStatement to avoid SQL injection
                        String query = "SELECT * FROM guests WHERE name LIKE ?";
                        try (java.sql.PreparedStatement preparedStatement = con.prepareStatement(query)) {
                            preparedStatement.setString(1, "%" + inputValue + "%");

  // Execute query
                            ResultSet rs = preparedStatement.executeQuery();
                            ResultSetMetaData rsmd = rs.getMetaData();
                            DefaultTableModel model = (DefaultTableModel) table.getModel();

                            int cols = rsmd.getColumnCount();
                            String[] colName = new String[cols];
                            for (int i = 0; i < cols; i++)
                                colName[i] = rsmd.getColumnName(i + 1);
                            model.setColumnIdentifiers(colName);

                            String guestID;
                            String name, contact_number, time_in, time_out, visit_date, office_to_visit;

                            boolean recordsFound = false; // Added flag to check if any records are found

                            while (rs.next()) {
                                guestID = rs.getString(1);
                                name = rs.getString(2);
                                contact_number = rs.getString(3);
                                time_in = rs.getString(4);
                                time_out = rs.getString(5);
                                visit_date = rs.getString(6);
                                office_to_visit = rs.getString(7);

                                String[] row = {guestID, name, contact_number, time_in, time_out, visit_date, office_to_visit};
                                model.addRow(row);

                                recordsFound = true; // Set flag to true since at least one record is found
                            }

 // Display a message if no records match the search criteria
                            if (!recordsFound) {
                                JOptionPane.showMessageDialog(null, "No matching guest(s) found!", "Not Found", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                        stm.close();
                        con.close();
                    } catch (ClassNotFoundException | SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        buttonPanel.add(s);

 // Back Button
        c = new JButton("Remove");
        c.setFont(new Font("Verdana", Font.BOLD, 10));
        c.setBounds(10, 170, 100, 40);
        c.setFocusable(false);
        c.addActionListener(new ActionListener() {
           // private Object dispose;

			@Override
            public void actionPerformed(ActionEvent e) {
            	if (e.getSource() ==  c) {
            		new Search();
            		dispose();
            		
                   
                }
            }

			});
       
        
        
        buttonPanel.add(c);
        
        
        
 // Back Button
        b = new JButton("Back");
        b.setFont(new Font("Verdana", Font.BOLD, 10));
        b.setBounds(10, 240, 100, 40);
        b.setFocusable(false);
        b.addActionListener(this);
        buttonPanel.add(b);     

 // Table panel
        JPanel tablePanel = new JPanel();
        tablePanel.setBounds(150, 50, 800, 350);
       
        tablePanel.setLayout(null);

// Initialize JTable
        table = new JTable();
        table.setBounds(50, 50, 700, 250);
        table.setOpaque(false);
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);

 // Add the table to a JScrollPane for scrolling if needed
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 50, 700, 250);
        tablePanel.add(scrollPane);

 // Logo
        ImageIcon logo1 = new ImageIcon("1logo.png");
        this.setIconImage(logo1.getImage());

 // Add panels to the main frame
        mainPanel.add(buttonPanel);
        mainPanel.add(tablePanel);
        
        
        this.add(guestPanel);
        this.add(mainPanel);

        this.setVisible(true);
        this.setSize(1000, 420);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("CampSafe");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b) {
            new Frame();
            this.dispose();
        }
    }
}
