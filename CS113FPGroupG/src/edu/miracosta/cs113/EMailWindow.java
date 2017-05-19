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
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;

/**
 * GUI to show the window for EMail.
 * @author Ryo Kanda <rensakou.touhou@gmail.com>
 * @version 0.91
 *
 */
@SuppressWarnings({ "serial", "unused" })
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
	private JButton open;
	private JButton save;

	private JMenuBar menuBar;
	private JMenu fileMenuBar;
	private JMenu viewMenuBar;
	private JCheckBoxMenuItem wordWrapCheckbox;
	private JMenuItem encryptMenu;
	private JMenuItem decryptMenu;
	private JMenuItem openMenu;
	private JMenuItem saveMenu;
	private JMenuItem exitMenu;
	
	private boolean saveChanged;	// used to ask for save changes made, but disabled (save not implemented)
	
	/**
	 * Create window
	 */
	public EMailWindow(){
		saveChanged = false;
		// Default window setup
		setPreferredSize(new Dimension(500, 400));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);
		setBounds(150, 150, 450, 300);
		setMinimumSize(new Dimension(200,270));
		
		mainPanel = new JPanel(new BorderLayout());
		getContentPane().add(mainPanel);
		
		// InfoPanel setup
		infoPanel = new JPanel(new SpringLayout());
		senderLabel = new JLabel("Sender:", SwingConstants.RIGHT);
		infoPanel.add(senderLabel, BorderLayout.WEST);

		senderField = new JTextField();
		senderField.setEditable(false);
		senderField.getDocument().addDocumentListener(this);
		infoPanel.add(senderField, BorderLayout.CENTER);
		
		subjectLabel = new JLabel("Subject:", SwingConstants.RIGHT);
		infoPanel.add(subjectLabel, BorderLayout.WEST);

		subjectField = new JTextField();
		subjectField.setEditable(false);
		subjectField.getDocument().addDocumentListener(this);
		infoPanel.add(subjectField, BorderLayout.CENTER);
		
		dateLabel = new JLabel("Date:", SwingConstants.RIGHT);
		infoPanel.add(dateLabel, BorderLayout.WEST);
		
		dateField = new JTextField();
		dateField.setEditable(false);
		dateField.getDocument().addDocumentListener(this);
		infoPanel.add(dateField, BorderLayout.CENTER);

		SpringUtilities.makeCompactGrid(infoPanel, 3, 2, 5, 5, 5, 5);
		
		mainPanel.add(infoPanel, BorderLayout.NORTH);
		
		// DescriptionPanel setup
		descriptionPanel = new JPanel(new BorderLayout());
		inputArea = new JTextArea();
		inputArea.setEditable(false);
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
		decrypt = new JButton("Decrypt");
		open = new JButton("Open");
		open.setVisible(false);		// disabled
		save = new JButton("Save");
		save.setVisible(false);		// disabled
		open.addActionListener(this);
		buttonPanel.add(encrypt);
		buttonPanel.add(decrypt);
		buttonPanel.add(open);
		buttonPanel.add(save);
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
		
		openMenu = new JMenuItem("Open");
		openMenu.setVisible(false);		// disabled
		openMenu.addActionListener(this);
		fileMenuBar.add(openMenu);
		
		saveMenu = new JMenuItem("Save");
		saveMenu.setVisible(false);		// disabled
		saveMenu.addActionListener(this);
		fileMenuBar.add(saveMenu);
		
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
		
		pack();
		setVisible(true);
	}
	
	/**
	 * Creates window with given EMail object.
	 * @param openEMail
	 */
	public EMailWindow(EMail openEMail){
		EMailWindow windowWithParams = new EMailWindow();
		windowWithParams.senderField.setText(openEMail.getSender());
		windowWithParams.subjectField.setText(openEMail.getSubject());
		windowWithParams.dateField.setText(openEMail.getDate().toString());
		windowWithParams.inputArea.setText(openEMail.getMsg());
		saveChanged = false;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == open || e.getSource() == openMenu){	// open button no need?
		}
		else if(e.getSource() == encrypt || e.getSource() == encryptMenu){
			//String rawText = inputArea.getText();
			//String encrypted = callEncryptMethod
			//inputArea.setText(encrypted);
		}
		else if(e.getSource() == decrypt || e.getSource() == decryptMenu){
			//String encrypted = inputArea.getText();
			//String decrypted = callEncryptMethod
			//inputArea.setText(decrypted);
		}
		else if(e.getSource() == save || e.getSource() == saveMenu){	// save button no need?
			saveChanged = false;
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		saveChanged = true;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		saveChanged = true;
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
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
		/* implemented, but will not be used
		if(saveChanged){
			int result = JOptionPane.showConfirmDialog(this, "Do you want to save changes?", null, JOptionPane.YES_NO_CANCEL_OPTION);
			switch(result){
			case -1:	// Message window closed by X, treat as cancel
				return;
			case 0:	//yes
				saveChanged = true;
				break;
			case 1:	//no
				super.dispose();
				break;
			case 2:	//cancel
				return;
			}
		}
		*/
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
	public static void main(String args[]){
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
	}
}
