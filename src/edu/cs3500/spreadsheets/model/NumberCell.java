package edu.cs3500.spreadsheets.model;

import java.text.DecimalFormat;
import java.util.HashMap;

/**
 * The NumberCell class which represents the number value Type.
 */
public class NumberCell extends ValueCell<Double> {

  public NumberCell(Double value, Enums.ValueType cellType) {
    this.value = value;
    this.cellType = cellType;
  }

  public boolean compareCells(HashMap<Coord, SingleCell> map,
                              Enums.OperationType op, SingleCell other) {
    return other.compareToDouble(map, op, this.value);
  }

  public boolean compareToDouble(HashMap<Coord, SingleCell> map,
                                 Enums.OperationType op, Double other) {
    return other < this.value;
  }

  public double numberRepresentation(HashMap<Coord, SingleCell> map,
                                     Enums.OperationType op) {
    DecimalFormat df = new DecimalFormat("#.#####");
    return this.value;
  }
}
