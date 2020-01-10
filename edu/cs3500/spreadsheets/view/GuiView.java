package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.controller.FeatureInterface;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.GetOnlyModel;

import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A GUI based view for the spreadsheet models. It will render a window similar to
 * other spreadsheets applications allowing you to scroll around and view all assigned cells.
 * Will be adding a way to add more rows and columns when we have a controller.
 */
public class GuiView implements IView {

  private JFrame frame;
  private GetOnlyModel sheet;
  private StandalonePanel staticPanel;
  private JPanel scrollPanel;

  /**
   * Public constructor that takes a read-only model to display.
   * @param sheet     read-only version of our spreadsheet.
   */
  public GuiView(GetOnlyModel sheet) {
    this.sheet = sheet;
    this.frame = new JFrame();
    this.setup();
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

  }

  @Override
  public void changeTableView(String buttonLabel) {

  }

  @Override
  public void changeCurrentButtonColor(String buttonLabel) {

  }


  /**
   * A private method for constructing the necessary Panels and Frames that
   * are the actual GUI.
   */
  private void setup() {
    this.staticPanel = new StandalonePanel(sheet);

    this.frame = new JFrame();

    this.scrollPanel = new ScrollPanel(staticPanel);

    frame.setSize(600, 400);

    frame.add(scrollPanel);
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
   * A method for having the View rePaint itself. For non-GUI views this won't do anything as
   * render serves that function for those views, while we need to call rePaint on GUI
   * views to refresh the client-facing output.
   */
  public void repaintView() {
    this.scrollPanel.repaint();
  }

  /**
   * Reset any viewer-mutable segments of the view.
   */
  @Override
  public void resetChanges() {
    return;
  }

  /**
   * Sets the given mouseListener to listen to for mouseEvents in the relevent
   * field of it's view. For GUI-views this is the grid representing the sheet cells,
   * for non-GUI views this listener is discarded.
   * @param listener    The mouseListener.
   */
  public void setMouseListener(MouseListener listener) {
    this.staticPanel.addMouseListener(listener);
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
   * Returns the Cell at the given X and Y coordinates. In non-GUI views, this
   * will return null as there is no string at numerical values X and Y.
   * @param x   The X coordinate.
   * @param y   The Y coordinate.
   * @return    The Coord of the cell at coordinates X and Y.
   */
  public Coord getCellAt(int x, int y) {
    x -= this.scrollPanel.getLocation().getX();
    y -= this.scrollPanel.getLocation().getX();

    int startRow = this.staticPanel.getRow() - 1;
    int startCol = this.staticPanel.getCol() - 1;

    int height = this.staticPanel.getHeight();
    int width = this.staticPanel.getWidth();

    // Clicks are outside of Grid, null signifies non-cell click
    if (x < (width / 17) | y < (height / 26)) {
      return null;
    }
    if (x > width | y > height) {
      return null;
    }

    return new Coord(x / (width / 17) + startCol, y / (height / 26) + startRow);
  }

  /**
   * Renders the View.
   * @throws IOException  if working with a bad appendable.
   */
  public void render() throws IOException {
    frame.setVisible(true);
  }
}
