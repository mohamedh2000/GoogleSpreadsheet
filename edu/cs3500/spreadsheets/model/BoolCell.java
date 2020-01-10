package edu.cs3500.spreadsheets.model;

import java.util.HashMap;

/**
 * A BoolCell class which is one of the three types of values a cell can be.
 */
public class BoolCell extends ValueCell<Boolean> {
  /**
   * a boolean value and boolean type have to be passed in.
   * @param value the boolean value of the cell.
   * @param cellType The cell type.
   */
  public BoolCell(Boolean value, Enums.ValueType cellType) {
    this.value = value;
    this.cellType = cellType;
  }

  /**
   * You can compare other cells values to this cell.
   * @param map the current worksheet/grid you're working with.
   * @param op The cell operation such a SUM, PRODUCT, etc.
   * @param other The other cell that you're comparing this cell to.
   * @return Checks other cells value is larger than the current cells.
   */
  public boolean compareCells(HashMap<Coord, SingleCell> map,
      Enums.OperationType op, SingleCell other) {
    return other.compareToBoolean(map, op, this.value);
  }

  /**
   * Compare if the other boolean and the value of this cell are both true.
   * @param map the current worksheet/grid you're working with.
   * @param op The cell operation such a SUM, PRODUCT, etc.
   * @param other The other cell that you're comparing this cell to.
   * @return should return a boolean showing if they're both true.
   */
  public boolean compareToBoolean(HashMap<Coord, SingleCell> map,
      Enums.OperationType op, Boolean other) {
    return other & !(this.value);
  }

  /**
   * Checking if the other double of this cell is larger than the other double.
   * @param map the current worksheet/grid you're working with.
   * @param op The cell operation such a SUM, PRODUCT, etc.
   * @param other The other cell that you're comparing this cell to.
   * @return true, as there is no boolean here to compare it to.
   */
  public boolean compareToDouble(HashMap<Coord, SingleCell> map,
      Enums.OperationType op, Double other) {
    return true;
  }

  /**
   * Seeing if the String of the other is lexographically larger/better than this cells string.
   * @param map the current worksheet/grid you're working with.
   * @param op The cell operation such a SUM, PRODUCT, etc.
   * @param other The other cell that you're comparing this cell to.
   * @return
   */
  public boolean compareToString(HashMap<Coord, SingleCell> map,
      Enums.OperationType op, String other) {
    return true;
  }

}
