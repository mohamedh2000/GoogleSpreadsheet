package edu.cs3500.spreadsheets.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * To test the LessThanCell operation class.
 */
public class LessThanCellTest {

  /**
   * To Test the hasCyclicReference method in the LessThanCell class.
   */
  @Test
  public void hasCyclicReference() {
    SingleCell stringCell = new StringCell("Hello", Enums.ValueType.String);
    SingleCell numCell = new NumberCell(50.23, Enums.ValueType.Double);
    SingleCell boolCell = new BoolCell(true, Enums.ValueType.Boolean);
    SingleCell numCell2 = new NumberCell(100.0, Enums.ValueType.Double);
    List<SingleCell> list = new ArrayList<SingleCell>();
    list.add(stringCell);
    list.add(numCell);
    list.add(boolCell);
    list.add(numCell2);
    SingleCell sumCell = new LessThanCell(list);
    HashSet<SingleCell> hash1 = new HashSet<SingleCell>();
    assertFalse(sumCell.hasCyclicReference(null, hash1));
    hash1.add(sumCell);
    assertTrue(sumCell.hasCyclicReference(null, hash1));
  }

  /**
   * To test the getValueMethod in the LessThanCell class.
   */
  @Test
  public void getValue() {
    SingleCell numCell = new NumberCell(50.23, Enums.ValueType.Double);
    SingleCell numCell2 = new NumberCell(100.0, Enums.ValueType.Double);
    List<SingleCell> list = new ArrayList<SingleCell>();
    list.add(numCell);
    list.add(numCell2);
    SingleCell lessThan = new LessThanCell(list);

    HashMap<Coord, SingleCell> map = new HashMap<Coord,SingleCell>();
    assertEquals(lessThan.getValue(map, null), true);
    assertEquals(lessThan.getValue(null, null), true);
  }
}