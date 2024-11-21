package presentacion;

import javax.swing.*;

public class ReplicateGUI extends JFrame{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static void  main (String[] args) {
		new ReplicateGUI();
	}
	
	public ReplicateGUI(){
		prepareElements();
		prepareActions();
	}
	
	private void prepareElements() {
		// TODO Auto-generated method stu
		this.setVisible(true);
		this.setSize(800,600);
		this.setLocationRelativeTo(null);

		JPanel panel = new JPanel();

		this.add(panel);

		panel.setSize(800, 600);

		JMenuBar menuBar = new JMenuBar();

		JMenu archivoMenu = new JMenu("File");

		JMenuItem newItem = new JMenuItem("New");
		JMenuItem openItem = new JMenuItem("Open");
		JMenuItem saveItem = new JMenuItem("Save");
		JMenuItem saveAsItem = new JMenuItem("Save As");
		JMenuItem importItem = new JMenuItem("Import");
		JMenuItem exportItem = new JMenuItem("Export");
		JMenuItem exitItem = new JMenuItem("Exit");

		archivoMenu.add(newItem);
		archivoMenu.add(openItem);
		archivoMenu.addSeparator();
		archivoMenu.add(saveItem);
		archivoMenu.add(saveAsItem);
		archivoMenu.addSeparator();
		archivoMenu.add(importItem);
		archivoMenu.add(exportItem);
		archivoMenu.add(exitItem);

		menuBar.add(archivoMenu);
		this.setJMenuBar(menuBar);
	}

	private void prepareActions() {
		// TODO Auto-generated method stub
		Open();
		Close();
		Save();
		Exit();
		
		
	}

	private void Exit() {
		// TODO Auto-generated method stub
		
	}

	private void Save() {
		// TODO Auto-generated method stub
		
	}

	private void Open() {
		// TODO Auto-generated method stub
		
	}

	private void Close() {
		// TODO Auto-generated method stub
		
	}

	
	
}
