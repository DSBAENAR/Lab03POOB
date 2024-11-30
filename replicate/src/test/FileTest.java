package test;
import domain.AManufacturing;
import domain.ReplicateException;
import org.junit.jupiter.api.Test;
import presentation.ReplicateGUI;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;


public class FileTest {
	
	@Test
	void testOptionSaveAndOpen() throws ReplicateException, IOException {
	    ReplicateGUI gui = new ReplicateGUI();
	    AManufacturing originalAm = new AManufacturing();
	    gui.setAm(originalAm);

	    // Archivo temporal para la prueba
	    File tempFile = File.createTempFile("test_am", ".dat");
	    tempFile.deleteOnExit();

	    // Simular JFileChooser para guardar
	    JFileChooser mockSaveFile = new JFileChooser() {
	        @Override
	        public File getSelectedFile() {
	            return tempFile;
	        }

	        @Override
	        public int showSaveDialog(java.awt.Component parent) {
	            return JFileChooser.APPROVE_OPTION;
	        }
	    };

	    // Simular JFileChooser para abrir
	    JFileChooser mockOpenFile = new JFileChooser() {
	        @Override
	        public File getSelectedFile() {
	            return tempFile;
	        }

	        @Override
	        public int showOpenDialog(java.awt.Component parent) {
	            return JFileChooser.APPROVE_OPTION;
	        }
	    };

	    // Guardar y luego abrir el archivo
	    gui.optionSave(mockSaveFile);
	    gui.optionOpen(mockOpenFile);

	    // Comparar objetos
	    AManufacturing loadedAm = gui.getAm();
	    assertNotNull(loadedAm, "El objeto cargado no debe ser null");
	    assertEquals(originalAm.toString(), loadedAm.toString(), "El objeto cargado debe ser igual al guardado");
	}
}
