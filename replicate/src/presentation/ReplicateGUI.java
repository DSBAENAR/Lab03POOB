package presentation;
import domain.*;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.awt.event.ActionEvent;

public class ReplicateGUI extends JFrame implements Serializable {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JMenuBar menuBar;
	JMenu mnMainMenu;
	JMenuItem menuItem_new;
	JMenuItem menuItemOpen;
	JMenuItem menuItemSave;
	JMenuItem menuItem_import;
	JMenuItem menuItem_export;
	JMenuItem menuItem_exit;
	JFileChooser file;
	AManufacturingGUI amanufacturingGUI;
	private AManufacturing am;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		ReplicateGUI gui= new ReplicateGUI();
		gui.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public ReplicateGUI() {
		prepareElements();
		
	}
	
	private void prepareElements(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		menuBar = new JMenuBar();
		menuBar.setBackground(SystemColor.controlHighlight);
		menuBar.setBorderPainted(false);
		menuBar.setBounds(0, 0, 69, 22);
		contentPane.add(menuBar);
		
		mnMainMenu= new JMenu("Menu");
		mnMainMenu.setHorizontalAlignment(SwingConstants.CENTER);
		mnMainMenu.setFont(new Font("Cascadia Mono", Font.BOLD, 13));
		menuBar.add(mnMainMenu);
		
		menuItem_new = new JMenuItem("New");
		menuItem_new.setFont(new Font("Cascadia Mono", Font.PLAIN, 13));
		mnMainMenu.add(menuItem_new);
		
		menuItemOpen = new JMenuItem("Open");
		
		menuItemOpen.setFont(new Font("Cascadia Mono", Font.PLAIN, 13));
		mnMainMenu.add(menuItemOpen);
		
		menuItemSave = new JMenuItem("Save as");
		menuItemSave.setFont(new Font("Cascadia Mono", Font.PLAIN, 13));
		mnMainMenu.add(menuItemSave);
		
		menuItem_import = new JMenuItem("Import");
		menuItem_import.setFont(new Font("Cascadia Mono", Font.PLAIN, 13));
		mnMainMenu.add(menuItem_import);
		
		menuItem_export = new JMenuItem("Export");
		menuItem_export.setFont(new Font("Cascadia Mono", Font.PLAIN, 13));
		mnMainMenu.add(menuItem_export);
		
		menuItem_exit = new JMenuItem("Exit");
		menuItem_exit.setFont(new Font("Cascadia Mono", Font.PLAIN, 13));
		mnMainMenu.add(menuItem_exit);
		prepareActionsMenu();
	}
	
	private void prepareActionsMenu() {
		menuItem_new.addActionListener(new ActionListener() { //New Listener
			public void actionPerformed(ActionEvent e) {
				optionNew();
			}
		});
		menuItemOpen.addActionListener(new ActionListener() { //Open Listener
			public void actionPerformed(ActionEvent e) {
				try {
					optionOpen(file);
				} catch (ReplicateException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		menuItemSave.addActionListener(new ActionListener() { //Save Listener
			public void actionPerformed(ActionEvent e) {
				try {
					optionSave(file);
				} catch (ReplicateException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		menuItem_import.addActionListener(new ActionListener() { //Import Listener
			public void actionPerformed(ActionEvent e) {
				try {
					optionImport(file);
				} catch (ReplicateException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		menuItem_export.addActionListener(new ActionListener() { //Export Listener
			public void actionPerformed(ActionEvent e) {
				try {
					optionExport(file);
				} catch (ReplicateException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		menuItem_exit.addActionListener(new ActionListener() { //Exit Listener
			public void actionPerformed(ActionEvent e) {
				optionExit();
			}
		});
		
	}
	
	public void optionNew() {
		setAm(new AManufacturing()); // Inicializar la instancia de am
		amanufacturingGUI = new AManufacturingGUI();
		amanufacturingGUI.setVisible(true);
		
	}
	
	private void optionExit() {
		System.exit(0);
	}
	
	public void optionOpen(JFileChooser file) throws ReplicateException {
	    file = new JFileChooser();
	    file.setDialogTitle("Abrir estado de Replicate");
	    file.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos DAT", "dat"));

	    int userSelection = file.showOpenDialog(this);

	    if (userSelection == JFileChooser.APPROVE_OPTION) {
	        File selectedFile = file.getSelectedFile();

	        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(selectedFile))) {
	            // Deserializar el objeto AManufacturing
	            AManufacturing loadedAm = (AManufacturing) ois.readObject();
	            setAm(loadedAm); // Reemplazar el objeto actual con el cargado

	            // Mostrar la interfaz de AManufacturing
	            if (amanufacturingGUI == null) {
	                amanufacturingGUI = new AManufacturingGUI();
	            }
	            amanufacturingGUI.setAm(loadedAm); // Pasar el objeto cargado
	            amanufacturingGUI.setVisible(true); // Hacer visible la interfaz

	            JOptionPane.showMessageDialog(this, "Estado cargado exitosamente desde: " + selectedFile.getAbsolutePath());
	        } catch (Exception ex) {
	            throw new ReplicateException("Error al cargar el estado: " + ex.getMessage());
	        }
	    } else {
	        JOptionPane.showMessageDialog(this, "Operación cancelada por el usuario.");
	    }
	}


	
	public void optionSave(JFileChooser file) throws ReplicateException {
	    file = new JFileChooser();
	    file.setDialogTitle("Guardar estado de Replicate");
	    file.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos DAT", "dat"));

	    int userSelection = file.showSaveDialog(this);

	    if (userSelection == JFileChooser.APPROVE_OPTION) {
	        File fileToSave = file.getSelectedFile();
	        if (!fileToSave.getAbsolutePath().endsWith(".dat")) {
	            fileToSave = new File(fileToSave.getAbsolutePath() + ".dat");
	        }

	        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileToSave))) {
	            // Serializar el objeto AManufacturing
	            oos.writeObject(am);
	            JOptionPane.showMessageDialog(this, "Estado guardado exitosamente en: " + fileToSave.getAbsolutePath());
	        } catch (Exception ex) {
	            throw new ReplicateException("Error al guardar el estado: " + ex.getMessage());
	        }
	    } else {
	        JOptionPane.showMessageDialog(this, "Operación cancelada por el usuario.");
	    }
	}

	
	private void optionImport(JFileChooser file) throws ReplicateException{
		
	}
	
	private void optionExport(JFileChooser file) throws ReplicateException{
		
	}

	public AManufacturing getAm() {
		return am;
	}

	public void setAm(AManufacturing am) {
		this.am = am;
	}
}
