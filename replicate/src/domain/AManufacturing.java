package domain;
import java.util.*;
import java.io.Serializable;
import java.lang.Class;
import java.lang.reflect.Constructor;

/**
 * La clase AManufacturing representa una estructura de células (o artefactos) organizada en una matriz.
 * Contiene lógica para agregar elementos, calcular vecinos activos, y gestionar el estado de cada célula
 * en base a las reglas del Juego de la Vida.
 */
public class AManufacturing implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Tamaño de la estructura de células. */
    static private int SIZE = 50;

    /** Matriz de células (o artefactos) en la estructura de manufactura. */
    private Thing[][] lattice;

    /**
     * Crea una nueva instancia de AManufacturing con una cuadrícula de células vacía.
     */
    public AManufacturing() {
        lattice = new Thing[SIZE][SIZE];
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                lattice[r][c] = null;
            }
        }
        someThings();
    }

    /**
     * Devuelve el tamaño de la estructura de manufactura.
     *
     * @return el tamaño de la estructura.
     */
    public int getSize() {
        return SIZE;
    }

    /**
     * Devuelve la célula o artefacto en una posición determinada.
     *
     * @param r la fila.
     * @param c la columna.
     * @return el artefacto en la posición especificada, o {@code null} si no hay ninguno.
     */
    public Thing getThing(int r, int c) {
        return lattice[r][c];
    }

    /**
     * Establece una célula o artefacto en una posición determinada.
     *
     * @param r la fila.
     * @param c la columna.
     * @param e el artefacto a colocar en la posición especificada.
     */
    public void setThing(int r, int c, Thing e) {
        lattice[r][c] = e;
    }

    /**
     * Agrega algunas células iniciales a la estructura de manufactura.
     */
    public void someThings() {
        new Cell(this, 1, 2, true);
        new Cell(this, 2, 3, true);
        new Cell(this, 3, 1, true);
        new Cell(this, 3, 2, true);
        new Cell(this, 3, 3, true);

        new TouristCell(this, 10, 10, "move");
        new TouristCell(this, 11, 10, "walk");
        new TouristCell(this, 12, 10, null);
    }

    /**
     * Calcula el número de vecinos activos de una célula en una posición determinada.
     *
     * @param r la fila de la célula.
     * @param c la columna de la célula.
     * @return el número de vecinos activos.
     */
    public int neighborsActive(int r, int c) {
        int num = 0;
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if ((dr != 0 || dc != 0) && inLatice(r + dr, c + dc)) {
                    Thing neighbor = lattice[r + dr][c + dc];
                    if (neighbor != null && neighbor.isActive() && !(neighbor instanceof TouristCell)) {
                        num++;
                    }
                }
            }
        }
        return num;
    }

    /**
     * Verifica si una posición de la estructura está vacía.
     *
     * @param r la fila.
     * @param c la columna.
     * @return {@code true} si la posición está vacía; {@code false} en caso contrario.
     */
    public boolean isEmpty(int r, int c) {
        return (inLatice(r, c) && lattice[r][c] == null);
    }

    /**
     * Verifica si una posición está dentro de los límites de la estructura.
     *
     * @param r la fila.
     * @param c la columna.
     * @return {@code true} si la posición está dentro de los límites; {@code false} en caso contrario.
     */
    private boolean inLatice(int r, int c) {
        return ((0 <= r) && (r < SIZE) && (0 <= c) && (c < SIZE));
    }

    /**
     * Realiza un ciclo de actualización sobre todas las células de la estructura, aplicando las reglas del Juego de la Vida.
     * Activa o desactiva células según corresponda.
     */
    public void ticTac() {
        // Lista para almacenar las posiciones de células que deben ser activadas o desactivadas
        List<int[]> cellsToActivate = new ArrayList<>();
        List<int[]> cellsToDeactivate = new ArrayList<>();

        // Primera pasada: cada célula existente decide su próximo estado
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                Thing thing = lattice[r][c];
                if (thing != null && thing instanceof Cell) {
                    thing.decide();
                }
            }
        }

        // Evaluar células que actualmente no existen para posibles nacimientos
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (lattice[r][c] == null) {
                    int activeNeighbors = neighborsActive(r, c);
                    if (activeNeighbors == 3) {
                        cellsToActivate.add(new int[]{r, c});
                    }
                }
            }
        }

        // Segunda pasada: aplicar los cambios a las células existentes
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                Thing thing = lattice[r][c];
                if (thing != null && thing instanceof Cell) {
                    thing.change();
                    if (!thing.isActive()) {
                        cellsToDeactivate.add(new int[]{r, c});
                    }
                }
            }
        }

        // Desactivar células que deben ser eliminadas
        for (int[] pos : cellsToDeactivate) {
            int r = pos[0];
            int c = pos[1];
            lattice[r][c] = null; // Eliminar la célula de la cuadrícula
        }

        // Activar nuevas células que deben nacer
        for (int[] pos : cellsToActivate) {
            int r = pos[0];
            int c = pos[1];
            new Cell(this, r, c, true); // Crear una nueva célula activa
        }
    }
    
    public AManufacturing getAmanufacturing() {
		return this;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SIZE).append("\n"); // Agregamos el tamaño de la matriz como primera línea

        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                Thing thing = lattice[r][c];
                if (thing != null) {
                    sb.append(thing.getClass().getSimpleName()).append(",") // Tipo de celda
                      .append(r).append(",") // Fila
                      .append(c).append(",") // Columna
                      .append(thing.toString()).append("\n"); // Estado específico
                }
            }
        }
        return sb.toString();
    }


    public static AManufacturing fromString(String data) {
        String[] lines = data.split("\n");
        int size = Integer.parseInt(lines[0]); // Primera línea: tamaño de la matriz
        AManufacturing am = new AManufacturing();

        for (int i = 1; i < lines.length; i++) { // Saltamos la primera línea
            String[] parts = lines[i].split(",");
            String className = parts[0];
            int row = Integer.parseInt(parts[1]);
            int col = Integer.parseInt(parts[2]);

            // Reconstruir el objeto Thing según su tipo
            Thing thing;
            if (className.equals("Cell")) {
                thing = Cell.fromString(am, row, col, parts[3]); // parts[3]: estado específico
            } else if (className.equals("TouristCell")) {
                thing = TouristCell.fromString(am, row, col, parts[3]); // parts[3]: estado específico
            } else {
                throw new IllegalArgumentException("Tipo desconocido: " + className);
            }

            am.setThing(row, col, thing);
        }

        return am;
    }

}
