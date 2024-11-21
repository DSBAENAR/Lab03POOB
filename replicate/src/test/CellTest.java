package test;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import dominio.AManufacturing;
import java.awt.Color;
import dominio.Cell;

public class CellTest {
    private AManufacturing am;
    private Cell cell;

    @Before
    public void setUp() {
        am = new AManufacturing();
        cell = new Cell(am, 1, 1, true);
    }

    @Test
    public void createNewCellWithActiveState() {
        assertTrue(cell.isActive());
        assertEquals(Color.BLACK, cell.getColor());
    }

    @Test
    public void createNewCellWithInactiveState() {
        Cell inactiveCell = new Cell(am, 1, 1, false);
        assertFalse(inactiveCell.isActive());
        assertEquals(Color.BLUE, inactiveCell.getColor());
    }

    @Test
    public void getRowReturnsCorrectValue() {
        assertEquals(1, cell.getRow());
    }

    @Test
    public void getColumnReturnsCorrectValue() {
        assertEquals(1, cell.getColumn());
    }
}
