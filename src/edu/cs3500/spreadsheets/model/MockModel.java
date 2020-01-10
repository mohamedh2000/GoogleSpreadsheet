package edu.cs3500.spreadsheets.model;

import java.util.HashMap;

/**
 * Represents a mock version of our regular worksheet that records
 * it's actions to appendable.
 */
public class MockModel implements Worksheet, GetOnlyModel {

  private HashMap<Coord, SingleCell> worksheetData;
  private int maxRow;
  private int maxCol;
  private Appendable ap;

  /**
   * Creates a blank worksheet or a new Worksheet.
   * @param ap    The appendable that tracks actions.
   */
  public MockModel(Appendable ap) {
    this.worksheetData = new HashMap<>();
    this.maxRow = 0;
    this.maxCol = 0;
    this.ap = ap;
  }

  /**
   * Gets the String representation of the Cell at the coord.
   * @param coord   Coord of cell.
   * @return        String rep of cell at coord.
   */
  public String getInputOfCell(Coord coord) {
    try {
      this.ap.append(String.format("Input form %d, %d", coord.col, coord.row));
    }
    catch (Exception e) {
      return null;
    }
    return null;
  }

  /**
   * Assigns SingleCells to coordinates in the Worksheet.
   * @param coord Where the cell data will be placed in the map.
   * @param cellData The data that will be placed in the hashmap.
   */
  public void assignCoord(Coord coord, SingleCell cellData) {
    try {
      this.ap.append(String.format("Assign %d, %d", coord.col, coord.row));
    }
    catch (Exception e) {
      return;
    }
  }

  /**
   * Removes the cell at the given coord.
   * @param coord   Coord to delete.
   */
  @Override
  public void removeCell(Coord coord) {
    try {
      this.ap.append(String.format("Remove %d, %d", coord.col, coord.row));
    }
    catch (Exception e) {
      return;
    }
  }

  /**
   * Changes the cell at the given coord to the new SingleCell.
   * @param coord   Coord to change.
   * @param newData New SingleCell to update to.
   */
  @Override
  public void changeCell(Coord coord, SingleCell newData) {
    try {
      this.ap.append(String.format("Change %d, %d", coord.col, coord.row));
    }
    catch (Exception e) {
      return;
    }
  }

  /**
   * Evaluates the cell to return the cell value.
   * @param coord The coordinate of the cell we want to evaluate.
   * @return the data of all the worksheetData needed.
   */
  public Object evaluateCell(Coord coord) {
    try {
      this.ap.append(String.format("Evaluate %d, %d", coord.col, coord.row));
    }
    catch (Exception e) {
      return null;
    }
    return null;
  }

  /**
   * This method will go through every assigned cell in the model and append the
   * cell as it would have been input to the model, to the appendable.
   * @return  The appendable.
   */
  @Override
  public Appendable getAllInputs() {
    try {
      this.ap.append(String.format("Get all inputs"));
    }
    catch (Exception e) {
      return null;
    }
    return null;
  }

  /**
   * Returns the hashmap of coordinates to SingelCells that the model stores.
   * @return  The hashmap.
   */
  @Override
  public HashMap<Coord, SingleCell> getData() {
    try {
      this.ap.append(String.format("Get data"));
    }
    catch (Exception e) {
      return null;
    }
    return null;
  }



  /**
   * Returns the maximum row of all assigned cells.
   * @return maximum row of all assigned cells.
   */
  public int getMaxRow() {
    return this.maxRow;
  }

  /**
   * Returns the maximum column of all assigned cells.
   * @return maximum column of all assigned cells.
   */
  public int getMaxCol() {
    return this.maxCol;
  }

}
