package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.model.Enums.OperationType;
import java.util.HashMap;
import java.util.HashSet;

/**
 * A SingleReferenceCell class that holds a reference to a single cell, ex: "A1 A2" would
 * mean this cell is A1 and it points towards cell A2.
 */
public class SingleReferenceCell<K> extends FormulaCell<K> {

  private String coord;

  /**
   * A SingleReferenceCell constructor that takes a string representing the coordinate
   * this cell references.
   */
  public SingleReferenceCell(String coord) {
    this.coord = coord;
  }

  /**
   * Checks to see if a cell has any cyclic reference. Currently works recursivly and creates
   * clones on branches which is inefficient so hopefully we can change that soon.
   * @param map A map of the coordinates to cells that we have.
   * @param lookup The table of Cells that we've already seen.
   * @return Returns a boolean representing if a cycle is present in this cell.
   */
  @Override
  public boolean hasCyclicReference(HashMap<Coord, SingleCell> map, HashSet<SingleCell> lookup) {
    if (lookup.contains(this)) {
      return true;
    }
    if (!cellExists(map)) {
      return false;
    }
    lookup.add(this);
    return getCell(map).hasCyclicReference(map, lookup);
  }

  /**
   * Evaluates the numeric representation of a cell, which for number cells and product/sum cells
   * is their value, but for non-numeric types is 0.
   * @param map A map of the coordinates to cells that we have.
   * @param op The operation being run by the object that is evaluating this cell.
   * @return Returns the numeric value of the cell.
   */
  public double numberRepresentation(HashMap<Coord, SingleCell> map, OperationType op) {
    if (!cellExists(map)) {
      return 0.0;
    }

    SingleCell newCell = getCell(map);

    return newCell.numberRepresentation(map, op);
  }

  /**
   * Checks if this cell is less than the other cell.
   * @param map A map of the coordinates to cells that we have.
   * @param op The operation being run by the object that is evaluating this cell.
   * @return Returns a boolean representing if this cell is less than the other cell.
   */
  public boolean compareCells(HashMap<Coord, SingleCell> map,
      OperationType op, SingleCell other) {
    if (cellExists(map)) {
      return getCell(map).compareCells(map, op, other);
    }
    else {
      return true;
    }
  }

  /**
   * Checks if this cell is greater than the given data.
   * @param map A map of the coordinates to cells that we have.
   * @param op The operation being run by the object that is evaluating this cell.
   * @param other The value we are being compared to.
   * @return Returns a boolean representing if this cell is greater than the given data.
   */
  public boolean compareToBoolean(HashMap<Coord, SingleCell> map,
      OperationType op, Boolean other) {
    if (cellExists(map)) {
      return getCell(map).compareToBoolean(map, op, other);
    }
    else {
      return false;
    }
  }

  /**
   * Checks if this cell is greater than the given data.
   * @param map A map of the coordinates to cells that we have.
   * @param op The operation being run by the object that is evaluating this cell.
   * @param other The value we are being compared to.
   * @return Returns a boolean representing if this cell is greater than the given data.
   */
  public boolean compareToDouble(HashMap<Coord, SingleCell> map,
      OperationType op, Double other) {
    if (cellExists(map)) {
      return getCell(map).compareToDouble(map, op, other);
    }
    else {
      return false;
    }
  }

  /**
   * Checks if this cell is greater than the given data.
   * @param map A map of the coordinates to cells that we have.
   * @param op The operation being run by the object that is evaluating this cell.
   * @param other The value we are being compared to.
   * @return Returns a boolean representing if this cell is greater than the given data.
   */
  public boolean compareToString(HashMap<Coord, SingleCell> map,
      OperationType op, String other) {
    if (cellExists(map)) {
      return getCell(map).compareToString(map, op, other);
    }
    else {
      return false;
    }
  }

  /**
   * Parses the string coordinate we store and returns a Coord object.
   * @return Returns the Coord object represented by our string coord.
   */
  private Coord cellCoord() {
    String[] coordParts = coord.split("(?<=\\D)(?=\\d)");

    int col = Coord.colNameToIndex(coordParts[0]);
    int row = Integer.parseInt(coordParts[1]);

    System.out.println(String.format("Row: %d, Col: %d", row, col));

    return new Coord(col, row);
  }

  /**
   * Gets the SingelCell at the coordinate coord.
   * @param map A map of the coordinates to cells that we have.
   * @return Returns a the SingelCell at the coordinate help by this cell.
   */
  private SingleCell getCell(HashMap<Coord, SingleCell> map) {
    SingleCell cell = map.get(cellCoord());

    return cell;
  }

  /**
   * Checks if this cell exists in the given map.
   * @param map A map of the coordinates to cells that we have.
   * @return Returns a boolean representing if this cell is in the map.
   */
  private boolean cellExists(HashMap<Coord, SingleCell> map) {
    return map.containsKey(cellCoord());
  }

  /**
   * Evaluates the value of a cell.
   * @param map A map of the coordinates to cells that we have.
   * @param op The operation being run by the object that is evaluating this cell.
   * @return Returns the correct value for the cell, be it boolean, double, string,
   *         or evaluated formula.
   */
  public K getValue(HashMap<Coord, SingleCell> map, OperationType op) {

    // empty cell
    if (!cellExists(map)) {
      SingleCell<String> strCell = new StringCell("", Enums.ValueType.String);
      map.put(cellCoord(), strCell);
    }

    SingleCell<K> newCell = getCell(map);

    return newCell.getValue(map, op);
  }

  @Override
  public String inputForm() {
    return this.coord;
  }

}
