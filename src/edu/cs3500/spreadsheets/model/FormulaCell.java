package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.model.Enums.OperationType;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Represents a FormulaCell which is a Single Cell.
 * @param <K> A Generic type of the FormulaCell.
 */
public class FormulaCell<K> implements SingleCell<K> {

  /**
   * Initializes the FormulaCell Class. Intellj gives Error if this is not present.
   */
  public FormulaCell(){
    /**
     * Intellj gives error if this is not present.
     */
  }

  /** FormulaCell can take in the operation and the cells to be evaluated.
   * Constructor to make sure
   * @param operation the operation passed in the cell.
   * @param toEvaluate The List of cells that need to be evaluated.
   */
  public FormulaCell(String operation, List<SingleCell> toEvaluate) {
    /**
     * FormulaCell will redirect them.
     */
  }

  @Override
  public boolean hasCyclicReference(HashMap<Coord, SingleCell> map, HashSet<SingleCell> lookup) {
    return false;
  }

  public double numberRepresentation(HashMap<Coord, SingleCell> map, OperationType op) {
    return 0;
  }

  public boolean compareCells(HashMap<Coord, SingleCell> map,
      OperationType op, SingleCell other) {
    return false;
  }

  public boolean compareToBoolean(HashMap<Coord, SingleCell> map,
      OperationType op, Boolean other) {
    return false;
  }

  public boolean compareToDouble(HashMap<Coord, SingleCell> map,
      OperationType op, Double other) {
    return false;
  }

  public boolean compareToString(HashMap<Coord, SingleCell> map,
      OperationType op, String other) {
    return false;
  }

  /**
   * Returns the input style string that was passed in to create this cell.
   * @return    String representing this cell as an input.
   */
  @Override
  public String inputForm() {
    return getValue(null, null).toString() + " ";
  }

  /**
   * Returns value of the Formula Cell.
   * @param map A map of the coordinates to cells that we have.
   * @param op The operation being run by the object that is evaluating this cell.
   * @return Should not return anything as it itself is not a returning a value.
   */
  public K getValue(HashMap<Coord, SingleCell> map, OperationType op) {
    /**
     * FIX THIS NOT WORKING
     * RETURN RESULT OF FORMULA
     */
    return null;
  }

}
