package edu.cs3500.spreadsheets.sexp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import edu.cs3500.spreadsheets.model.Enums;
import edu.cs3500.spreadsheets.model.FormulaCell;
import edu.cs3500.spreadsheets.model.NumberCell;
import edu.cs3500.spreadsheets.model.SingleCell;
import edu.cs3500.spreadsheets.model.SumCell;
import edu.cs3500.spreadsheets.model.ValueCell;

/**
 * To test IdentifyInput.
 */
public class IdentifyInputTest {

  private IdentifyInput inputHandle = new IdentifyInput();

  @Test
  public void visitBoolean() {
    String input1 = "true";
    FormulaCell<Boolean> expectedOutPut1 =
            new ValueCell<Boolean>(true, Enums.ValueType.Boolean);
    assertEquals(new SBoolean(true), Parser.parse(input1));
    assertEquals(expectedOutPut1.getValue(null,null),
            inputHandle.visitBoolean(true).getValue(null,null));
    assertEquals(expectedOutPut1.getValue(null, null), true);
    assertEquals(inputHandle.visitBoolean(true).getValue(null, null), true);
  }

  @Test
  public void visitNumber() {
    String input2 = "50.2";
    FormulaCell<Double> expectedOutPut2 = new ValueCell<Double>(50.2,Enums.ValueType.Double);
    assertEquals(new SNumber(50.2), Parser.parse(input2));
    assertEquals(expectedOutPut2.getValue(null, null),
            inputHandle.visitNumber(50.2).getValue(null, null));
    assertEquals(inputHandle.visitNumber(50.2).getValue(null, null), 50.2);
    assertEquals(50.2, expectedOutPut2.getValue(null, null), 0.0);
  }

  @Test
  public void visitSList() {
    String input3 = "(Sum 2 2)";
    ArrayList<SingleCell> array1 = new ArrayList<SingleCell>();
    array1.add(new NumberCell(2.0, Enums.ValueType.Double));
    array1.add(new NumberCell(2.0, Enums.ValueType.Double));
    FormulaCell<Double> sumCell = new SumCell(array1);

    List<Sexp> list1 = new ArrayList<Sexp>();
    list1.add(new SString("SUM"));
    list1.add(new SNumber(2.0));
    list1.add(new SNumber(2.0));

    assertTrue(inputHandle.visitSList(list1) instanceof SumCell);
    assertEquals(java.util.Optional.of(4.0), sumCell.getValue(null, Enums.OperationType.SUM));
    assertEquals(4.0, inputHandle.visitSList(list1).getValue(null, Enums.OperationType.SUM));
    assertEquals(sumCell.getValue(null, Enums.OperationType.SUM),
            inputHandle.visitSList(list1).getValue(null, null));
  }

  @Test
  public void visitSymbol() {
    String input4 = "A1";
    FormulaCell<String> expectedOutPut4 = new ValueCell<String>("A1",Enums.ValueType.String);
    assertEquals(new SSymbol("A1"), Parser.parse(input4));
    assertEquals(expectedOutPut4.getValue(null, null),
            inputHandle.visitSymbol("A1").getValue(null, null));
    assertEquals(inputHandle.visitSymbol("A1").getValue(null, null), "A1");
    assertEquals("A1", expectedOutPut4.getValue(null, null));
  }

  @Test
  public void visitString() {
    String input5 = "Hello";
    FormulaCell<String> expectedOutPut5 = new ValueCell<String>("Hello",Enums.ValueType.String);
    assertEquals(new SString("Hello"), Parser.parse(input5));
    assertEquals(expectedOutPut5.getValue(null, null),
            inputHandle.visitString("Hello").getValue(null, null));
    assertEquals(inputHandle.visitString("A1").getValue(null, null), "Hello");
    assertEquals("Hello", expectedOutPut5.getValue(null, null));
  }
}