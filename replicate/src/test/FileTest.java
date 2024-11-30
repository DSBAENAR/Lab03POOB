package test;

import domain.AManufacturing;
import domain.ReplicateException;
import org.junit.jupiter.api.Test;
import presentation.ReplicateGUI;

import javax.swing.*;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class FileTest {

    @Test
    public void testSaveAndOpen() throws ReplicateException {
    	// Crear instancia de ReplicateGUI
        ReplicateGUI gui = new ReplicateGUI();
        gui.optionNew(); // Inicializa el objeto AManufacturing
        AManufacturing originalData = gui.getAm(); // Obtener la instancia inicial

        // Crear un archivo temporal para pruebas
        File tempFile = new File("test_data.dat");

        // Simular guardar el estado
        JFileChooser saveChooser = new JFileChooser() {
            private static final long serialVersionUID = 1L;

            @Override
            public File getSelectedFile() {
                return tempFile;
            }
        };
        gui.optionSave(saveChooser);


        // Simular abrir el archivo guardado
        JFileChooser openChooser = new JFileChooser() {
            private static final long serialVersionUID = 1L;

            @Override
            public File getSelectedFile() {
                return tempFile;
            }
        };
        gui.optionOpen(openChooser);

        // Verificar que el estado cargado sea igual al original
        AManufacturing loadedData = gui.getAm();
        assertNotNull(loadedData, "El estado cargado es nulo.");
        assertEquals(originalData, loadedData, "El estado cargado no coincide con el original.");

        // Eliminar archivo temporal después de la prueba
        tempFile.delete();
    }
        
}
