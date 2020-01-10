package edu.cs3500.spreadsheets.model;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * To Test the BoolCell Class.
 */
public class BoolCellTest {

  /**
   * To test the compareCell method which will check two different boolean cells.
   */
  @Test
  public void compareCells() {
    SingleCell bool1 = new BoolCell(false, Enums.ValueType.Boolean);
    SingleCell bool2 = new BoolCell(true, Enums.ValueType.Boolean);
    assertFalse(bool1.compareCells(null, null, bool2));
    assertTrue(bool2.compareCells(null, null, bool1));
    assertFalse(bool1.compareCells(null, null, bool1));
  }

  /**
   * To test the comparetoBoolean method.
   */
  @Test
  public void compareToBoolean() {
    SingleCell bool1 = new BoolCell(false, Enums.ValueType.Boolean);
    SingleCell bool2 = new BoolCell(true, Enums.ValueType.Boolean);
    assertTrue(bool1.compareToBoolean(null, null, true));
    assertFalse(bool2.compareToBoolean(null, null, false));
  }

  /**
   * to test the compareToDouble method.
   */
  @Test
  public void compareToDouble() {
    SingleCell bool1 = new BoolCell(false, Enums.ValueType.Boolean);
    assertTrue(bool1.compareToDouble(null, null, 50.0));
  }

  /**
   * To test the comparetoString method.
   */
  @Test
  public void compareToString() {
    SingleCell bool1 = new BoolCell(false, Enums.ValueType.Boolean);
    assertTrue(bool1.compareToString(null, null, "hi"));
  }
}