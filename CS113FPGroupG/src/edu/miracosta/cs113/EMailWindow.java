package edu.miracosta.cs113;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.BorderLayout;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Dimension;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;

@SuppressWarnings("serial")
public class EMailWindow extends JFrame implements ActionListener{
	
	JPanel mainPanel;

	//TODO: add menu bar
	
	private JPanel infoPanel;
	
	private JLabel senderLabel;
	private JLabel subjectLabel;
	private JLabel dateLabel;
	
	private JTextField senderField;
	private JTextField subjectField;
	private JTextField dateField;
	
	private JPanel descriptionPanel;
	private JTextArea inputArea;
	private JScrollPane inputScroll;

	private JPanel buttonPanel;
	private JButton open;
	private JButton encrypt;
	private JButton decrypt;
	private JButton save;

	private JMenuBar menuBar;
	private JMenu fileMenuBar;
	private JMenu viewMenuBar;
	private JCheckBoxMenuItem wordWrapCheckbox;
	private JMenuItem openMenu;
	private JMenuItem encryptMenu;
	private JMenuItem decryptMenu;
	private JMenuItem saveMenu;
	
	public EMailWindow(){
		// Default window setup
		setPreferredSize(new Dimension(500, 400));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(200,270));
		
		mainPanel = new JPanel(new BorderLayout());
		getContentPane().add(mainPanel);
		
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
		open.addActionListener(this);
		buttonPanel.add(open);
		buttonPanel.add(encrypt);
		buttonPanel.add(decrypt);
		buttonPanel.add(save);
		SpringUtilities.makeGrid(buttonPanel, 4, 1, 5, 5, 5, 5);
		
		descriptionPanel.add(buttonPanel, BorderLayout.EAST);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		fileMenuBar = new JMenu("File");
		menuBar.add(fileMenuBar);
		
		openMenu = new JMenuItem("Open");
		openMenu.addActionListener(this);
		fileMenuBar.add(openMenu);
		
		encryptMenu = new JMenuItem("Encrypt");
		encryptMenu.addActionListener(this);
		fileMenuBar.add(encryptMenu);
		
		decryptMenu = new JMenuItem("Decrypt");
		decryptMenu.addActionListener(this);
		fileMenuBar.add(decryptMenu);
		
		saveMenu = new JMenuItem("Save");
		saveMenu.addActionListener(this);
		fileMenuBar.add(saveMenu);
		
		viewMenuBar = new JMenu("View");
		viewMenuBar.setActionCommand("Format");
		menuBar.add(viewMenuBar);
		
		wordWrapCheckbox = new JCheckBoxMenuItem("Word Wrap", true);
		wordWrapCheckbox.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				inputArea.setLineWrap(wordWrapCheckbox.isSelected());
			}
		});
		viewMenuBar.add(wordWrapCheckbox);
		
		pack();
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == open || e.getSource() == openMenu){
			
		}
		else if(e.getSource() == encrypt || e.getSource() == encryptMenu){
			
		}else if(e.getSource() == decrypt || e.getSource() == decryptMenu){
			
		}else if(e.getSource() == save || e.getSource() == saveMenu){
			
		}
	}
	
	public static void main(String args[]){
		@SuppressWarnings("unused")
		EMailWindow run = new EMailWindow();
	}
}
