package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.model.Enums.OperationType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Represents the Less than Operation which compares the values of two cells.
 * @param <K> The generic type of the Cell.
 */
public class LessThanCell<K> extends FormulaCell<Boolean> {

  private List<SingleCell> toEvaluate;

  public LessThanCell(List<SingleCell> toEvaluate) {
    this.toEvaluate = toEvaluate;
  }

  @Override
  public boolean hasCyclicReference(HashMap<Coord, SingleCell> map, HashSet<SingleCell> lookup) {
    if (lookup.contains(this)) {
      return true;
    }
    else if (checkValidInput(map)) {
      lookup.add(this);
      return toEvaluate.get(0).hasCyclicReference(map, lookup) |
          toEvaluate.get(1).hasCyclicReference(map, lookup);
    }
    return false;
  }

  private boolean checkValidInput(HashMap<Coord, SingleCell> map) {
    return this.toEvaluate.size() == 2;
  }

  private boolean checkCyclicReference(HashMap<Coord, SingleCell> map) {
    return false;
  }

  /**
   * To get the value of the Less than Cell.
   * @param map The worksheet.
   * @param op Operation.
   * @return Returns a boolean if the value of this is bigger than the comparative cell.
   */
  public Boolean getValue(HashMap<Coord, SingleCell> map, OperationType op) {

    if (!checkValidInput(map)) {
      throw new IllegalStateException("Error less");
    }

    SingleCell firstCell = toEvaluate.get(0);
    SingleCell secondCell = toEvaluate.get(1);

    return firstCell.compareCells(map, op, secondCell);
  }

  @Override
  public String inputForm() {
    String output = "(<";
    for (SingleCell cell : toEvaluate) {
      output += " ";
      output += cell.inputForm();
    }
    return output + ")";
  }

}
