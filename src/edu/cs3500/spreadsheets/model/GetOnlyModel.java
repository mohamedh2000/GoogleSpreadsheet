package edu.cs3500.spreadsheets.model;

/**
 * A Get Only Worksheet interface that will  allow structures such as vies to treat
 * Alias's to the model as Read-Only with no functionality to change contents, only
 * retrieve cell outputs.
 */
public interface GetOnlyModel {

  /**
   * Evaluates the cell at the given coordinate and returns the object that it stores.
   * @param coord The coordinate of the cell we want to evaluate.
   * @return The value of the cell being evaluated.
   */
  public Object evaluateCell(Coord coord);

  public String getInputOfCell(Coord coord);

  /**
   * This method will go through every assigned cell in the model and append the
   * cell as it would have been input to the model, to the appendable.
   * @return  The appendable.
   */
  public Appendable getAllInputs();

  /**
   * Returns the maximum row of all assigned cells.
   * @return maximum row of all assigned cells.
   */
  public int getMaxRow();

  /**
   * Returns the maximum column of all assigned cells.
   * @return maximum column of all assigned cells.
   */
  public int getMaxCol();

}
