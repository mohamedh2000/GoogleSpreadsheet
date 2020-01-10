package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.model.Enums.OperationType;
import java.util.HashMap;
import java.util.HashSet;

/**
 * A SingleCell interface that will be the interface for all of our "Cell" type
 * classes that we create. It will help provide some regular functionality in
 * all of our cells.
 */
public interface SingleCell<K> {

  /**
   * Evaluates the value of a cell.
   * @param map A map of the coordinates to cells that we have.
   * @param op The operation being run by the object that is evaluating this cell.
   * @return Returns the correct value for the cell, be it boolean, double, string,
   *         or evaluated formula.
   */
  public K getValue(HashMap<Coord, SingleCell> map, OperationType op);

  /**
   * Evaluates the numeric representation of a cell, which for number cells and product/sum cells
   * is their value, but for non-numeric types is 0.
   * @param map A map of the coordinates to cells that we have.
   * @param op The operation being run by the object that is evaluating this cell.
   * @return Returns the numeric value of the cell.
   */
  public double numberRepresentation(HashMap<Coord, SingleCell> map, OperationType op);

  /**
   * Checks to see if a cell has any cyclic reference. Currently works recursivly and creates
   * clones on branches which is inefficient so hopefully we can change that soon.
   * @param map A map of the coordinates to cells that we have.
   * @param lookup The table of Cells that we've already seen.
   * @return Returns a boolean representing if a cycle is present in this cell.
   */
  public boolean hasCyclicReference(HashMap<Coord, SingleCell> map, HashSet<SingleCell> lookup);

  /**
   * Checks if this cell is less than the other cell.
   * @param map A map of the coordinates to cells that we have.
   * @param op The operation being run by the object that is evaluating this cell.
   * @return Returns a boolean representing if this cell is less than the other cell.
   */
  public boolean compareCells(HashMap<Coord, SingleCell> map,
      OperationType op, SingleCell other);

  /**
   * Checks if this cell is greater than the given data.
   * @param map A map of the coordinates to cells that we have.
   * @param op The operation being run by the object that is evaluating this cell.
   * @param other The value we are being compared to.
   * @return Returns a boolean representing if this cell is greater than the given data.
   */
  public boolean compareToBoolean(HashMap<Coord, SingleCell> map,
      OperationType op, Boolean other);

  /**
   * Checks if this cell is greater than the given data.
   * @param map A map of the coordinates to cells that we have.
   * @param op The operation being run by the object that is evaluating this cell.
   * @param other The value we are being compared to.
   * @return Returns a boolean representing if this cell is greater than the given data.
   */
  public boolean compareToDouble(HashMap<Coord, SingleCell> map,
      OperationType op, Double other);

  /**
   * Checks if this cell is greater than the given data.
   * @param map A map of the coordinates to cells that we have.
   * @param op The operation being run by the object that is evaluating this cell.
   * @param other The value we are being compared to.
   * @return Returns a boolean representing if this cell is greater than the given data.
   */
  public boolean compareToString(HashMap<Coord, SingleCell> map,
      OperationType op, String other);

  /**
   * Returns the input style string that was passed in to create this cell.
   * @return    String representing this cell as an input.
   */
  public String inputForm();

}
