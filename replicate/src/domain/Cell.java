package domain;

import java.awt.Color;
import java.io.Serializable;

/**
 * La clase Cell representa una célula en la estructura de manufactura (AManufacturing).
 * Proporciona información sobre su ubicación, estado, color, y permite determinar
 * su próximo estado según las reglas del Juego de la Vida.
 * <br>
 * <b>(aManufactuing, fila, columna, edad, estado, próximo estado, color)</b><br>
 */
public class Cell extends Artefact implements Thing,  Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Estado siguiente de la célula. */
    protected char nextState;

    /** Color de la célula. */
    protected Color color;

    /** Referencia a la estructura de manufactura en la que se encuentra la célula. */
    protected AManufacturing AManufacturing;

    /** Fila y columna actuales de la célula. */
    protected int row, column;

    /**
     * Crea una nueva célula en la posición (<b>fila, columna</b>) dentro de la estructura de manufactura.
     *
     * @param am la estructura de manufactura en la que se crea la célula.
     * @param row la fila inicial de la célula.
     * @param column la columna inicial de la célula.
     * @param active indica si la célula está activa o no.
     */
    public Cell(AManufacturing am, int row, int column, boolean active) {
        this.AManufacturing = am;
        this.row = row;
        this.column = column;
        state = (active ? Artefact.ACTIVE : Artefact.INACTIVE);
        nextState = (active ? Artefact.ACTIVE : Artefact.INACTIVE);
        AManufacturing.setThing(row, column, (Thing) this);
        color = (state == Artefact.ACTIVE) ? Color.BLACK : Color.BLUE;
    }

    /**
     * Devuelve la fila de la célula.
     *
     * @return la fila actual de la célula.
     */
    public final int getRow() {
        return row;
    }

    /**
     * Devuelve la columna de la célula.
     *
     * @return la columna actual de la célula.
     */
    public final int getColumn() {
        return column;
    }

    /**
     * Devuelve el color de la célula.
     *
     * @return el color actual de la célula.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Decide el próximo estado de la célula basado en las reglas del Juego de la Vida.
     * Si la célula está activa, puede morir por soledad o sobrepoblación. Si está inactiva, puede resucitar.
     */
    public void decide() {
        int activeNeighbors = AManufacturing.neighborsActive(row, column);
        if (isActive()) {
            // Célula viva:
            if (activeNeighbors < 2 || activeNeighbors > 3) {
                nextState = Artefact.INACTIVE; // Muere
            } else {
                nextState = Artefact.ACTIVE; // Sobrevive
            }
        } else {
            // Célula muerta:
            if (activeNeighbors == 3) {
                nextState = Artefact.ACTIVE; // Resucita
            } else {
                nextState = Artefact.INACTIVE; // Permanece muerta
            }
        }
    }

    /**
     * Cambia el estado actual de la célula al próximo estado calculado.
     * También incrementa el contador de pasos y actualiza el color basado en el estado.
     */
    @Override
    public void change() {
        step();
        state = nextState;
        color = isActive() ? Color.BLACK : Color.RED;
    }

    /**
     * Establece una nueva posición para la célula.
     *
     * @param row la nueva fila de la célula.
     * @param column la nueva columna de la célula.
     */
    public void setPosition(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Devuelve el número de vecinos activos alrededor de la célula.
     *
     * @return el número de vecinos activos.
     */
    public int neighborsActive() {
        return AManufacturing.neighborsActive(row, column);
    }

    /**
     * Verifica si el vecino en la posición especificada está vacío.
     *
     * @param dr el desplazamiento en filas.
     * @param dc el desplazamiento en columnas.
     * @return {@code true} si el vecino está vacío; {@code false} en caso contrario.
     */
    public boolean neighborIsEmpty(int dr, int dc) {
        return AManufacturing.isEmpty(row + dr, column + dc);
    }
}
