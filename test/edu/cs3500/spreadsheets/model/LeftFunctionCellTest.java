package edu.cs3500.spreadsheets.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * To Test the leftFunctionCell class.
 */
public class LeftFunctionCellTest {

  /**
   * To test the hasCyclicReference method in the LeftFunctionCell class.
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
    SingleCell leftFunctionCell = new LeftFunctionCell(list);

    HashSet<SingleCell> hash1 = new HashSet<SingleCell>();
    assertFalse(leftFunctionCell.hasCyclicReference(null, hash1));
    hash1.add(leftFunctionCell);
    assertTrue(leftFunctionCell.hasCyclicReference(null, hash1));
    List<SingleCell> list2 = new ArrayList<SingleCell>();
    list2.add(leftFunctionCell);
    SingleCell leftFunctionCell2 = new LeftFunctionCell(list2);
    list.add(leftFunctionCell2);
    hash1.add(leftFunctionCell2);
    hash1.add(leftFunctionCell);
    assertTrue(leftFunctionCell.hasCyclicReference(null, hash1));

  }

  /**
   * To test the getValue method in the LeftFunctionCell class.
   */
  @Test
  public void getValue() {
    SingleCell stringCell1 = new StringCell("Hello", Enums.ValueType.String);
    SingleCell numCell1 = new NumberCell(4.0, Enums.ValueType.Double);
    List<SingleCell> list = new ArrayList<SingleCell>();
    list.add(stringCell1);
    list.add(numCell1);
    SingleCell left1 = new LeftFunctionCell(list);

    assertEquals(left1.getValue(null, null), "Hell");
    numCell1 = new NumberCell(1.0, Enums.ValueType.String);
    list.remove(1);
    list.add(numCell1);
    assertEquals(left1.getValue(null, null), "H");
  }
}