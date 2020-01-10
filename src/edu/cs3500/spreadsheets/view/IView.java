package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Coord;
import java.awt.event.MouseListener;
import java.io.IOException;

import edu.cs3500.spreadsheets.controller.FeatureInterface;
import edu.cs3500.spreadsheets.provider.model.ISpreadsheetViewOnly;

/**
 * An interface for the view of our spreadsheet application.
 */
public interface IView {

  /**
   * Add features callbacks to the appropriate Swing elements
   * such as listeners. Allows for Classes like our controllers
   * to implement callbacks in the Views without knowing Swing.
   *
   * @param features    The features to be mapped to listeners.
   */
  void addFeatures(FeatureInterface features);

  /**
   * Returns the Cell at the given X and Y coordinates. In non-GUI views, this
   * will return null as there is no string at numerical values X and Y.
   * @param x   The X coordinate.
   * @param y   The Y coordinate.
   * @return    The Coord of the cell at coordinates X and Y.
   */
  Coord getCellAt(int x, int y);

  /**
   * Sets the given mouseListener to listen to for mouseEvents in the relevent
   * field of it's view. For GUI-views this is the grid representing the sheet cells,
   * for non-GUI views this listener is discarded.
   * @param listener    The mouseListener.
   */
  void setMouseListener(MouseListener listener);

  /**
   * Selects the cell at the Given coordinate in the view. For GUI-cells this will select
   * one of the cells in the grid they display. For non-GUI views, this will be discarded,
   * as highlighted cells won't be interacted with given no GUI.
   * @param coord   The coord of the cell to select.
   */
  void selectCell(Coord coord);

  /**
   * A method for having the View rePaint itself. For non-GUI views this won't do anything as
   * render serves that function for those views, while we need to call rePaint on GUI
   * views to refresh the client-facing output.
   */
  void repaintView();

  /**
   * Reset any viewer-mutable segments of the view.
   */
  void resetChanges();


  /**
   * Renders the View.
   * @throws IOException  if working with a bad appendable.
   */
  void render() throws IOException;

  /**
   * Sets the user interactable field to the given String,
   * for non-GUI views this should do nothing since nothing
   * is interactable.
   * @param context   String to set the field to.
   */
  void setUserInputField(String context);

  /**
   * Increases the rows that you can see in the table. While updating the
   * row numbers.
   */
  void increaseRow();

  /**
   * Increases the columns that you can see in the table. While updating the
   * Column headers.
   */
  void increaseCol();

  /**
   * Updates the Cell Value at the coordinates given below with the text given.
   * @param text The String Value you'll update the cell to.
   * @param col the column number of the coordinate.
   * @param row the row number of the coordinate.
   */
  void updateCellValue(String text, int col, int row);

  /**
   * Add a new worksheet that can be switched to for the view.
   */
  void addWorksheet();

  /**
   * To change the current table view to another sheet.
   * @param buttonLabel The Button label that was pressed. It's used so that I can trace it
   *                    back to a holder, and retrieve information from the holder.
   */
  void changeTableView(String buttonLabel);

  void changeCurrentButtonColor(String buttonLabel);

}
