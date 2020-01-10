package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.model.Enums.OperationType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * A Reference Cell which holds the references needed for the evaluation.
 */
public class ReferenceCell extends FormulaCell<Double> {

  private String start;
  private String end;

  public ReferenceCell(String start, String end) {
    this.start = start;
    this.end = end;
  }

  @Override
  public boolean hasCyclicReference(HashMap<Coord, SingleCell> map, HashSet<SingleCell> lookup) {
    if (lookup.contains(this)) {
      return true;
    }
    else {
      boolean result = false;
      lookup.add(this);
      ArrayList<SingleCell> toEvaluate = getCells(map);
      for (SingleCell cell : toEvaluate) {
        result = result | cell.hasCyclicReference(map, lookup);
      }

      return result;
    }
  }

  public boolean compareCells(HashMap<Coord, SingleCell> map,
      OperationType op, SingleCell other) {
    throw new IllegalStateException("Error comp");
  }

  public boolean compareToBoolean(HashMap<Coord, SingleCell> map,
      OperationType op, Boolean other) {
    throw new IllegalStateException("Error comp");
  }

  public boolean compareToDouble(HashMap<Coord, SingleCell> map,
      OperationType op, Double other) {
    throw new IllegalStateException("Error comp");
  }

  public boolean compareToString(HashMap<Coord, SingleCell> map,
      OperationType op, String other) {
    throw new IllegalStateException("Error comp");
  }

  public double numberRepresentation(HashMap<Coord, SingleCell> map, OperationType op) {
    return getValue(map, op);
  }

  private boolean cellExists(HashMap<Coord, SingleCell> map, Coord cellCoord) {
    return map.containsKey(cellCoord);
  }

  private ArrayList<SingleCell> getCells(HashMap<Coord, SingleCell> map) {
    String[] startParts = start.split("(?<=\\D)(?=\\d)");
    String[] endParts = end.split("(?<=\\D)(?=\\d)");

    try {

      int startCol = Coord.colNameToIndex(startParts[0]);
      int endCol = Coord.colNameToIndex(endParts[0]);

      int startRow = Integer.parseInt(startParts[1]);
      int endRow = Integer.parseInt(endParts[1]);

      // If these are negative, an integer was the first part of the reference
      if (startCol < 1 | endCol < 0) {
        throw new IllegalStateException("Error neg");
      }

      ArrayList<SingleCell> cellList = new ArrayList<>();
      Coord cur;

      for (int col = startCol; col <= endCol; col++) {
        for (int row = startRow; row <= endRow; row++) {
          cur = new Coord(col, row);
          if (!cellExists(map, cur)) {
            SingleCell<String> strCell = new StringCell("", Enums.ValueType.String);
            map.put(cur, strCell);
          }
          cellList.add(map.get(cur));
        }
      }

      return cellList;
    }
    // ArrayIndex error occurs when there's no letter identifcation for the column
    // NumberFormatException occurs whens there's some non-Integer value in the row identifier
    catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
      throw new IllegalStateException("Error ref");
    }

  }

  /**
   * Gets the Values of the References by making new Operation Cells with the
   * cells of the Reference.
   * @param map The WorkSheet data you're working with.
   * @param op The operation you're doing.
   * @return The value of the Reference cell based on what Operation Type.
   */
  public Double getValue(HashMap<Coord, SingleCell> map, OperationType op) {
    SingleCell newCell;

    if (op.equals(OperationType.SUM)) {
      newCell = new SumCell(getCells(map));
    }
    else if (op.equals(OperationType.PRODUCT)) {
      newCell = new ProductCell(getCells(map));
    }
    else if (op.equals(OperationType.LESS)) {
      newCell = new LessThanCell(getCells(map));
    }
    else if (op.equals(OperationType.STRING)) {
      newCell = new SumCell(getCells(map));
    }
    // Base case is going to be the NONE case
    // We can allow this since we know that a reference cell is only ever called with NONE
    // if it is evaluated from the worksheet so it is a raw cell value, and we are implementing
    // Cell = [start:end] as invalid so we'll print an error.
    else {
      throw new IllegalArgumentException("Cell can't be reference");
    }

    return newCell.numberRepresentation(map, op);
  }

  @Override
  public String inputForm() {
    return "[" + this.start + ":" + this.end + "]";
  }

}
