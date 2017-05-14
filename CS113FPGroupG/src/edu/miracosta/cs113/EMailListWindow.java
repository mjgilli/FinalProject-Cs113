package edu.miracosta.cs113;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FilenameFilter;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import externalClass.ExitAction;

import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Component;

@SuppressWarnings("serial")
public class EMailListWindow extends JFrame implements ActionListener, MouseListener{

	private JPanel contentPane;
	private DefaultTableModel tableModel;
	private JTable eMailTable;
	private JScrollPane scrollTable;
	
	private String eMailFiles[];
	private String eMailData[][];
	
	private static final File eMailsDirectory = new File(".\\EMails");
	private JMenuItem reloadMenu;
	private JMenuItem exitMenu;


	/**
	 * Create the frame.
	 */
	public EMailListWindow() {
		setMinimumSize(new Dimension(500, 450));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		JMenu viewMenu = new JMenu("View");
		menuBar.add(viewMenu);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		ExitAction ea = new ExitAction();
		ea.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control x"));
		exitMenu = new JMenuItem(ea);
		
		reloadMenu = new JMenuItem("Reload");
		reloadMenu.addActionListener(this);
		fileMenu.add(reloadMenu);
		fileMenu.add(exitMenu);
		
		loadEMails();
		String[] columns = {"Sender(temporal: filename)", "Date", "Subject"};
		tableModel = new DefaultTableModel(columns, 0){
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		eMailTable = new JTable(tableModel);
		eMailTable.addMouseListener(this);
		for(int i=0; i<eMailFiles.length; i++){
			tableModel.addRow(eMailData[i]);
		}
		scrollTable = new JScrollPane(eMailTable);
		contentPane.add(scrollTable, BorderLayout.CENTER);
	}
	
	private void loadEMails(){
		eMailFiles = eMailsDirectory.list(new EMailFilter());
		if(eMailFiles == null){
			try{
				new File("EMails\\temporary.txt").createNewFile();
			}catch(Exception e){}
		}
		eMailData = new String[eMailFiles.length][3];
		for(int i=0; i<eMailFiles.length; i++){
			eMailData[i][0] = eMailFiles[i];	//TODO: get sender
			eMailData[i][1] = "Subject data "+(i+1);	//TODO: get subject
			eMailData[i][2] = "Date data "+(i+1);	//TODO: get date
		}
	}

	/**
	 * Launch the application.
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
		/*File tempFile = new File("EMails\\testfile.txt");
		try{
			tempFile.createNewFile();
		}catch(Exception e){}*/
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
			// open EMail with new window
			System.out.println("Doubleclicked");
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == reloadMenu){
			loadEMails();
			tableModel.setRowCount(0);
			for(int i=0; i<eMailFiles.length; i++){
				tableModel.addRow(eMailData[i]);
			}
		}
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
