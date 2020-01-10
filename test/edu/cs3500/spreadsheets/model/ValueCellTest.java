package edu.cs3500.spreadsheets.model;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;


/**
 * To test the valueCell class.
 */
public class ValueCellTest {

  /**
   * To test the numberRepresentation method in the ValueCell class.
   */
  @Test
  public void numberRepresentation() {
    SingleCell val1 = new ValueCell<Boolean>(true, Enums.ValueType.Boolean);
    SingleCell val2 = new ValueCell<Double>(50.0, Enums.ValueType.Double);

    assertTrue(0 == val1.numberRepresentation(null, null));
    assertTrue(0 ==  val2.numberRepresentation(null, null));
  }

  /**
   * To test the getValue method in the value cell class.
   */
  @Test
  public void getValue() {
    SingleCell val1 = new ValueCell<Boolean>(true, Enums.ValueType.Boolean);
    SingleCell val2 = new ValueCell<Double>(50.0, Enums.ValueType.Double);
    SingleCell val3 = new ValueCell<String>("Hello", Enums.ValueType.String);

    assertEquals(val1.getValue(null, null), true);
    assertEquals(val2.getValue(null, null), 50.0);
    assertEquals(val3.getValue(null, null), "Hello");
  }
}