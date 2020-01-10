package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.model.Enums.OperationType;
import edu.cs3500.spreadsheets.model.Enums.ValueType;
import java.util.HashMap;

/**
 * A ValueCell of type K that is meant as a generic parent class
 * for the bool, string, and number cells.
 * @param <K> A Generic for the Type of Value Cell type.
 */
public class ValueCell<K> extends FormulaCell<K> {

  protected K value;
  protected ValueType cellType;

  /**
   * A Value cell constructor that assigns the given value and ValueType.
   * @param value The value type.
   * @param cellType The cell Type of the ValueCell.
   */
  public ValueCell(K value, ValueType cellType) {
    this.value = value;
    this.cellType = cellType;
  }

  /**
   * A Value cell constructor default.
   */
  public ValueCell(){
    /**
     * Initialize an empty valueCell constructor. Error in Intellj without it.
     */
  }

  /**
   * Evaluates the numeric representation of a cell, which for number cells and product/sum cells
   * is their value, but for non-numeric types is 0.
   * @param map A map of the coordinates to cells that we have.
   * @param op The operation being run by the object that is evaluating this cell.
   * @return Returns the numeric value of the cell.
   */
  public double numberRepresentation(HashMap<Coord, SingleCell> map,
                                     OperationType op) {
    return 0;
  }

  /**
   * Evaluates the numeric representation of a cell, which for number cells and product/sum cells
   * is their value, but for non-numeric types is 0.
   * @param map A map of the coordinates to cells that we have.
   * @param op The operation being run by the object that is evaluating this cell.
   * @return Returns the numeric value of the cell.
   */
  public K getValue(HashMap<Coord, SingleCell> map, OperationType op) {
    return this.value;
  }

}
