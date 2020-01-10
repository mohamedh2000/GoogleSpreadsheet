package edu.cs3500.spreadsheets.model;

import java.util.HashMap;

/**
 * A StringCell class that stores a string.
 */
public class StringCell extends ValueCell<String> {

  /**
   * A StringCell class constructor that takes both the String value and the
   * ValueType of the cell. The second part is redundent and will be removed for
   * next assignment, we ran out of time.
   */
  public StringCell(String value, Enums.ValueType cellType) {
    this.value = value;
    this.cellType = cellType;
  }

  /**
   * Checks if this cell is less than the other cell.
   * @param map A map of the coordinates to cells that we have.
   * @param op The operation being run by the object that is evaluating this cell.
   * @return Returns a boolean representing if this cell is less than the other cell.
   */
  public boolean compareCells(HashMap<Coord, SingleCell> map,
                              Enums.OperationType op, SingleCell other) {
    return other.compareToString(map, op, this.value);
  }

  /**
   * Checks if this cell is greater than the given data.
   * @param map A map of the coordinates to cells that we have.
   * @param op The operation being run by the object that is evaluating this cell.
   * @param other The value we are being compared to.
   * @return Returns a boolean representing if this cell is greater than the given data.
   */
  public boolean compareToString(HashMap<Coord, SingleCell> map,
                                  Enums.OperationType op, String other) {
    return other.compareTo(this.value) < 0;
  }

  /**
   * Checks if this cell is greater than the given data.
   * @param map A map of the coordinates to cells that we have.
   * @param op The operation being run by the object that is evaluating this cell.
   * @param other The value we are being compared to.
   * @return Returns a boolean representing if this cell is greater than the given data.
   */
  public boolean compareToBoolean(HashMap<Coord, SingleCell> map,
                                  Enums.OperationType op, Boolean other) {
    return true;
  }

  /**
   * Returns the input style string that was passed in to create this cell.
   * @return    String representing this cell as an input.
   */
  @Override
  public String inputForm() {
    return "\"" + this.value + "\"";
  }

}