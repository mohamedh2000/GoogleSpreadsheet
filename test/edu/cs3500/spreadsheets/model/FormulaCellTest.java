package edu.cs3500.spreadsheets.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * To test the FormulaCell Class.
 */
public class FormulaCellTest {

  /**
   * To test the cyclicReference method in the FormulaCell class.
   */
  @Test
  public void hasCyclicReference() {
    List<SingleCell> list1 = new ArrayList<SingleCell>();
    SingleCell formulaCell = new FormulaCell(null, list1);
    assertFalse(formulaCell.hasCyclicReference(null, null));
  }

  /**
   *To Test the Number Representation method in the FormulaCell class.
   */
  @Test
  public void numberRepresentation() {
    List<SingleCell> list1 = new ArrayList<SingleCell>();
    SingleCell formulaCell = new FormulaCell(null, list1);
    assertEquals(0, formulaCell.numberRepresentation(null, null));
  }

  /**
   * To test the compareCells method in the formulaCell class.
   */
  @Test
  public void compareCells() {
    List<SingleCell> list1 = new ArrayList<SingleCell>();
    SingleCell formulaCell = new FormulaCell(null, list1);
    SingleCell numCell = new NumberCell(50.0, Enums.ValueType.Double);
    assertFalse(formulaCell.compareCells(null, null,numCell));
    assertFalse(formulaCell.compareCells(null, null,formulaCell));
  }

  /**
   * To Test the comparetoBoolean method in the FormulaCell Class.
   */
  @Test
  public void compareToBoolean() {
    List<SingleCell> list1 = new ArrayList<SingleCell>();
    SingleCell formulaCell = new FormulaCell(null, list1);
    assertFalse(formulaCell.compareToBoolean(null, null, false));
  }

  /**
   * To test the ComparetoDouble method in the FormulaCell class.
   */
  @Test
  public void compareToDouble() {
    List<SingleCell> list1 = new ArrayList<SingleCell>();
    SingleCell formulaCell = new FormulaCell(null, list1);
    assertFalse(formulaCell.compareToDouble(null, null, 50.0));
  }

  /**
   * To test the compareToString method in the FormulaCell class.
   */
  @Test
  public void compareToString() {
    List<SingleCell> list1 = new ArrayList<SingleCell>();
    SingleCell formulaCell = new FormulaCell(null, list1);
    assertFalse(formulaCell.compareToString(null, null, "hello"));
  }

}