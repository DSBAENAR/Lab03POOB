package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.AManufacturing;
import domain.Cell;

import java.awt.Color;

public class CellTest {
    private AManufacturing am;
    private Cell cell;

    @BeforeEach
    void setUp() {
        am = new AManufacturing();
        cell = new Cell(am, 1, 1, true);
    }

    @Test
    void createNewCellWithActiveState() {
        assertTrue(cell.isActive());
        assertEquals(Color.BLACK, cell.getColor());
    }

    @Test
    void createNewCellWithInactiveState() {
        Cell inactiveCell = new Cell(am, 1, 1, false);
        assertFalse(inactiveCell.isActive());
        assertEquals(Color.BLUE, inactiveCell.getColor());
    }

    @Test
    void getRowReturnsCorrectValue() {
        assertEquals(1, cell.getRow());
    }

    @Test
    void getColumnReturnsCorrectValue() {
        assertEquals(1, cell.getColumn());
    }
}

