package edu.cs3500.spreadsheets.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Represents a LeftFunction Cell Operation.
 */
public class LeftFunctionCell extends FormulaCell<String> {

  private List<SingleCell> toEvaluate;

  public LeftFunctionCell(List<SingleCell> toEvaluate) {
    this.toEvaluate = toEvaluate;
  }

  @Override
  public boolean hasCyclicReference(HashMap<Coord, SingleCell> map, HashSet<SingleCell> lookup) {
    if (lookup.contains(this)) {
      return true;
    }
    else if (checkInvalidInput(map)) {
      lookup.add(this);
      return toEvaluate.get(0).hasCyclicReference(map, lookup) |
          toEvaluate.get(1).hasCyclicReference(map, lookup);
    }
    return false;
  }

  private boolean checkInvalidInput(HashMap<Coord, SingleCell> map) {
    // We can only expect 2 inputs
    return this.toEvaluate.size() != 2;
  }

  private boolean checkCyclicReference(HashMap<Coord, SingleCell> map) {
    return false;
  }

  /**
   * Gets the value by returning the String's index based on the number passed in.
   * @param map The worksheet you're working with.
   * @param op The operation type.
   * @return The String Value.
   */
  public String getValue(HashMap<Coord, SingleCell> map, Enums.OperationType op) {
    SingleCell firstCell = toEvaluate.get(0);
    SingleCell secondCell = toEvaluate.get(1);

    Double substringIndex = secondCell.numberRepresentation(map, op);

    if (checkInvalidInput(map)) {
      throw new IllegalStateException("Error left");
    }

    return firstCell.getValue(map, op)
            .toString().substring(0, substringIndex.intValue());
  }

  @Override
  public String inputForm() {
    String output = "(Left";
    for (SingleCell cell : toEvaluate) {
      output += " ";
      output += cell.inputForm();
    }
    return output + ")";
  }

}
