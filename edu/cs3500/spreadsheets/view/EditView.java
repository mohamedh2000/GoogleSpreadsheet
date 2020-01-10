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
import edu.cs3500.spreadsheets.model.RegularWorksheet;

/**
 * A GUI-based view that is interactable, so it's similar to our View GUIView, but comes
 * with an interactable Text pane for catching user input Strings, and buttons for users
 * to confirm the changes, or undo the changes purposed in the TextPanel.
 */
public class EditView implements IView {

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


  /**
   * A public constructor that will takes a Read-Only version of our model that is used
   * to get Cell Contents for printing.
   * @param sheet   The Read-Only model.
   */
  public EditView(GetOnlyModel sheet) {
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
    this.input.setText(context);
  }

  @Override
  public void increaseRow() {
    throw new UnsupportedOperationException("increaseRow not supported");
  }

  @Override
  public void increaseCol() {
    throw new UnsupportedOperationException("IncreaseCol not supported");
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

    frame.setSize(800, 800);

    third = new JPanel();
    third.setLayout(new BoxLayout(third, BoxLayout.PAGE_AXIS));
    third.add(Box.createRigidArea(new Dimension(0,5)));
    third.add(temp);
    third.add(this.scrollPanel);

    frame.getContentPane().add(third, BorderLayout.CENTER);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
   * Reset any viewer-mutable segments of the view.
   */
  @Override
  public void resetChanges() {
    this.input.setText(this.sheet.getInputOfCell(new Coord(this.selectedCol, this.selectedRow)));
  }

  /**
   * Add features callbacks to the appropriate Swing elements
   * such as listeners. Allows for Classes like our controllers
   * to implement callbacks in the Views without knowing Swing.
   *
   * @param features    The features to be mapped to listeners.
   */
  public void addFeatures(FeatureInterface features) {
    confirmButton.addActionListener(evt ->
            features.clickCheckBox(this.input.getText()));
    undoButton.addActionListener(evt ->
            features.clickDeleteBox());
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
   * A method for having the View rePaint itself. For non-GUI views this won't do anything as
   * render serves that function for those views, while we need to call rePaint on GUI
   * views to refresh the client-facing output.
   */
  public void repaintView() {
    this.scrollPanel.repaint();
  }

  /**
   * Selects the cell at the Given coordinate in the view. For GUI-cells this will select
   * one of the cells in the grid they display. For non-GUI views, this will be discarded,
   * as highlighted cells won't be interacted with given no GUI.
   * @param coord   The coord of the cell to select.
   */
  public void selectCell(Coord coord) {
    if (coord == null) {
      this.selectedRow = -1;
      this.selectedCol = -1;
      this.input.setText("");
    }
    else {
      this.selectedRow = coord.row;
      this.selectedCol = coord.col;
      this.input.setText(this.sheet.getInputOfCell(coord));
    }
    this.staticPanel.selectCell(selectedRow, selectedCol);
  }

  /**
   * Renders the View.
   * @throws IOException  if working with a bad appendable.
   */
  @Override
  public void render() {
    frame.setVisible(true);
  }


}
