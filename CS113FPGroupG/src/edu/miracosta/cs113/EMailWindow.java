package edu.miracosta.cs113;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import externalClass.ExitAction;
import externalClass.SpringUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.awt.Dimension;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;

/**
 * GUI to show the window for EMail.
 * @author Ryo Kanda <rensakou.touhou@gmail.com>
 * @version 1.0
 *
 */
@SuppressWarnings("serial")
public class EMailWindow extends JFrame implements ActionListener, ItemListener, DocumentListener, WindowListener{
	
	private JPanel mainPanel;
	
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
	private JButton encrypt;
	private JButton decrypt;
	private JButton save;
	private JButton saveAs;

	private JMenuBar menuBar;
	private JMenu fileMenuBar;
	private JMenu viewMenuBar;
	private JCheckBoxMenuItem wordWrapCheckbox;
	private JMenuItem encryptMenu;
	private JMenuItem decryptMenu;
	private JMenuItem saveMenu;
	private JMenuItem saveAsMenu;
	private JMenuItem exitMenu;
	
	private boolean saveChanged;	// used to ask for save changes made, but disabled (save not implemented)
	private File currentFile;
	
	private EncryptionTree<ModCharacter> encryptor;
	/**
	 * Create window
	 */
	public EMailWindow(){
		saveChanged = false;
		currentFile = null;
		// Default window setup
		setPreferredSize(new Dimension(500, 400));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);
		setBounds(150, 150, 450, 300);
		setMinimumSize(new Dimension(200,270));
		setTitle("New EMail");
		
		mainPanel = new JPanel(new BorderLayout());
		getContentPane().add(mainPanel);
		
		// InfoPanel setup
		infoPanel = new JPanel(new SpringLayout());
		senderLabel = new JLabel("Sender:", SwingConstants.RIGHT);
		infoPanel.add(senderLabel, BorderLayout.WEST);

		senderField = new JTextField();
		senderField.getDocument().addDocumentListener(this);
		infoPanel.add(senderField, BorderLayout.CENTER);
		
		subjectLabel = new JLabel("Subject:", SwingConstants.RIGHT);
		infoPanel.add(subjectLabel, BorderLayout.WEST);

		subjectField = new JTextField();
		subjectField.getDocument().addDocumentListener(this);
		infoPanel.add(subjectField, BorderLayout.CENTER);
		
		dateLabel = new JLabel("Date:", SwingConstants.RIGHT);
		infoPanel.add(dateLabel, BorderLayout.WEST);
		
		SimpleDateFormat initial = new SimpleDateFormat("MMMM d, yyyy");
		java.util.Date current = new java.util.Date();
		dateField = new JTextField(initial.format(current));
		dateField.getDocument().addDocumentListener(this);
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
		inputArea.getDocument().addDocumentListener(this);
		descriptionPanel.add(inputScroll, BorderLayout.CENTER);
		mainPanel.add(descriptionPanel, BorderLayout.CENTER);
		
		// ButtonPanel setup
		buttonPanel = new JPanel(new SpringLayout());
		encrypt = new JButton("Encrypt");
		encrypt.addActionListener(this);
		decrypt = new JButton("Decrypt");
		decrypt.addActionListener(this);
		save = new JButton("Save");
		save.addActionListener(this);
		save.setEnabled(false);
		saveAs = new JButton("Save As...");
		saveAs.addActionListener(this);
		buttonPanel.add(encrypt);
		buttonPanel.add(decrypt);
		buttonPanel.add(save);
		buttonPanel.add(saveAs);
		SpringUtilities.makeGrid(buttonPanel, 4, 1, 5, 5, 5, 5);
		descriptionPanel.add(buttonPanel, BorderLayout.EAST);
		
		// MenuBar setup
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		fileMenuBar = new JMenu("File");
		menuBar.add(fileMenuBar);
		
		encryptMenu = new JMenuItem("Encrypt");
		encryptMenu.addActionListener(this);
		fileMenuBar.add(encryptMenu);
		
		decryptMenu = new JMenuItem("Decrypt");
		decryptMenu.addActionListener(this);
		fileMenuBar.add(decryptMenu);
		
		saveMenu = new JMenuItem("Save");
		saveMenu.addActionListener(this);
		saveMenu.setEnabled(false);
		fileMenuBar.add(saveMenu);

		saveAsMenu = new JMenuItem("Save As...");
		saveAsMenu.addActionListener(this);
		fileMenuBar.add(saveAsMenu);
		
		ExitAction ea = new ExitAction();
		ea.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control x"));
		exitMenu = new JMenuItem(ea);
		fileMenuBar.add(exitMenu);
		
		viewMenuBar = new JMenu("View");
		viewMenuBar.setActionCommand("Format");
		menuBar.add(viewMenuBar);
		
		wordWrapCheckbox = new JCheckBoxMenuItem("Word Wrap", true);
		wordWrapCheckbox.addItemListener(this);
		viewMenuBar.add(wordWrapCheckbox);
		
		encryptor = new EncryptionTree<ModCharacter>();
		
		pack();
		setVisible(true);
	}
	
	/**
	 * Creates window with given EMail object.
	 * @param openEMail
	 */
	public EMailWindow(EMail openEMail, File currentFile){
		EMailWindow windowWithParams = new EMailWindow();
		windowWithParams.senderField.setText(openEMail.getSender());
		windowWithParams.subjectField.setText(openEMail.getSubject());
		windowWithParams.dateField.setText(openEMail.getDate().toString());
		windowWithParams.inputArea.setText(openEMail.getMsg());
		windowWithParams.currentFile = currentFile;
		windowWithParams.saveChanged = false;
		windowWithParams.save.setEnabled(false);
		windowWithParams.saveMenu.setEnabled(false);
		windowWithParams.setTitle(currentFile.getPath());
	}
	
	/**
	 * Saves EMail to file. If not saved yet, calls saveAs().
	 */
	public void save(){
		if(currentFile != null){
			try{
				PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(currentFile)));
				writer.println(senderField.getText());
				writer.println(subjectField.getText());
				writer.println(dateField.getText());
				String[] rawInputArea = inputArea.getText().split("\n");
				for(int i=0; i<rawInputArea.length; i++){
					writer.print(rawInputArea[i]);
					if(i != rawInputArea.length-1){
						writer.println();
					}
				}
				writer.close();
			}catch(Exception e){
				JOptionPane.showMessageDialog(this, "Could not save to \""+currentFile.getAbsolutePath()+"\"");
				e.printStackTrace();
			}
		}
		else{
			saveAs();
			return;
		}
		setTitle(currentFile.getPath());
		JOptionPane.showMessageDialog(this, "EMail has been saved.");
	}
	
	/**
	 * Saves EMail to file with different name. Called by save() if not saved yet. Checks equality of last saved file, and checks already existing file.
	 */
	public void saveAs(){
		boolean saveLocationConfirmed = false;
		String rawNameInput = "";
		while(!saveLocationConfirmed){
			rawNameInput = JOptionPane.showInputDialog(this, "Save As...", rawNameInput);
			
			if(rawNameInput == null){	// Cancel
				return;
			}
			else if(rawNameInput.equals("")){	// empty input
				continue;	// I won't let you (repeat while)
			}
			
			if(rawNameInput.indexOf(".txt") == -1){	// input without .txt extension
				rawNameInput += ".txt";
			}
			
			if(currentFile != null && currentFile.getAbsolutePath().equals(new File(".\\EMails").getAbsolutePath()+"\\"+rawNameInput)){	// same as current editing file
				saveLocationConfirmed = true;
			}
			else{	// saving to different file
				File checkExistence = new File(".\\EMails\\"+rawNameInput);
				if(checkExistence.exists()){	// if file already exists
					int selection = JOptionPane.showConfirmDialog(this, rawNameInput+" already exists. Save it?", null, JOptionPane.YES_NO_OPTION);
					if(selection == 0){
						saveLocationConfirmed = true;
					}
				}
				else{	// completely new file
					saveLocationConfirmed = true;
				}
			}
		}
		currentFile = new File(".\\EMails\\"+rawNameInput);
		save();
		saveChanged = false;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == encrypt || e.getSource() == encryptMenu){
			StringBuilder sb = new StringBuilder();
			try{
				for(String rawInput : inputArea.getText().split("\\n")){
					if(!sb.toString().equals("")){
						sb.append("\n");
					}
					for(int i=0; i<rawInput.length(); i++){
						char c = rawInput.charAt(i);
						sb.append(encryptor.encode(c));
					}
				}
				inputArea.setText(sb.toString().substring(0, sb.length()-1));
			}catch(Exception ex){}
		}
		
		else if(e.getSource() == decrypt || e.getSource() == decryptMenu){
			StringBuilder sb = new StringBuilder();
			for(String rawInput : inputArea.getText().split("\n")){
				String[] encoded = rawInput.split(" ");
				String decoded = "";
				if(!sb.toString().equals("")){
					sb.append("\n");
				}
				try{
					for(int i=0; i<encoded.length; i++){
						decoded += Character.toString(encryptor.decode(encoded[i]));
					}
					sb.append(decoded);
				}catch(Exception ex){}
			}
			inputArea.setText(sb.toString());
		}
		
		else if(e.getSource() == save || e.getSource() == saveMenu){
			save();
			save.setEnabled(saveChanged);
			saveMenu.setEnabled(saveChanged);
		}
		
		else if(e.getSource() == saveAs || e.getSource() == saveAsMenu){
			saveAs();
			save.setEnabled(saveChanged);
			saveMenu.setEnabled(saveChanged);
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		if(!saveChanged){
			setTitle("*"+getTitle());
			save.setEnabled(true);
			saveMenu.setEnabled(true);
		}
		saveChanged = true;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		if(!saveChanged){
			setTitle("*"+getTitle());
			save.setEnabled(true);
			saveMenu.setEnabled(true);
		}
		saveChanged = true;
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		if(!saveChanged){
			setTitle("*"+getTitle());
			save.setEnabled(true);
			saveMenu.setEnabled(true);
		}
		saveChanged = true;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		inputArea.setLineWrap(wordWrapCheckbox.isSelected());
	}

	@Override
	public void windowActivated(WindowEvent e) {}
	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) {
		if(saveChanged){
			int result = JOptionPane.showConfirmDialog(this, "Do you want to save changes?", null, JOptionPane.YES_NO_CANCEL_OPTION);
			switch(result){
			case 0:	//yes
				save();
				saveChanged = false;
				break;
			case 1:	//no
				super.dispose();
				break;
			case -1:// Message window closed by X, treat as cancel
			case 2:	//cancel
				return;
			}
		}
		super.dispose();
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {}
	@Override
	public void windowDeiconified(WindowEvent e) {}
	@Override
	public void windowIconified(WindowEvent e) {}
	@Override
	public void windowOpened(WindowEvent e) {
	}
	
	/**
	 * GUI tester.
	 */
	/*public static void main(String args[]){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EMailWindow frame = new EMailWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
}
