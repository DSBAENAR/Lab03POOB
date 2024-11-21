package dominio;

import java.awt.Color;

/**
 * La interfaz Thing representa un objeto con ciertas propiedades, como forma, color
 * y comportamiento. Incluye métodos para decidir el comportamiento, cambiar el estado y
 * recuperar atributos como la forma y el color. Esta interfaz puede ser implementada
 * por diversas entidades para definir su comportamiento y propiedades únicas.
 */
public interface Thing {

    /** Constante que representa una forma redonda. */
    public static final int ROUND = 1;

    /** Constante que representa una forma cuadrada. */
    public static final int SQUARE = 2;

    /**
     * Define el comportamiento o la lógica de decisión para el objeto que implementa esta interfaz.
     * Las clases que implementen esta interfaz deben proporcionar su propia implementación de este método.
     */
    public abstract void decide();

    /**
     * Cambia el estado o las propiedades del objeto. Este es un método predeterminado que puede ser
     * sobrescrito por las clases que lo implementen si es necesario.
     */
    default void change() {
    }

    /**
     * Devuelve la forma del objeto. Por defecto, devuelve {@code SQUARE}.
     *
     * @return la forma del objeto, ya sea {@code ROUND} o {@code SQUARE}.
     */
    default int shape() {
        return SQUARE;
    }

    /**
     * Devuelve el color del objeto. Por defecto, devuelve {@code Color.red}.
     *
     * @return el color del objeto.
     */
    default Color getColor() {
        return Color.red;
    }

    /**
     * Indica si el objeto está activo o no. Por defecto, devuelve {@code false}.
     *
     * @return {@code true} si el objeto está activo, {@code false} en caso contrario.
     */
    default boolean isActive() {
        return false;
    }
}

