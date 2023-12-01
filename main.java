package CampSafe;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main {
	 public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	            JFrame frame = new JFrame("CampSafe");
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.setSize(700, 600);

	            // Load the GIF as an ImageIcon
	            ImageIcon gifIcon = new ImageIcon("campsafe1.gif");

	            // Create a JLabel to display the GIF
	            JLabel gifLabel = new JLabel(gifIcon);

	            // Create and configure the progress bar
	            JProgressBar progressBar = new JProgressBar();
	            progressBar.setStringPainted(true);

	            // Create and configure the timer to simulate progress
	            Timer timer = new Timer(100, new ActionListener() {
	                int progress = 0;

	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    progress += 2;
	                    progressBar.setValue(progress);

	                    if (progress >= 100) {
	                        ((Timer) e.getSource()).stop();
	                        new Frame();
	                        frame.dispose();
	                    }
	                }
	            });

	            // Add components to the frame
	            frame.setLayout(new BorderLayout());
	            frame.add(gifLabel, BorderLayout.CENTER);

	            // Use a panel to group the progress bar
	            JPanel progressPanel = new JPanel();
	            progressPanel.setLayout(new BorderLayout());
	            progressPanel.add(progressBar, BorderLayout.NORTH);

	            frame.add(progressPanel, BorderLayout.SOUTH);

	            ImageIcon logo1 = new ImageIcon("1logo.png");
	            frame.setIconImage(logo1.getImage());
	            frame.setVisible(true);

	            // Start the timer when the program starts
	            progressBar.setValue(0);
	            timer.start();
	        });
	    }
}
