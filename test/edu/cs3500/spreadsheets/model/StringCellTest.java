package edu.cs3500.spreadsheets.model;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * To test the StringCell class.
 */
public class StringCellTest {

  /**
   * To test the compareCells method in the StringCell Class.
   */
  @Test
  public void compareCells() {
    SingleCell cell1 = new StringCell("Hello", Enums.ValueType.String);
    SingleCell cell2 = new StringCell("Baba", Enums.ValueType.String);

    assertFalse(cell1.compareCells(null, null, cell2));
    assertTrue(cell2.compareCells(null, null, cell1));

  }

  /**
   * To test the compareToString method in the StringCell class.
   */
  @Test
  public void compareToString() {
    SingleCell cell1 = new StringCell("Hello",
            Enums.ValueType.String);
    SingleCell cell2 = new StringCell("Baba",
            Enums.ValueType.String);

    assertFalse(cell1.compareToString(null, null,
            "baba"));
    assertFalse(cell2.compareToString(null, null,
            "baba"));
    assertFalse(cell2.compareToString(null, null,
            "Hello"));
    assertTrue(cell1.compareToString(null, null,
            "Allo"));
  }

  /**
   * To test the compareToBoolean method in the StringCell class.
   */
  @Test
  public void compareToBoolean() {
    SingleCell cell1 = new StringCell("Hello",
            Enums.ValueType.String);

    assertTrue(cell1.compareToBoolean(null, null, true));

  }
}