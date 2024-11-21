package dominio;

import java.awt.Color;

/**
 * La clase TouristCell representa una célula que se mueve dentro de una estructura de manufactura (
 * AManufacturing lattice). Puede cambiar de estado entre activo e inactivo y se mueve hacia el
 * centro o hacia los bordes según su estado.
 */
public class TouristCell extends Cell {

    /** Indica si la célula está activa o no. */
    private boolean active;

    /** Fila actual de la célula. */
    private int row;

    /** Columna actual de la célula. */
    private int col;

    /** Referencia a la estructura de manufactura en la que se encuentra la célula. */
    private AManufacturing lattice;

    /**
     * Crea una nueva célula turista.
     *
     * @param lattice la estructura de manufactura en la que se encuentra la célula.
     * @param row la fila inicial de la célula.
     * @param col la columna inicial de la célula.
     * @param name el nombre de la célula.
     * @throws IllegalArgumentException si la estructura de manufactura es nula.
     */
    public TouristCell(AManufacturing lattice, int row, int col, String name) {
        super(lattice, row, col, true);
        if (lattice == null) {
            throw new IllegalArgumentException("Lattice cannot be null");
        }
        this.lattice = lattice;
        this.row = row;
        this.col = col;
        this.active = true; // Comienza como activa
    }

    /**
     * Devuelve el color de la célula.
     *
     * @return {@code Color.ORANGE} si la célula está activa, {@code Color.YELLOW} si está inactiva.
     */
    @Override
    public Color getColor() {
        return active ? Color.ORANGE : Color.YELLOW;
    }

    /**
     * Decide la próxima acción de la célula. Si está activa, se moverá hacia el centro; si está inactiva, se moverá hacia los bordes.
     */
    @Override
    public void decide() {
        if (active) {
            moveToCenter();
        } else {
            moveToEdge();
        }
    }

    /**
     * Mueve la célula hacia el centro de la estructura de manufactura. Si no puede moverse, cambia su estado a inactivo.
     */
    private void moveToCenter() {
        if (lattice == null) {
            return; // Evitar NullPointerException
        }
        int centerRow = lattice.getSize() / 2;
        int centerCol = lattice.getSize() / 2;

        int newRow = row + Integer.compare(centerRow, row);
        int newCol = col + Integer.compare(centerCol, col);

        if (lattice.isEmpty(newRow, newCol)) {
            move(newRow, newCol);
        } else {
            active = false; // Cambia el estado si no puede moverse
        }
    }

    /**
     * Mueve la célula hacia el borde de la estructura de manufactura. Si no puede moverse, cambia su estado a activo.
     */
    private void moveToEdge() {
        if (lattice == null) {
            return; // Evitar NullPointerException
        }
        int edgeRow = (row < lattice.getSize() / 2) ? 0 : lattice.getSize() - 1;
        int edgeCol = (col < lattice.getSize() / 2) ? 0 : lattice.getSize() - 1;

        int newRow = row + Integer.compare(edgeRow, row);
        int newCol = col + Integer.compare(edgeCol, col);

        if (lattice.isEmpty(newRow, newCol)) {
            move(newRow, newCol);
        } else {
            active = true; // Cambia el estado si no puede moverse
        }
    }

    /**
     * Mueve la célula a una nueva ubicación dentro de la estructura de manufactura.
     *
     * @param newRow la nueva fila a la que se moverá la célula.
     * @param newCol la nueva columna a la que se moverá la célula.
     */
    private void move(int newRow, int newCol) {
        lattice.setThing(newRow, newCol, this);
        lattice.setThing(row, col, null);
        row = newRow;
        col = newCol;
    }
}

