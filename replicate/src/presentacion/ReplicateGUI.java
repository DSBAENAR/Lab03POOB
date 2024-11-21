package presentation;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ReplicateGUI extends JFrame{
	JFrame frame;
	JPanel panel;

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
		// TODO Auto-generated method stub
		frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(800,600);
		frame.setLocationRelativeTo(null);
		
		panel = new JPanel();
		panel.setSize(800, 600);
		
		
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
