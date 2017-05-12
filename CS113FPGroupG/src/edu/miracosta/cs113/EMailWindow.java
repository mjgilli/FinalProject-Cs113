package edu.miracosta.cs113;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

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
	JTextArea inputArea;
	JScrollPane inputScroll;

	JPanel buttonPanel;
	JButton open;
	JButton encrypt;
	JButton decrypt;
	JButton save;
	
	public EMailWindow(){
		// Default window setup
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(200,250));
		
		mainPanel = new JPanel(new BorderLayout());
		add(mainPanel);
		
		// InfoPanel setup
		infoPanel = new JPanel(new SpringLayout());
		senderLabel = new JLabel("Sender:", SwingConstants.RIGHT);
		infoPanel.add(senderLabel, BorderLayout.WEST);

		senderField = new JTextField();
		infoPanel.add(senderField, BorderLayout.CENTER);
		
		subjectLabel = new JLabel("Subject:", SwingConstants.RIGHT);
		infoPanel.add(subjectLabel, BorderLayout.WEST);

		subjectField = new JTextField();
		infoPanel.add(subjectField, BorderLayout.CENTER);
		
		dateLabel = new JLabel("Date:", SwingConstants.RIGHT);
		infoPanel.add(dateLabel, BorderLayout.WEST);
		
		dateField = new JTextField();
		infoPanel.add(dateField, BorderLayout.CENTER);

		SpringUtilities.makeCompactGrid(infoPanel, 3, 2, 5, 5, 5, 5);
		
		mainPanel.add(infoPanel, BorderLayout.NORTH);
		
		// DescriptionPanel setup
		descriptionPanel = new JPanel(new BorderLayout());
		inputArea = new JTextArea();
		inputScroll = new JScrollPane(inputArea);
		inputArea.setLineWrap(true);
		EtchedBorder border = new EtchedBorder(EtchedBorder.RAISED);
		inputArea.setBorder(border);
		descriptionPanel.add(inputScroll, BorderLayout.CENTER);
		mainPanel.add(descriptionPanel, BorderLayout.CENTER);
		
		// ButtonPanel setup
		buttonPanel = new JPanel(new SpringLayout());
		open = new JButton("Open");
		encrypt = new JButton("Encrypt");
		decrypt = new JButton("Decrypt");
		save = new JButton("Save");
		buttonPanel.add(open);
		buttonPanel.add(encrypt);
		buttonPanel.add(decrypt);
		buttonPanel.add(save);
		SpringUtilities.makeGrid(buttonPanel, 4, 1, 5, 5, 5, 5);
		
		descriptionPanel.add(buttonPanel, BorderLayout.EAST);

		setVisible(true);
	}
	
	public static void main(String args[]){
		@SuppressWarnings("unused")
		EMailWindow run = new EMailWindow();
	}
}
