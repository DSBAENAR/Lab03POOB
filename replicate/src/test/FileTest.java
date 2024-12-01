package test;
import domain.AManufacturing;
import domain.ReplicateException;
import org.junit.jupiter.api.Test;
import presentation.ReplicateGUI;
import javax.swing.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;


public class FileTest {
	
	@Test
	void testOptionSaveAndOpen() throws ReplicateException, IOException {
	    ReplicateGUI gui = new ReplicateGUI();
	    AManufacturing originalAm = new AManufacturing();
	    gui.setAm(originalAm);

	    // Archivo temporal para la prueba
	    File tempFile = File.createTempFile("onereplicate", ".dat");
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
	@Test
	void testOptionImportAndExport() throws ReplicateException, IOException {
	    ReplicateGUI gui = new ReplicateGUI();

	    // Crear una instancia inicial de AManufacturing
	    AManufacturing originalAm = new AManufacturing();
	    gui.setAm(originalAm);

	    // Archivo temporal para exportar
	    File tempExportFile = File.createTempFile("replicate_export", ".txt");
	    tempExportFile.deleteOnExit();

	    // Archivo temporal para importar
	    File tempImportFile = File.createTempFile("replicate_import", ".txt");
	    tempImportFile.deleteOnExit();

	    // Simular JFileChooser para exportar
	    JFileChooser mockExportFile = new JFileChooser() {
	        @Override
	        public File getSelectedFile() {
	            return tempExportFile;
	        }

	        @Override
	        public int showSaveDialog(java.awt.Component parent) {
	            return JFileChooser.APPROVE_OPTION;
	        }
	    };

	    // Simular JFileChooser para importar
	    JFileChooser mockImportFile = new JFileChooser() {
	        @Override
	        public File getSelectedFile() {
	            return tempImportFile;
	        }

	        @Override
	        public int showOpenDialog(java.awt.Component parent) {
	            return JFileChooser.APPROVE_OPTION;
	        }
	    };

	    // Exportar el estado al archivo temporal
	    gui.optionExport(mockExportFile);

	    // Copiar el archivo exportado al archivo de importación con formatos variados
	    try (BufferedReader reader = new BufferedReader(new FileReader(tempExportFile));
	         BufferedWriter writer = new BufferedWriter(new FileWriter(tempImportFile))) {

	        String line;
	        while ((line = reader.readLine()) != null) {
	            writer.write(line);
	            writer.newLine();
	        }

	        // Agregar una línea incompleta para probar la importación con datos predeterminados
	        writer.write("Cell,12,13");
	        writer.newLine();
	    }

	    // Importar el estado desde el archivo modificado
	    gui.optionImport(mockImportFile);

	    // Obtener el objeto importado
	    AManufacturing importedAm = gui.getAm();

	    // Verificar que el objeto importado no sea null
	    assertNotNull(importedAm, "El objeto importado no debe ser null");

	    // Verificar que el contenido importado es consistente con el exportado más los datos adicionales
	    assertEquals(originalAm.getSize(), importedAm.getSize(), "El tamaño de la matriz debe coincidir");
	    System.out.println("Estado del objeto importado: " + importedAm);
	}

	
}
