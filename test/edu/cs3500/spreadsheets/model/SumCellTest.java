package edu.cs3500.spreadsheets.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * To test the Sum Cell Class.
 */
public class SumCellTest {

  /**
   * To test the hasCyclicReference method in the SumCell class.
   */
  @Test
  public void hasCyclicReference() {
    SingleCell doubleCell1 = new NumberCell(2.0, Enums.ValueType.Double);
    SingleCell doubleCell2 = new NumberCell(2.0, Enums.ValueType.Double);
    SingleCell doubleCell3 = new NumberCell(5.0, Enums.ValueType.Double);
    ArrayList<SingleCell> array = new ArrayList<SingleCell>();
    array.add(doubleCell1);
    array.add(doubleCell2);
    array.add(doubleCell3);

    SingleCell sumCell = new SumCell(array);
    HashSet<SingleCell> set1 = new HashSet<SingleCell>();

    assertFalse(sumCell.hasCyclicReference(null,set1));
    set1.add(sumCell);
    assertTrue(sumCell.hasCyclicReference(null, set1));

  }

  /**
   * To test the compareCells method in the Sum Cell class.
   */
  @Test
  public void compareCells() {
    SingleCell doubleCell1 = new NumberCell(2.0, Enums.ValueType.Double);
    SingleCell doubleCell2 = new NumberCell(2.0, Enums.ValueType.Double);
    SingleCell doubleCell3 = new NumberCell(5.0, Enums.ValueType.Double);
    ArrayList<SingleCell> array = new ArrayList<SingleCell>();
    array.add(doubleCell1);
    array.add(doubleCell2);
    array.add(doubleCell3);

    SingleCell sumCell = new SumCell(array);
    SingleCell prodCell = new ProductCell(array);

    assertEquals(sumCell.getValue(null, null), 9.0);
    assertEquals(prodCell.getValue(null, null), 20.0);

    //if sumCell is smaller than Prod Cell.
    assertTrue(sumCell.compareCells(null,null, prodCell));
  }

  /**
   * To test the compareToDouble method in the SumCell class.
   */
  @Test
  public void compareToDouble() {
    SingleCell doubleCell1 = new NumberCell(2.0, Enums.ValueType.Double);
    SingleCell doubleCell2 = new NumberCell(2.0, Enums.ValueType.Double);
    SingleCell doubleCell3 = new NumberCell(5.0, Enums.ValueType.Double);
    ArrayList<SingleCell> array = new ArrayList<SingleCell>();
    array.add(doubleCell1);
    array.add(doubleCell2);
    array.add(doubleCell3);

    SingleCell sumCell = new SumCell(array);
    SingleCell prodCell = new ProductCell(array);

    assertEquals(sumCell.getValue(null, null), 9.0);
    assertEquals(prodCell.getValue(null, null), 20.0);

    assertFalse(sumCell.compareToDouble(null, null,
            20.0));
    assertTrue(prodCell.compareToDouble(null, null, 9.0));
  }

  /**
   * To test the numberRepresentation method in the sumCell class.
   */
  @Test
  public void numberRepresentation() {
    SingleCell doubleCell1 = new NumberCell(2.0, Enums.ValueType.Double);
    SingleCell doubleCell2 = new NumberCell(2.0, Enums.ValueType.Double);
    ArrayList<SingleCell> array = new ArrayList<SingleCell>();
    array.add(doubleCell1);
    array.add(doubleCell2);
    SingleCell sumCell = new SumCell(array);
    assertEquals(4.0, sumCell.numberRepresentation(null, null), 0.0);
  }

  /**
   * To test the getValue method in the SumCell class.
   */
  @Test
  public void getValue() {
    SingleCell doubleCell1 = new NumberCell(2.0, Enums.ValueType.Double);
    SingleCell doubleCell2 = new NumberCell(2.0, Enums.ValueType.Double);
    SingleCell doubleCell3 = new NumberCell(5.0, Enums.ValueType.Double);
    SingleCell strCell = new StringCell("hi", Enums.ValueType.String);
    SingleCell boolCell = new BoolCell(Boolean.FALSE, Enums.ValueType.Boolean);

    ArrayList<SingleCell> array = new ArrayList<SingleCell>();
    array.add(doubleCell1);
    array.add(doubleCell2);
    FormulaCell<Double> sumCell = new SumCell(array);
    assertEquals(4.0, sumCell.getValue(null, null), 0.0);
    array.add(doubleCell3);
    assertEquals(9.0, sumCell.getValue(null, null), 0.0);
    array.add(strCell);
    array.add(boolCell);
    assertEquals(9.0, sumCell.getValue(null, null), 0.0);
  }

}
