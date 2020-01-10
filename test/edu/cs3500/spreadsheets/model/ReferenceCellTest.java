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
 * To test the Reference Cell Class.
 */
public class ReferenceCellTest {

  /**
   * To test the hasCyclicReference method in the Reference Cell class.
   */
  @Test
  public void hasCyclicReference() {
    SingleCell numCell = new NumberCell(50.0, Enums.ValueType.Double);
    SingleCell numCell2 = new NumberCell(10.0, Enums.ValueType.Double);
    List<SingleCell> list1 = new ArrayList<SingleCell>();
    list1.add(numCell);
    list1.add(numCell2);
    HashSet<SingleCell> hash1 = new HashSet<SingleCell>();
    SingleCell ref1 = new ReferenceCell("A1", "A2");
    hash1.add(numCell);
    HashMap<Coord,SingleCell> hash2 = new HashMap<Coord,SingleCell>();
    assertFalse(ref1.hasCyclicReference(hash2, hash1));
    hash1.add(ref1);
    assertTrue(ref1.hasCyclicReference(hash2, hash1));
  }

  /**
   * To test the compareCells method in the Reference Cell class.
   * @throws Exception When It's being compared to anotherCell.
   */
  @Test (expected = IllegalStateException.class)
  public void compareCells() throws Exception {
    SingleCell ref1 = new ReferenceCell("A1", "A2");
    SingleCell numCell = new NumberCell(50.0, Enums.ValueType.Double);

    assertEquals(IllegalArgumentException.class,
            ref1.compareCells(null, null, numCell));
  }

  /**
   * To test the comparetoBoolean method in the ReferenceCell class.
   * @throws Exception if there is a comparison to a boolCell.
   */
  @Test (expected = IllegalStateException.class)
  public void compareToBoolean() throws Exception {
    SingleCell ref1 = new ReferenceCell("A1", "A2");
    SingleCell boolCell = new BoolCell(true, Enums.ValueType.Boolean);

    assertEquals(IllegalArgumentException.class,
            ref1.compareCells(null, null, boolCell));
  }

  /**
   * To test the CompareToDouble method in the ReferenceCell class.
   */
  @Test (expected = IllegalStateException.class)
  public void compareToDouble() {
    SingleCell ref1 = new ReferenceCell("A1", "A2");
    SingleCell numCell = new NumberCell(50.0, Enums.ValueType.Double);

    assertEquals(IllegalArgumentException.class,
            ref1.compareCells(null, null, numCell));
  }

  /**
   * To test the compareToString method in the reference cell class.
   */
  @Test (expected = IllegalStateException.class)
  public void compareToString() {
    SingleCell ref1 = new ReferenceCell("A1", "A2");
    SingleCell stringCell = new StringCell("hello", Enums.ValueType.String);

    assertEquals(IllegalArgumentException.class,
            ref1.compareCells(null, null, stringCell));
  }

  /**
   * To test the number Representation in the ReferenceCell class.
   */
  @Test
  public void numberRepresentation() {
    SingleCell ref1 = new ReferenceCell("A1", "A2");
    SingleCell numCell = new NumberCell(50.0, Enums.ValueType.Double);
    SingleCell numCell2 = new NumberCell(10.0, Enums.ValueType.Double);
    HashMap<Coord, SingleCell> map = new HashMap<>();
    map.put(new Coord(1,1), numCell);
    map.put(new Coord(1,2), numCell2);

    assertEquals(60.0, ref1.numberRepresentation(map, Enums.OperationType.SUM), 0.0);
    assertEquals(500.0, ref1.numberRepresentation(map, Enums.OperationType.PRODUCT), 0.0);
  }

  /**
   * To test the getValue method in the reference Cell class.
   */
  @Test
  public void getValue() {
    SingleCell ref1 = new ReferenceCell("A1", "A2");
    SingleCell numCell = new NumberCell(50.0, Enums.ValueType.Double);
    SingleCell numCell2 = new NumberCell(10.0, Enums.ValueType.Double);
    HashMap<Coord, SingleCell> map = new HashMap<>();
    map.put(new Coord(1,1), numCell);
    map.put(new Coord(1,2), numCell2);

    assertEquals(ref1.getValue(map, Enums.OperationType.SUM),
            60.0);
    assertEquals(ref1.getValue(map, Enums.OperationType.PRODUCT),
            500.0);
  }
}