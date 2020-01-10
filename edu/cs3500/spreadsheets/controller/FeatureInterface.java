package edu.cs3500.spreadsheets.controller;

/**
 * Feature interface that is created to act as a means for being called
 * on by listeners during events, so that the FeatureInterface can be
 * implemented by the listeners without the FeatureInterface knowing anything about
 * the listeners themselves.
 */
public interface FeatureInterface {

  /**
   * A feature that will allow a listener to call this method when the check button in
   * our view has been pressed. This will then handle updating the selected Coordinate
   * in the model with the contents in the text panel.
   * @param content   Strings contents in the text panel.
   */
  void clickCheckBox(String content);

  /**
   * A feature that will allow a listener to call this method when the undo button in
   * our view has been pressed. This will reset the data in the text panel to be
   * the contents of the currently selected cell, canceling any pending changes.
   */
  void clickDeleteBox();

  /**
   * A feature that will allow a mouse listener to call this method when it detects
   * the mouse being pressed on the relevant panel of the view. This will select the
   * cell that was clicked on in the view if the click was in a valid location. Clicking
   * on a selected cell should un-select the cell.
   * @param mouseX    X coordinate of mouse click.
   * @param mouseY    Y coordinate of mouse click.
   */
  void clickAt(int mouseX, int mouseY);

  void submit(String text);

  void cancel();

  void addRow();

  void addCol();

  void save(String text);

  void open(String text);

  void displayFormula(int row, int col);

  void addNewWorkSheet();

  void move(String direction);
}
