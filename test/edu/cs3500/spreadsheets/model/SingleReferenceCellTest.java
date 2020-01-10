package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.model.Enums.ValueType;
import java.util.HashMap;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * To test the SingleReferenceCell class.
 */
public class SingleReferenceCellTest {

  /**
   * To test the compareCells method in the SingleReferenceCell class.
   */
  @Test
  public void compareCells() {
    HashMap<Coord, SingleCell> map = new HashMap<>();

    SingleCell bool1 = new BoolCell(false, Enums.ValueType.Boolean);
    SingleCell bool2 = new BoolCell(true, Enums.ValueType.Boolean);

    map.put(new Coord(1,1), bool1);
    map.put(new Coord(1,2), bool2);

    SingleCell ref = new SingleReferenceCell("A1");
    assertFalse(ref.compareCells(map, null, bool2));
    assertFalse(bool1.compareCells(map, null, ref));
    assertFalse(ref.compareCells(map, null, bool1));
    assertTrue(bool2.compareCells(map, null, ref));

  }

  /**
   * To test the compareToBoolean method in the SingleReferenceCell class.
   */
  @Test
  public void compareToBoolean() {
    HashMap<Coord, SingleCell> map = new HashMap<>();

    SingleCell bool1 = new BoolCell(false, Enums.ValueType.Boolean);
    SingleCell bool2 = new BoolCell(true, Enums.ValueType.Boolean);

    map.put(new Coord(1,1), bool1);
    map.put(new Coord(1,2), bool2);

    SingleCell ref = new SingleReferenceCell("A1");
    assertFalse(ref.compareCells(map, null, bool2));
    assertFalse(bool1.compareCells(map, null, ref));
    assertFalse(ref.compareCells(map, null, bool1));
    assertTrue(bool2.compareCells(map, null, ref));
  }

  /**
   * To test the compareToNumber method in the SingleReferenceCell class.
   */
  @Test
  public void compareToNumber() {
    HashMap<Coord, SingleCell> map = new HashMap<>();

    SingleCell num1 = new NumberCell(10.0, ValueType.Double);
    SingleCell num2 = new NumberCell(9.0, Enums.ValueType.Double);

    map.put(new Coord(1,1), num1);
    map.put(new Coord(1,2), num2);

    SingleCell ref = new SingleReferenceCell("A1");
    assertFalse(ref.compareCells(map, null, num2));
    assertFalse(num1.compareCells(map, null, ref));
    assertFalse(ref.compareCells(map, null, num1));
    assertTrue(num2.compareCells(map, null, ref));
  }

  /**
   * To test the compareToDouble method in the SingleReferenceCell class.
   */
  @Test
  public void compareToDouble() {
    HashMap<Coord, SingleCell> map = new HashMap<>();

    SingleCell num1 = new NumberCell(10.0, ValueType.Double);
    SingleCell num2 = new NumberCell(9.0, Enums.ValueType.Double);

    map.put(new Coord(1,1), num1);
    map.put(new Coord(1,2), num2);

    SingleCell ref = new SingleReferenceCell("A1");
    assertFalse(ref.compareCells(map, null, num2));
    assertFalse(num1.compareCells(map, null, ref));
    assertFalse(ref.compareCells(map, null, num1));
    assertTrue(num2.compareCells(map, null, ref));
  }

  /**
   * To test the compareToString method in the SingleReferenceCell class.
   */
  @Test
  public void compareToString() {
    HashMap<Coord, SingleCell> map = new HashMap<>();

    SingleCell str1 = new StringCell("you", ValueType.String);
    SingleCell str2 = new StringCell("me", ValueType.String);

    map.put(new Coord(1,1), str1);
    map.put(new Coord(1,2), str2);

    SingleCell ref = new SingleReferenceCell("A1");
    assertFalse(ref.compareCells(map, null, str2));
    assertFalse(str1.compareCells(map, null, ref));
    assertFalse(ref.compareCells(map, null, str1));
    assertTrue(str2.compareCells(map, null, ref));

  }
}