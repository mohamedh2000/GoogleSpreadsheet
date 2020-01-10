package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Coord;
import java.awt.event.MouseListener;
import java.io.IOException;

import edu.cs3500.spreadsheets.controller.FeatureInterface;
import edu.cs3500.spreadsheets.model.Worksheet;

/**
 * To create a textual view of the model.
 */
public class TextualViewModel implements IView {
  private Worksheet worksheet;
  private Appendable ap;

  /**
   * Takes in a worksheet and outputs a textual view of the worksheet data to the appendable.
   * @param worksheet The worksheet data you want sent to the appendable.
   * @param ap Where you want the data sent to.
   */
  public TextualViewModel(Worksheet worksheet, Appendable ap) {
    this.worksheet = worksheet;
    this.ap = ap;
  }

  /**
   * Sets the user interactable field to the given String,
   * for non-GUI views this should do nothing since nothing
   * is interactable.
   * @param context   String to set the field to.
   */
  public void setUserInputField(String context) {
    return;
  }

  @Override
  public void increaseRow() {
    return;
  }

  @Override
  public void increaseCol() {
    return;
  }

  @Override
  public void updateCellValue(String text, int col, int row) {
    return;
  }

  @Override
  public void addWorksheet() {

  }

  @Override
  public void changeTableView(String buttonLabel) {

  }

  @Override
  public void changeCurrentButtonColor(String buttonLabel) {

  }

  /**
   * Add features callbacks to the appropriate Swing elements
   * such as listeners. Allows for Classes like our controllers
   * to implement callbacks in the Views without knowing Swing.
   *
   * @param features    The features to be mapped to listeners.
   */
  public void addFeatures(FeatureInterface features) {
    return;
  }

  /**
   * Reset any viewer-mutable segments of the view.
   */
  @Override
  public void resetChanges() {
    return;
  }

  /**
   * A method for having the View rePaint itself. For non-GUI views this won't do anything as
   * render serves that function for those views, while we need to call rePaint on GUI
   * views to refresh the client-facing output.
   */
  public void repaintView() {
    return;
  }

  /**
   * Sets the given mouseListener to listen to for mouseEvents in the relevent
   * field of it's view. For GUI-views this is the grid representing the sheet cells,
   * for non-GUI views this listener is discarded.
   * @param listener    The mouseListener.
   */
  public void setMouseListener(MouseListener listener) {
    return;
  }

  /**
   * Selects the cell at the Given coordinate in the view. For GUI-cells this will select
   * one of the cells in the grid they display. For non-GUI views, this will be discarded,
   * as highlighted cells won't be interacted with given no GUI.
   * @param coord   The coord of the cell to select.
   */
  public void selectCell(Coord coord) {
    return;
  }

  public Coord getCellAt(int x, int y) {
    return new Coord(0, 0);
  }

  /**
   * Renders the View.
   * @throws IOException  if working with a bad appendable.
   */
  @Override
  public void render() {
    Appendable reader = this.worksheet.getAllInputs();

    try {
      this.ap.append(reader.toString());
    }
    catch (IOException ex) {
      System.out.println("Issue with appendable");
    }
  }

}
