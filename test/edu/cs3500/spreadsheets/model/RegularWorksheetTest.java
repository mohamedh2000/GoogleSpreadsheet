package edu.cs3500.spreadsheets.model;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * To test the regularWorkSheet class.
 */
public class RegularWorksheetTest {

  /**
   * To test the assignAccord method in the regularWorsheet class.
   */
  @Test
  public void assignCoord() {
    SingleCell numCell = new NumberCell(50.0, Enums.ValueType.Double);
    HashMap<Coord, SingleCell> map = new HashMap<>();

    RegularWorksheet regWork = new RegularWorksheet();
    regWork.assignCoord(new Coord(1,1),numCell);

    map.put(new Coord(1,1), numCell);
    //to way to get to worksheet data in the
    // regular worksheet so going to recreate it
    assertEquals(numCell, map.get(new Coord(1,1)));
  }

  /**
   * To test the evaluate cell method in the Regular Worksheet class.
   */
  @Test
  public void evaluateCell() {
    SingleCell numCell = new NumberCell(50.0, Enums.ValueType.Double);
    SingleCell stringCell = new StringCell("Hello", Enums.ValueType.String);

    RegularWorksheet regWork = new RegularWorksheet();
    regWork.assignCoord(new Coord(1,1),numCell);
    regWork.assignCoord(new Coord(1,2),stringCell);

    assertEquals(regWork.evaluateCell(new Coord(1, 1)), 50.0);
    assertEquals(regWork.evaluateCell(new Coord(1,2)), "Hello");
  }

}