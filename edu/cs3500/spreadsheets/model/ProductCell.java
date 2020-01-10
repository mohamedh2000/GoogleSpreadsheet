package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.model.Enums.OperationType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * A ProductCell Class which represents the product operation.
 */
public class ProductCell extends FormulaCell<Double> {

  private List<SingleCell> toEvaluate;

  public ProductCell(List<SingleCell> toEvaluate) {
    this.toEvaluate = toEvaluate;
  }

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

  public boolean compareCells(HashMap<Coord, SingleCell> map,
      OperationType op, SingleCell other) {
    return other.compareToDouble(map, op, this.getValue(map, op));
  }

  public boolean compareToDouble(HashMap<Coord, SingleCell> map,
      OperationType op, Double other) {
    return other < this.getValue(map, op);
  }

  private boolean checkCyclicReference(HashMap<Coord, SingleCell> map) {
    return false;
  }

  public double numberRepresentation(HashMap<Coord, SingleCell> map, OperationType op) {
    return getValue(map, op);
  }

  /**
   * Represents the get Value Method.
   * @param map The worksheet you're working with.
   * @param op The operation type you want to get the value for.
   * @return The value of the Product Cell as everythings multiplied.
   */
  public Double getValue(HashMap<Coord, SingleCell> map, OperationType op) {
    double sum = 0;
    double cur;

    if (this.toEvaluate.isEmpty()) {
      throw new IllegalArgumentException("Error prod");
    }

    for (SingleCell<?> cell : toEvaluate) {
      cur = cell.numberRepresentation(map, OperationType.PRODUCT);

      // check if the cellVal can be parsed as double
      if (sum == 0) {
        sum = cur;
      }
      else if (cur != 0) {
        sum *= cur;
      }
    }

    return sum;
  }

  @Override
  public String inputForm() {
    String output = "(PRODUCT";
    for (SingleCell cell : toEvaluate) {
      output += " ";
      output += cell.inputForm();
    }
    return output + ")";
  }

}
