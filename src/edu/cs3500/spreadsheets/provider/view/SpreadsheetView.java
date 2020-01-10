package edu.cs3500.spreadsheets.provider.view;

import edu.cs3500.spreadsheets.controller.FeatureInterface;

/**
 * This class represents the spreadsheet view interface. Currently, there are 2 types of views: 1)
 * TextualView 2) VisualView These classes are used to display the cell values/spreadsheet cells to
 * the user.
 */
public interface SpreadsheetView {

  /**
   * Renders a spreadsheet model into either a GUI or textual file.
   */
  void render();

  /**
   * Adds a controller to the view so the view can communicate to the model.
   * @param f controller features
   */
  void addFeatures(FeatureInterface f);

  /**
   * Sets the given formula in the view's textbox.
   * @param formula given formula
   */
  void setFormulaDisplay(String formula);

  /**
   * Updates the view of the cell at the given coordinates.
   * @param value new formula
   * @param row cell row
   * @param col cell column
   */
  void updateCellValue(String value, int row, int col);

  /**
   * Increases the number of visible rows.
   */
  void increaseRow();

  /**
   * Increases number of visible columns.
   */
  void increaseCol();

  /**
   * Closes the view window.
   */
  void close();

}
