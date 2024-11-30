package domain;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * La clase Machine representa una máquina que puede activar y mover elementos adyacentes en una estructura de manufactura.
 * Puede estar activa o inactiva y tiene un color asociado dependiendo de su estado.
 */
public class Machine implements Thing {

    /** Referencia a la estructura de manufactura en la que se encuentra la máquina. */
    private AManufacturing aManufacturing;

    /** Fila y columna actuales de la máquina. */
    private int row;
    private int column;

    /** Indica si la máquina está activa. */
    private boolean active;

    /** Color de la máquina según su estado. */
    private Color color;

    /**
     * Crea una nueva máquina en la estructura de manufactura.
     *
     * @param am la estructura de manufactura en la que se creará la máquina.
     * @param row la fila inicial de la máquina.
     * @param column la columna inicial de la máquina.
     * @param active indica si la máquina está activa o no.
     */
    public Machine(AManufacturing am, int row, int column, boolean active) {
        this.aManufacturing = am;
        this.row = row;
        this.column = column;
        this.active = active;
        this.color = active ? Color.GREEN : Color.GRAY; // Verde si está activa, gris si no
        aManufacturing.setThing(row, column, this);
    }

    /**
     * Decide el próximo comportamiento de la máquina. Si está activa, mueve los elementos adyacentes.
     */
    @Override
    public void decide() {
        if (active) {
            moveNearbyThings();
        }
    }

    /**
     * Mueve los elementos adyacentes a posiciones aleatorias vacías dentro de la estructura de manufactura.
     */
    private void moveNearbyThings() {
        int size = aManufacturing.getSize();
        Random rand = new Random();
        List<int[]> thingsToMove = new ArrayList<>();

        // Buscar elementos adyacentes
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) continue; // Saltar la posición de la máquina
                int newRow = row + dr;
                int newCol = column + dc;
                if (newRow >= 0 && newRow < size && newCol >= 0 && newCol < size) {
                    Thing thing = aManufacturing.getThing(newRow, newCol);
                    if (thing != null && !(thing instanceof Machine)) {
                        thingsToMove.add(new int[]{newRow, newCol});
                    }
                }
            }
        }

        // Mover los elementos adyacentes a posiciones vacías aleatorias
        for (int[] pos : thingsToMove) {
            int oldRow = pos[0];
            int oldCol = pos[1];
            List<int[]> emptyPositions = new ArrayList<>();
            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    if (aManufacturing.getThing(r, c) == null) {
                        emptyPositions.add(new int[]{r, c});
                    }
                }
            }
            if (!emptyPositions.isEmpty()) {
                int[] newPos = emptyPositions.get(rand.nextInt(emptyPositions.size()));
                int newRow = newPos[0];
                int newCol = newPos[1];
                Thing thing = aManufacturing.getThing(oldRow, oldCol);
                aManufacturing.setThing(newRow, newCol, thing);
                if (thing instanceof Cell) {
                    ((Cell) thing).setPosition(newRow, newCol);
                }
                aManufacturing.setThing(oldRow, oldCol, null);
            }
        }
    }

    /**
     * No hay cambios adicionales para la máquina en este ejemplo.
     */
    @Override
    public void change() {
        // No hay cambios adicionales
    }

    /**
     * Devuelve la forma de la máquina.
     *
     * @return la forma cuadrada.
     */
    @Override
    public int shape() {
        return Thing.SQUARE;
    }

    /**
     * Devuelve el color de la máquina.
     *
     * @return el color de la máquina.
     */
    @Override
    public Color getColor() {
        return color;
    }

    /**
     * Indica si la máquina está activa.
     *
     * @return {@code true} si la máquina está activa, {@code false} en caso contrario.
     */
    @Override
    public boolean isActive() {
        return active;
    }

    /**
     * Activa o desactiva la máquina y actualiza su color según el estado.
     *
     * @param active indica si la máquina debe estar activa o no.
     */
    public void setActive(boolean active) {
        this.active = active;
        this.color = active ? Color.GREEN : Color.GRAY;
    }

    /**
     * Devuelve la fila de la máquina.
     *
     * @return la fila actual de la máquina.
     */
    public int getRow() {
        return row;
    }

    /**
     * Devuelve la columna de la máquina.
     *
     * @return la columna actual de la máquina.
     */
    public int getColumn() {
        return column;
    }
}

