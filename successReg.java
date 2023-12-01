package CampSafe;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class successReg extends JFrame implements ActionListener {

    JButton home;

    // Constructor for SuccessReg class
    successReg() {
        // Panel for success message
        JPanel successPanel = new JPanel();
        successPanel.setBounds(0, 200, 650, 50);
        JLabel successLabel = new JLabel("You successfully registered in our digital logbook!");
        successLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        successPanel.add(successLabel);

        // Panel for home button
        JPanel homePanel = new JPanel();
        homePanel.setBounds(0, 250, 650, 50);

        // Home button
        home = new JButton("Home");
        home.setFont(new Font("Verdana", Font.BOLD, 10));
        home.setBounds(20, 60, 150, 50);
        home.setFocusable(false);
        home.addActionListener(this);
        homePanel.add(home);

        // Set frame properties
        this.setVisible(true);
        this.setSize(700, 500);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("CampSafe");
        this.setLayout(null);

        // Set the application icon
        ImageIcon logo1 = new ImageIcon("1logo.png");
        this.setIconImage(logo1.getImage());

        // Add panels to the main frame
        this.add(successPanel);
        this.add(homePanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Check if the source of the action is the "Home" button
        if (e.getSource() == home) {
            // Close the current frame and open the main frame (Frame class)
            this.dispose();
            new Frame();
        }
    }
}
