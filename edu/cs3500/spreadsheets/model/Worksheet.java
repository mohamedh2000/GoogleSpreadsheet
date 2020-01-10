package edu.cs3500.spreadsheets.model;

import java.util.HashMap;

/**
 * A Worksheet interface that will help us regulate the functionality of worksheets,
 * for right now it is only implemented by the regular worksheet class.
 */
public interface Worksheet {

  /**
   * Removes the cell at the given coord.
   * @param coord   Coord to delete.
   */
  public void removeCell(Coord coord);

  /**
   * Changes the cell at the given coord to the new SingleCell.
   * @param coord   Coord to change.
   * @param newData New SingleCell to update to.
   */
  public void changeCell(Coord coord, SingleCell newData);

  /**
   * Evaluates the cell at the given coordinate and returns the object that it stores.
   * @param coord The coordinate of the cell we want to evaluate.
   * @return The value of the cell being evaluated.
   */
  public Object evaluateCell(Coord coord);

  /**
   * Gets the String representation of the Cell at the coord.
   * @param coord   Coord of cell.
   * @return        String rep of cell at coord.
   */
  public String getInputOfCell(Coord coord);

  /**
   * Assigns the coord to the value of the SingleCell.
   * @param coord   The coord.
   * @param cellData  New SingleCell data to assign.
   */
  public void assignCoord(Coord coord, SingleCell cellData);

  /**
   * This method will go through every assigned cell in the model and append the
   * cell as it would have been input to the model, to the appendable.
   * @return  The appendable.
   */
  public Appendable getAllInputs();

  /**
   * Returns the hashmap of coordinates to SingelCells that the model stores.
   * @return  The hashmap.
   */
  public HashMap<Coord, SingleCell> getData();


}
