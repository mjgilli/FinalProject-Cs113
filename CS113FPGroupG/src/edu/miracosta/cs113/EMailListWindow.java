package edu.miracosta.cs113;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import externalClass.ExitAction;

import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;

/**
 * GUI to show the list of the EMails.
 * @author Ryo Kanda <rensakou.touhou@gmail.com>
 * @version 1.0
 *
 */
@SuppressWarnings("serial")
public class EMailListWindow extends JFrame implements ActionListener, MouseListener{

	private JMenuItem reloadMenu;
	private JMenuItem exitMenu;
	private JMenuItem resetSortMenu;
	
	private JPanel topPanel;
	private JButton newButton;
	
	private JPanel sortPanel;
	private JPanel sortButtonPanel;
	private JLabel sortLabel;
	private JRadioButton senderSort;
	private JRadioButton senderSortR;
	private JRadioButton subjectSort;
	private JRadioButton subjectSortR;
	private JRadioButton dateSort;
	private JRadioButton dateSortR;
	private ButtonGroup radioButtons;
	
	private JPanel listPanel;
	private DefaultTableModel tableModel;
	private JTable eMailTable;
	private JScrollPane scrollTable;
	
	private String eMailFiles[];	// list of files in .\EMails
	private EMail eMailList[];		// list of EMails
	private String eMailData[][];	// data of EMails, used for JTable
	private HashMap<EMail, File> eMailPath;	// path for EMails
	
	private static final File eMailsDirectory = new File(".\\EMails");
	private JMenuItem newButtonMenu;

	/**
	 * Create window.
	 */
	public EMailListWindow() {
		setMinimumSize(new Dimension(500, 450));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		setTitle("CS113 Group G FP EMail Client");
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		JMenu viewMenu = new JMenu("View");
		menuBar.add(viewMenu);

		newButtonMenu = new JMenuItem("New");
		newButtonMenu.addActionListener(this);
		fileMenu.add(newButtonMenu);
		
		reloadMenu = new JMenuItem("Reload");
		reloadMenu.addActionListener(this);
		fileMenu.add(reloadMenu);
		
		ExitAction ea = new ExitAction();
		ea.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control x"));
		exitMenu = new JMenuItem(ea);
		fileMenu.add(exitMenu);
		
		resetSortMenu = new JMenuItem("Reset sort");
		resetSortMenu.addActionListener(this);
		viewMenu.add(resetSortMenu);
		
		topPanel = new JPanel();
		newButton = new JButton("New");
		newButton.addActionListener(this);
		newButton.setVisible(false);
		topPanel.add(newButton);
		
		sortPanel = new JPanel(new FlowLayout());
		sortLabel = new JLabel("Sort by:");
		sortPanel.add(sortLabel);
		sortButtonPanel = new JPanel(new GridLayout(2, 3));
		senderSort = new JRadioButton("Sender(Ascending)");
		senderSortR = new JRadioButton("Sender(Descending)");
		subjectSort = new JRadioButton("Subject(Ascending)");
		subjectSortR = new JRadioButton("Subject(Descending");
		dateSort = new JRadioButton("Date(Ascending)", true);
		dateSortR = new JRadioButton("Date(Descending)");
		radioButtons = new ButtonGroup();
		senderSort.addActionListener(this);
		senderSortR.addActionListener(this);
		subjectSort.addActionListener(this);
		subjectSortR.addActionListener(this);
		dateSort.addActionListener(this);
		dateSortR.addActionListener(this);
		radioButtons.add(senderSort);
		radioButtons.add(senderSortR);
		radioButtons.add(subjectSort);
		radioButtons.add(subjectSortR);
		radioButtons.add(dateSort);
		radioButtons.add(dateSortR);
		sortButtonPanel.add(senderSort);
		sortButtonPanel.add(subjectSort);
		sortButtonPanel.add(dateSort);
		sortButtonPanel.add(senderSortR);
		sortButtonPanel.add(subjectSortR);
		sortButtonPanel.add(dateSortR);
		sortPanel.add(sortButtonPanel);
		topPanel.add(sortPanel);
		getContentPane().add(topPanel, BorderLayout.NORTH);
		
		listPanel = new JPanel();
		listPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		listPanel.setLayout(new BorderLayout(0, 0));
		getContentPane().add(listPanel,BorderLayout.CENTER);
		
		String[] columns = {"Sender", "Subject", "Date"};
		tableModel = new DefaultTableModel(columns, 0){
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		loadEMails();
		setInitialSort();
		eMailTable = new JTable(tableModel);
		eMailTable.addMouseListener(this);
		scrollTable = new JScrollPane(eMailTable);
		listPanel.add(scrollTable, BorderLayout.CENTER);
	}
	
	/**
	 * Loads EMails from folder, and update the table.
	 */
	@SuppressWarnings("unused")
	private void loadEMails(){
		eMailFiles = eMailsDirectory.list(new EMailFilter());	// get all .txt files
		if(eMailFiles == null){
			try{
				new File("EMails\\temporary.txt").createNewFile();
				JOptionPane.showMessageDialog(this, "No files found in "+eMailsDirectory.getAbsolutePath()+"\nCreated temporary file.");
			}catch(Exception e){}
		}
		
		eMailData = new String[eMailFiles.length][3];	// 0:sender 1:subject 2:date 3:message
		eMailList = new EMail[eMailFiles.length];
		eMailPath = new HashMap<EMail, File>();
		
		for(int i=0; i<eMailFiles.length; i++){
			// try .txt -> EMail[] eMailList
			File file = new File(eMailsDirectory+"\\"+eMailFiles[i]);
			BufferedReader read = null;
			String sender = "";
			String subject = "";
			String rawDateString = "";
			String[] dateString = null;
			StringBuilder message;
			try{
				read = new BufferedReader(new FileReader(file));
				sender = read.readLine();
				subject = read.readLine();
				rawDateString = read.readLine();
				dateString = rawDateString.split(" ");
				Date date = new Date(dateString[0], Integer.parseInt(dateString[1].substring(0, dateString[1].length()-1)), Integer.parseInt(dateString[2]));
				message = new StringBuilder("");
				String nextLine = read.readLine();
				while(nextLine != null){
					message.append(nextLine);
					nextLine = read.readLine();
					if(nextLine != null){
						message.append("\n");
					}
				}
				if(message == null && message.toString().equals("")){
					eMailList[i] = new EMail(sender, subject, date, "");
				}
				else{
					eMailList[i] = new EMail(sender, subject, date, message.toString());
				}
				read.close();
			}catch(Exception e){
				JOptionPane.showMessageDialog(this, "file: "+file.getAbsolutePath()+" corrupted!\nShowing as [CORRUPTED] file.");
				StringBuilder corrupted = new StringBuilder("");
				corrupted.append(sender+"\n"+subject+"\n"+rawDateString);
				String nextLine = "";
				try{
					nextLine = read.readLine();
					while(nextLine != null){
						corrupted.append(nextLine);
						nextLine = read.readLine();
						if(nextLine != null){
							corrupted.append("\n");
						}
					}
				}catch(Exception ex){}
				eMailList[i] = new EMail("[CORRUPTED]("+eMailFiles[i]+")", "[CORRUPTED]", new Date(12, 31, 9999), corrupted.toString());
				try{read.close();}catch(Exception ee){}
			}
			finally{
				eMailPath.put(eMailList[i], new File(eMailsDirectory+"\\"+eMailFiles[i]));
			}
		}
		updateList();
	}
	
	/**
	 * Sort list as default sort when window created.
	 */
	private void setInitialSort(){
		EMail.mergeSort(eMailList, 'D');
		updateList();
	}
	
	/**
	 * Updates the JTable
	 */
	private void updateList(){
		tableModel.setRowCount(0);
		for(int i=0; i<eMailList.length; i++){
			eMailData[i][0] = eMailList[i].getSender();
			eMailData[i][1] = eMailList[i].getSubject();
			eMailData[i][2] = eMailList[i].getDate().toString();
			
			tableModel.addRow(eMailData[i]);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getClickCount() == 2){
			EMail selected = eMailList[eMailTable.getSelectedRow()];
			@SuppressWarnings("unused")
			EMailWindow open = new EMailWindow(selected, eMailPath.get(selected));
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == reloadMenu){
			loadEMails();
			setInitialSort();
		}

		else if(e.getSource() == senderSort || e.getSource() == senderSortR){
			EMail.mergeSort(eMailList, 'A');
			if(e.getSource() == senderSortR){
				Collections.reverse(Arrays.asList(eMailList));
			}
			updateList();
		}
		
		else if(e.getSource() == subjectSort || e.getSource() == subjectSortR){
			EMail.mergeSort(eMailList,  'B');
			if(e.getSource() == subjectSortR){
				Collections.reverse(Arrays.asList(eMailList));
			}
			updateList();
		}
		
		else if(e.getSource() == dateSort || e.getSource() == dateSortR){
			EMail.mergeSort(eMailList, 'C');
			if(e.getSource() == dateSortR){
				Collections.reverse(Arrays.asList(eMailList));
			}
			updateList();
		}
		
		else if(e.getSource() == resetSortMenu){
			dateSort.setSelected(true);
			tableModel.setRowCount(0);
			EMail.mergeSort(eMailList, 'D');
			updateList();
		}
		
		else if(e.getSource() == newButton || e.getSource() == newButtonMenu){
			@SuppressWarnings("unused")
			EMailWindow newWindow = new EMailWindow();
		}
	}

	/**
	 * GUI tester.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EMailListWindow frame = new EMailListWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

/**
 * EMailFilter	Filters file with given directory that matches file with ".txt" (Simply, finds .txt file in the given directory)
 * @author Ryo Kanda <rensakou.touhou@gmail.com>
 *
 */
class EMailFilter implements FilenameFilter{

	@Override
	public boolean accept(File directory, String name) {
		int extensionIndex = name.lastIndexOf(".");
		String extension = name.substring(extensionIndex+1).toLowerCase();
		
		return extension.equals("txt");
	}
	
}
