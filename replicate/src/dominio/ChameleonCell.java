package dominio;

import java.awt.Color;
import java.util.Random;

/**
 * La clase ChameleonCell representa una célula camaleónica que cambia de color de manera aleatoria
 * y se reproduce después de un número determinado de ciclos tic-tac. También puede cambiar de forma.
 */
public class ChameleonCell extends Cell {

    /** Color actual de la célula camaleónica. */
    private Color color;

    /** Forma de la célula camaleónica. */
    private String shape;

    /** Contador de ciclos tic-tac. */
    private int ticTacCount;

    /**
     * Crea una nueva célula camaleónica en la ubicación especificada.
     *
     * @param manufacturing La instancia de AManufacturing a la que pertenece esta célula.
     * @param r La posición de la fila de la célula.
     * @param c La posición de la columna de la célula.
     * @param active Indica si la célula está activa.
     */
    public ChameleonCell(AManufacturing manufacturing, int r, int c, boolean active) {
        super(manufacturing, r, c, active);
        this.color = Color.RED;  // Iniciar con color rojo
        this.ticTacCount = 0;    // Inicializar el contador
    }

    /**
     * Devuelve el color actual de la célula camaleónica.
     *
     * @return El color actual de la célula.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Devuelve la forma actual de la célula camaleónica.
     *
     * @return La forma actual de la célula.
     */
    public String getShape() {
        return shape;
    }

    /**
     * Establece un nuevo color para la célula camaleónica.
     *
     * @param newColor El nuevo color de la célula.
     */
    public void setColor(Color newColor) {
        this.color = newColor;
    }

    /**
     * Establece una nueva forma para la célula camaleónica.
     *
     * @param newShape La nueva forma de la célula.
     */
    public void setShape(String newShape) {
        this.shape = newShape;
    }

    /**
     * Cambia el estado de la célula camaleónica.
     * Esto incluye cambiar su color aleatoriamente y verificar si debe reproducirse después de 3 ciclos.
     */
    @Override
    public void change() {
        // Cambiar el color aleatoriamente
        this.color = new Color((int)(Math.random() * 0x1000000)); // Color aleatorio

        // Incrementar el contador de ciclos
        ticTacCount++;

        // Verificar si el contador alcanza 3
        if (ticTacCount >= 3) {
            multiply(); // Reproducir
            ticTacCount = 0; // Reiniciar el contador
        }
    }

    /**
     * Reproduce la célula camaleónica creando una nueva célula en una posición vecina vacía.
     */
    public void multiply() {
        Random random = new Random();

        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                int newRow = row + dr;
                int newCol = column + dc;

                // Asegurarse de no colocar una nueva célula en la posición actual y verificar que la celda vecina esté vacía
                if ((dr != 0 || dc != 0) && AManufacturing.isEmpty(newRow, newCol)) {
                    // Crear una nueva ChameleonCell en la posición vacía
                    new ChameleonCell(AManufacturing, newRow, newCol, true);
                    return; // Salir del bucle después de crear una célula
                }
            }
        }
    }
}

