package CampSafe;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;





public class Register extends JFrame implements ActionListener{
	
	
	JButton backB;
	JButton registerB;
	JTextField name;
	JTextField contactNum;
	JTextField timeIn;
	JTextField timeOut;
	JTextField date;
	JTextField offV;
	int guestID;
	 Register(){
			
		JPanel regP = new JPanel();
		regP.setBounds(70, 50, 500, 40);
		regP.setOpaque(false);
		JLabel regL = new JLabel();
		regL.setText("Registration form");
		regP.add(regL);
		
		
        JPanel bgc = new JPanel();
		bgc.setBackground(Color.darkGray);
		bgc.setBounds(447, 0, 550, 600);
		
		
		
//NAME INPUT
		
			JPanel panel = new JPanel();
			panel.setBounds(50, 100, 500, 40);
			panel.setOpaque(false);
			JLabel label = new JLabel();
			label.setText("Name: ");		
			name = new JTextField();	
			name.setPreferredSize(new Dimension(200,20));
			name.setBounds(0, 0, 250, 20);
			panel.add(label);
			panel.add(name);
			
// Contact number INPUT	
			JPanel panel1 = new JPanel();
			panel1.setBounds(22, 150, 500, 40);
			panel1.setOpaque(false);
			JLabel label1 = new JLabel();
			label1.setText("Contact number: ");			
			contactNum = new JTextField();	
			contactNum.setPreferredSize(new Dimension(200,20));
			contactNum.setBounds(0, 0, 250, 20);
			panel1.add(label1);
			panel1.add(contactNum);
			
//time in INPUT
			
			JPanel panel2 = new JPanel();
			panel2.setBounds(45, 200, 500, 40);
			panel2.setOpaque(false);
			JLabel label2 = new JLabel();
			label2.setText("Time In: ");			
			timeIn = new JTextField();	
			timeIn.setPreferredSize(new Dimension(200,20));
			timeIn.setBounds(0, 0, 250, 20);
			panel2.add(label2);
			panel2.add(timeIn);
			
//time out INPUT
			
			JPanel panel3 = new JPanel();
			panel3.setBounds(40, 250, 500, 40);
			panel3.setOpaque(false);
			JLabel label3 = new JLabel();
			label3.setText("Time Out: ");			
			timeOut = new JTextField();	
			timeOut.setPreferredSize(new Dimension(200,20));
			timeOut.setBounds(0, 0, 250, 20);
			panel3.add(label3);
			panel3.add(timeOut);
			
			
//Date INPUT
			
			JPanel panel4 = new JPanel();
			panel4.setBounds(53, 300, 500, 40);
			panel4.setOpaque(false);
			JLabel label4 = new JLabel();
			label4.setText("Date: ");			
			date = new JTextField();	
			date.setPreferredSize(new Dimension(200,20));
			date.setBounds(0, 0, 250, 20);
			panel4.add(label4);
			panel4.add(date);
	
//Office to visit INPUT
			
			JPanel panel5 = new JPanel();
			panel5.setBounds(30, 350, 500, 40);
			panel5.setOpaque(false);
			JLabel label5 = new JLabel();
			label5.setText("Office to visit: ");			
			offV = new JTextField();	
			offV.setPreferredSize(new Dimension(200,20));
			panel5.add(label5);
			panel5.add(offV);
			
			
// back button INPUT
			JPanel panel6 = new JPanel();
			panel6.setBounds(150, 400, 100, 40);
			panel6.setOpaque(false);
			backB = new JButton("Back");
			backB.setFont(new Font("Verdana", Font.BOLD, 10));
			backB.setBounds(750, 500, 100, 100);
			backB.setFocusable(false);
			backB.addActionListener(this);
			panel6.add(backB);
			
			
// Register button INPUT		
			JPanel panel7 = new JPanel();
			panel7.setBounds(350, 400, 100, 40);
			panel7.setOpaque(false);
			registerB = new JButton("Register");
			registerB.setFont(new Font("Verdana", Font.BOLD, 10));
			registerB.setBounds(50, 500, 100, 100);
			registerB.setFocusable(false);
			registerB.addActionListener(this);
			panel7.add(registerB);
			
			

			
			this.setVisible(true);
			this.setSize(600,550);
			this.setResizable(false);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setTitle("CampSafe");
			this.setLayout(null);
//logo INPUT
			ImageIcon logo1 = new ImageIcon("1logo.png");
			this.setIconImage(logo1.getImage());
			
			this.add(regP);
			this.add(panel);
			this.add(panel1);
			this.add(panel2);
			this.add(panel3);
			this.add(panel4);
			this.add(panel5);
			this.add(panel6);
			this.add(panel7);
			
	}

	 @Override
	 public void actionPerformed(ActionEvent e) {
	     Object source = e.getSource();
	     if (source == registerB) {
	         String sname = name.getText().trim();
	         String scontactNum = contactNum.getText().trim();
	         String stimeIn = timeIn.getText().trim();
	         String stimeOut = timeOut.getText().trim();
	         String sdate = date.getText().trim();
	         String soffV = offV.getText().trim();

	         if (sname.isEmpty() || scontactNum.isEmpty() || stimeIn.isEmpty() || stimeOut.isEmpty() || sdate.isEmpty() || soffV.isEmpty()) {
	             JOptionPane.showMessageDialog(this, "Please fill in all the fields.");
	         } else {
	             try {
	                 // Open connection
	                 Class.forName("com.mysql.cj.jdbc.Driver");
	                 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/campsafe", "root", "");

	                 // Prepare statement for inserting data into the table
	                 String sql = "INSERT INTO guests (guestID, name, contact_number, time_in, time_out, visit_date, office_to_visit) VALUES (?, ?, ?, ?, ?, ?, ?)";
	                 PreparedStatement preparedStatement = con.prepareStatement(sql);

	                 // Set values for parameters in the prepared statement, including guestID
	                 preparedStatement.setInt(1, guestID);
	                 preparedStatement.setString(2, sname);
	                 preparedStatement.setString(3, scontactNum);
	                 preparedStatement.setString(4, stimeIn);
	                 preparedStatement.setString(5, stimeOut);
	                 preparedStatement.setString(6, sdate);
	                 preparedStatement.setString(7, soffV);
	                 // Execute the update
	                 preparedStatement.executeUpdate();

	                 // Close connection
	                 con.close();
	                 JOptionPane.showMessageDialog(this, "Registration Successful");
	                 this.dispose();
	                 new successReg();
	             } catch (ClassNotFoundException | SQLException ex) {
	                 ex.printStackTrace();
	                 JOptionPane.showMessageDialog(this, "Error in database operation");
	             }
	         }
	     } else if (source == backB) {
	         new Frame();
	         this.dispose();
	     }
	 }

		
	}
	

