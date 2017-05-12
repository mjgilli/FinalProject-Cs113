package edu.miracosta.cs113;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class EMailWindow extends JFrame{
	
	JPanel mainPanel;
	
	JPanel infoPanel;
	JLabel senderLabel;
	JLabel subjectLabel;
	JLabel dateLabel;
	JTextField senderField;
	JTextField subjectField;
	JTextField dateField;
	
	JPanel descriptionPanel;
	JTextField inputField;

	JPanel buttonPanel;
	JButton open;
	JButton encrypt;
	JButton decrypt;
	JButton save;
	
	public EMailWindow(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(400, 300));
		
		mainPanel = new JPanel(new BorderLayout());
		add(mainPanel);
		
		infoPanel = new JPanel();
		mainPanel.add(infoPanel, BorderLayout.NORTH);
		
		descriptionPanel = new JPanel(new BorderLayout());
		mainPanel.add(descriptionPanel, BorderLayout.CENTER);
		
		buttonPanel = new JPanel();
		descriptionPanel.add(buttonPanel, BorderLayout.EAST);

		setVisible(true);
	}
	
	public static void main(String args[]){
		@SuppressWarnings("unused")
		EMailWindow run = new EMailWindow();
	}
}
