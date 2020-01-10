package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.model.Enums.OperationType;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;



/**
 * A SumCell is a cell that represents a sum function. It hold SingleCells
 * in it's toEvaluate list that are the components it should sum.
 */
public class SumCell extends FormulaCell<Double> {

  private List<SingleCell> toEvaluate;

  /**
   * A SumCell constructor. It hold SingleCells in it's toEvaluate list
   * that are passed in as arguments here.
   */
  public SumCell(List<SingleCell> toEvaluate) {
    this.toEvaluate = toEvaluate;
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
    else {
      boolean result = false;
      lookup.add(this);
      for (SingleCell cell : toEvaluate) {
        result = result | cell.hasCyclicReference(map, (HashSet<SingleCell>) lookup.clone());
      }

      return result;
    }
  }

  /**
   * Checks if this cell is less than the other cell.
   * @param map A map of the coordinates to cells that we have.
   * @param op The operation being run by the object that is evaluating this cell.
   * @return Returns a boolean representing if this cell is less than the other cell.
   */
  public boolean compareCells(HashMap<Coord, SingleCell> map,
                              OperationType op, SingleCell other) {
    return other.compareToDouble(map, op, this.getValue(map, op));
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
    return other < this.getValue(map, op);
  }

  /**
   * Evaluates the numeric representation of a cell, which for number cells and product/sum cells
   * is their value, but for non-numeric types is 0.
   * @param map A map of the coordinates to cells that we have.
   * @param op The operation being run by the object that is evaluating this cell.
   * @return Returns the numeric value of the cell.
   */
  public double numberRepresentation(HashMap<Coord, SingleCell> map, OperationType op) {
    return getValue(map, op);
  }

  /**
   * Evaluates the value of a cell.
   * @param map A map of the coordinates to cells that we have.
   * @param op The operation being run by the object that is evaluating this cell.
   * @return Returns the correct value for the cell, be it boolean, double, string,
   *         or evaluated formula.
   */
  public Double getValue(HashMap<Coord, SingleCell> map, OperationType op) {
    double sum = 0;
    double cur;

    if (this.toEvaluate.isEmpty()) {
      throw new IllegalArgumentException("Error sum");
    }

    for (SingleCell cell : toEvaluate) {

      cur = cell.numberRepresentation(map, OperationType.SUM);

      sum += cur;
    }

    return sum;
  }

  @Override
  public String inputForm() {
    String output = "(SUM";
    for (SingleCell cell : toEvaluate) {
      output += " ";
      output += cell.inputForm();
    }
    return output + ")";
  }

}
