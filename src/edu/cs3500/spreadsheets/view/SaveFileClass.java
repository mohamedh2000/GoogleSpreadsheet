package edu.cs3500.spreadsheets.view;

import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import edu.cs3500.spreadsheets.controller.FeatureInterface;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.RegularWorksheet;
import edu.cs3500.spreadsheets.model.SingleCell;

/**
 * Saves the data and outputs it to the file.
 */
public class SaveFileClass implements IView {
  private RegularWorksheet worksheet;
  private PrintWriter ap;
  private String fileNameToSave;

  /**
   * Takes in the worksheet data, the appendable out,
   * and the name of the file you'll send it out to.
   * @param worksheet The worksheet you want to get data from and place in the file.
   * @param ap The appendable, which is a printWriter.
   * @param fileNameToSave The name of the file you want the info saved to.
   */
  public SaveFileClass(RegularWorksheet worksheet, PrintWriter ap, String fileNameToSave) {
    if (worksheet == null || ap == null) {
      throw new IllegalArgumentException("Invalid Worksheet or Appendable");
    }
    this.worksheet = worksheet;
    this.ap = ap;
    if (fileNameToSave == null) {
      throw new IllegalArgumentException("Invalid Save File!");
    }
    this.fileNameToSave = fileNameToSave;
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
    throw new UnsupportedOperationException("increaseRow not supported");
  }

  @Override
  public void increaseCol() {
    throw new UnsupportedOperationException("increaseCol not supported");
  }

  @Override
  public void updateCellValue(String text, int col, int row) {
    throw new UnsupportedOperationException("update cell value not supported");
  }

  @Override
  public void addWorksheet() {
    throw new UnsupportedOperationException("AddWorkSheet not supported");
  }

  @Override
  public void changeTableView(String buttonLabel) {
    throw new UnsupportedOperationException("ChangeTableView not supported");
  }

  @Override
  public void changeCurrentButtonColor(String buttonLabel) {
    throw new UnsupportedOperationException("ChangeCurrentButtonColor not supported");
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

  /**
   * A method for having the View rePaint itself. For non-GUI views this won't do anything as
   * render serves that function for those views, while we need to call rePaint on GUI
   * views to refresh the client-facing output.
   */
  public void repaintView() {
    return;
  }

  /**
   * Returns the Cell at the given X and Y coordinates. In non-GUI views, this
   * will return null as there is no string at numerical values X and Y.
   * @param x   The X coordinate.
   * @param y   The Y coordinate.
   * @return    The Coord of the cell at coordinates X and Y.
   */
  public Coord getCellAt(int x, int y) {
    return null;
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
   * Renders the View.
   * @throws IOException  if working with a bad appendable.
   */
  @Override
  public void render() throws IOException {
    /**
     * Check if we have to give it out to that file.
     */

    try {
      FileWriter writer =
              new FileWriter(fileNameToSave);

      ap = new PrintWriter(writer);

      HashMap<Coord, SingleCell> map = worksheet.getData();

      for (Map.Entry<Coord, SingleCell> entry : map.entrySet()) {
        Coord key = entry.getKey();
        String value = entry.getValue().inputForm();
        ap.write(key + " = " + value);
        ap.write("\n");
      }
      ap.close();
    }
    catch (FileNotFoundException f) {
      throw new FileNotFoundException("File not found!");
    }

  }

}
