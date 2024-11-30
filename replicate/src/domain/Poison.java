package domain;

import java.awt.Color;

/**
 * Representa una célula de veneno en la simulación del autómata celular.
 * La célula de veneno es cuadrada y cambia de color cíclicamente a través de colores del arcoíris con cada tic-tac.
 */
public class Poison extends Cell implements Thing {

    /** Colores del arcoíris a través de los cuales la célula de veneno cambia. */
    private static final Color[] RAINBOW_COLORS = {
            Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE};

    /** Índice del color actual en la secuencia de colores del arcoíris. */
    private int colorIndex;

    /**
     * Crea una nueva célula de veneno en la ubicación especificada.
     *
     * @param am La instancia de AManufacturing a la que pertenece esta célula de veneno
     * @param row La posición de la fila del veneno
     * @param column La posición de la columna del veneno
     */
    public Poison(AManufacturing am, int row, int column) {
        super(am, row, column, true);  // El veneno siempre está activo
        this.colorIndex = (int) (Math.random() * RAINBOW_COLORS.length);
        updateColor();
    }

    /**
     * Actualiza el color del veneno al siguiente color en la secuencia del arcoíris.
     */
    private void updateColor() {
        this.color = RAINBOW_COLORS[colorIndex];
    }

    /**
     * Decide el próximo estado del veneno.
     * Para el veneno, esto solo implica cambiar al siguiente color.
     */
    @Override
    public void decide() {
        colorIndex = (colorIndex + 1) % RAINBOW_COLORS.length;
    }

    /**
     * Cambia el estado del veneno, lo cual solo implica actualizar su color.
     */
    @Override
    public void change() {
        updateColor();
    }

    /**
     * Devuelve el color actual del veneno.
     *
     * @return El color actual del veneno
     */
    @Override
    public Color getColor() {
        return this.color;
    }

    /**
     * Especifica la forma de la célula de veneno.
     *
     * @return {@code SQUARE}, ya que las células de veneno siempre son cuadradas
     */
    @Override
    public int shape() {
        return Thing.SQUARE;
    }
}

