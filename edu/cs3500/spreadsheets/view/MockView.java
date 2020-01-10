package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Coord;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import edu.cs3500.spreadsheets.controller.FeatureInterface;
import edu.cs3500.spreadsheets.model.GetOnlyModel;

/**
 * A mock View that has an appendable to hold it actions.
 */
public class MockView implements IView {

  private JButton confirmButton;
  private JButton undoButton;
  private JTextField input;
  private GetOnlyModel sheet;
  private JPanel temp;
  private JPanel third;
  private JFrame frame;
  private int selectedRow;
  private int selectedCol;
  private StandalonePanel staticPanel;
  private ScrollPanel scrollPanel;
  private Appendable ap;
  private FeatureInterface features;
  private MouseListener mouseListener;

  /**
   * A public constructor that will takes a Read-Only version of our model that is used
   * to get Cell Contents for printing.
   * @param sheet   The Read-Only model.
   * @param ap      The appendable.
   */
  public MockView(GetOnlyModel sheet, Appendable ap) {
    this.ap = ap;
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
   * A private method for constructing the necessary Panels and Frames that
   * are the actual GUI.
   */
  private void setup() {
    this.staticPanel = new StandalonePanel(this.sheet);

    this.scrollPanel = new ScrollPanel(this.staticPanel);

    undoButton = new JButton("Undo");

    confirmButton = new JButton("Confirm");

    input = new JTextField();
    input.setMinimumSize(this.confirmButton.getMinimumSize());
    input.setPreferredSize(new Dimension(this.confirmButton.getPreferredSize().width * 2,
        this.confirmButton.getPreferredSize().height));
    input.setText("Click a Cell");
    input.setBorder(LineBorder.createBlackLineBorder());

    temp = new JPanel();
    temp.add(confirmButton);
    temp.add(undoButton);
    temp.add(input);
    temp.setLayout(new FlowLayout());

    frame.setSize(600, 400);

    third = new JPanel();
    third.setLayout(new BoxLayout(third, BoxLayout.PAGE_AXIS));
    third.add(Box.createRigidArea(new Dimension(0,5)));
    third.add(temp);
    third.add(this.scrollPanel);

    frame.getContentPane().add(third, BorderLayout.CENTER);
  }

  /**
   * Returns the Cell at the given X and Y coordinates. In non-GUI views, this
   * will return null as there is no string at numerical values X and Y.
   * @param x   The X coordinate.
   * @param y   The Y coordinate.
   * @return    The Coord of the cell at coordinates X and Y.
   */
  public Coord getCellAt(int x, int y) {
    try {
      this.ap.append(String.format("Get cell %d, %d", x, y));
    }
    catch (Exception e) {
      return null;
    }
    return new Coord(7, 13);
  }


  /**
   * Reset any viewer-mutable segments of the view.
   */
  @Override
  public void resetChanges() {
    try {
      this.ap.append(String.format("Reset view"));
    }
    catch (Exception e) {
      return;
    }
  }

  /**
   * Add features callbacks to the appropriate Swing elements
   * such as listeners. Allows for Classes like our controllers
   * to implement callbacks in the Views without knowing Swing.
   *
   * @param features    The features to be mapped to listeners.
   */
  public void addFeatures(FeatureInterface features) {
    try {
      this.ap.append(String.format("Hook up controller"));
      this.features = features;
    }
    catch (Exception e) {
      return;
    }
  }

  /**
   * Sets the given mouseListener to listen to for mouseEvents in the relevent
   * field of it's view. For GUI-views this is the grid representing the sheet cells,
   * for non-GUI views this listener is discarded.
   * @param listener    The mouseListener.
   */
  public void setMouseListener(MouseListener listener) {
    this.mouseListener = listener;
    try {
      this.ap.append(String.format("Hook up mouse listener."));
    }
    catch (Exception e) {
      return;
    }
  }

  /**
   * A method for having the View rePaint itself. For non-GUI views this won't do anything as
   * render serves that function for those views, while we need to call rePaint on GUI
   * views to refresh the client-facing output.
   */
  public void repaintView() {

    try {
      this.ap.append(String.format("Repaint View."));
    }
    catch (Exception e) {
      return;
    }
  }

  /**
   * Selects the cell at the Given coordinate in the view. For GUI-cells this will select
   * one of the cells in the grid they display. For non-GUI views, this will be discarded,
   * as highlighted cells won't be interacted with given no GUI.
   * @param coord   The coord of the cell to select.
   */
  public void selectCell(Coord coord) {
    try {
      this.ap.append(String.format("Select Cell %d, %d", coord.col, coord.row));
    }
    catch (Exception e) {
      return;
    }
  }

  /**
   * Renders the View.
   * @throws IOException  if working with a bad appendable.
   */
  @Override
  public void render() {
    try {
      this.ap.append(String.format("Render View"));
    }
    catch (Exception e) {
      return;
    }
  }

  public void simulateCheck() {
    this.features.clickCheckBox("hey mark");
  }

  public void simulateUndo() {
    this.features.clickDeleteBox();
  }

  public MouseListener getMouseListener() {
    return this.mouseListener;
  }


}
