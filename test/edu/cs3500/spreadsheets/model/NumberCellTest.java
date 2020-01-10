package edu.cs3500.spreadsheets.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * To Test the NumberCell class.
 */
public class NumberCellTest {

  /**
   * To test the compare Cells method in the NumberCell class.
   */
  @Test
  public void compareCells() {
    SingleCell numCell1 = new NumberCell(50.0, Enums.ValueType.Double);
    SingleCell numCell2 = new NumberCell(100.0, Enums.ValueType.Double);
    assertTrue(numCell1.compareCells(null, null, numCell2));
    assertFalse(numCell2.compareCells(null, null, numCell1));
  }

  /**
   * To test the compareToDouble method in the NumberCell Class.
   */
  @Test
  public void compareToDouble() {
    SingleCell numCell1 = new NumberCell(50.0, Enums.ValueType.Double);
    SingleCell numCell2 = new NumberCell(100.0, Enums.ValueType.Double);
    assertFalse(numCell1.compareToDouble(null, null ,100.0));
    assertTrue(numCell2.compareToDouble(null, null, 50.0));
  }

  /**
   * To test the numberRepresentation method in the NumberCell class.
   */
  @Test
  public void numberRepresentation() {
    SingleCell numCell1 = new NumberCell(50.0, Enums.ValueType.Double);
    SingleCell numCell2 = new NumberCell(100.0, Enums.ValueType.Double);
    assertEquals(50.0, numCell1.numberRepresentation(null, null), 0.0);
    assertEquals(100.0, numCell2.numberRepresentation(null, null), 0.0);

  }
}