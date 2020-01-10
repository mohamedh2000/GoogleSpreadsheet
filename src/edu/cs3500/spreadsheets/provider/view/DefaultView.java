package edu.cs3500.spreadsheets.provider.view;

import edu.cs3500.spreadsheets.controller.FeatureInterface;
import edu.cs3500.spreadsheets.provider.model.ISpreadsheetViewOnly;

/**
 * To represent a default view. This class is primarily used for textual views to reduce any
 * duplicated code (especially in throwing the unsupported operation exceptions).
 */
public abstract class DefaultView implements SpreadsheetView {
  ISpreadsheetViewOnly ss;

  /**
   * Constructor for a default view.
   *
   * @param ss spreadsheet model
   */
  public DefaultView(ISpreadsheetViewOnly ss) {
    this.ss = ss;
  }

  @Override
  public void addFeatures(FeatureInterface f) {
    return;
  }

  @Override
  public void setFormulaDisplay(String formula) {
    throw new UnsupportedOperationException("setformuladisplay not supported");
  }

  @Override
  public void updateCellValue(String value, int row, int col) {
    throw new UnsupportedOperationException("update cell value not supported");
  }

  @Override
  public void increaseRow() {
    throw new UnsupportedOperationException("increaseRow not supported");
  }

  @Override
  public void increaseCol() {
    throw new UnsupportedOperationException("increaseCol not supported");
  }

  @Override
  public void close() {
    throw new UnsupportedOperationException("Close not supported");
  }
}




