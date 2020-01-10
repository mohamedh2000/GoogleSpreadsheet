package edu.cs3500.spreadsheets.controller;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.RegularWorksheet;
import edu.cs3500.spreadsheets.model.SingleCell;
import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.sexp.IdentifyInput;
import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.view.IView;
import edu.cs3500.spreadsheets.view.SaveFileClass;

import java.io.IOException;

/**
 * A Basic controller class that implements our Controller Interface. That interface
 * also extend the FeatureInterface, as all controllers should implement features to be
 * interactable as well.
 */
public class BasicController implements ControllerInterface {
  private Worksheet model;
  private IView view;
  private Coord selectedCell;
  private Boolean isCellSelected;
  private CustomMouseListener mouseListener;

  /**
   * Constructor for out controller, takes a model to start with. Set's it's selectedCell to null
   * and isCellSelected to false as nothing in the view should be selected. Also establishes a
   * mouse listener with itself as the feature interface to send mouse events to.
   * @param model   Model to control.
   */
  public BasicController(Worksheet model) {
    this.model = model;
    this.selectedCell = null;
    this.isCellSelected = false;
    this.mouseListener = new CustomMouseListener(this);
  }

  /**
   * Set's the view of the controller to be the one passed in. This controller will
   * then pass itself as a feature interface to the given view so that it can interact
   * with the view listeners.
   * @param view    The new view to be assigned to the controller.
   */
  public void setView(IView view) {
    this.view = view;
    this.view.addFeatures(this);
    this.view.setMouseListener(this.mouseListener);
  }

  /**
   * Private function for taking user input Strings (eg. from the text panel), and turning
   * them into Single cells by having them be parsed by the Sexp Visitor we wrote in Assignment 5.
   * @param content   String user input.
   * @return          SingleCell that reflects the input.
   */
  private SingleCell stringToSingleCell(String content) {

    IdentifyInput inputHandle = new IdentifyInput();
    Sexp result = Parser.parse(content);
    SingleCell cell = result.accept(inputHandle);

    return cell;
  }

  /**
   * A feature that will allow a listener to call this method when the check button in
   * our view has been pressed. This will then handle updating the selected Coordinate
   * in the model with the contents in the text panel.
   * @param content   Strings contents in the text panel.
   */
  public void clickCheckBox(String content) {
    content = content.replaceAll("=", "");
    if (this.isCellSelected) {
      this.model.assignCoord(this.selectedCell, stringToSingleCell(content));
      this.view.repaintView();
    }
  }

  /**
   * Private method for updating a currentlySelectedCell to a new location. Sets isSelected to true
   * and updates the coord of the selected cell. Also informs the view that we have selected
   * a cell.
   * @param coord   New selected cell Coord.
   */
  private void updateSelectedCell(Coord coord) {
    this.isCellSelected = true;
    this.selectedCell = coord;
    this.view.selectCell(coord);
  }

  /**
   * Private method for unselecting a cell. This can occur when a selected cell is pressed, or
   * an out of grid mouse click is registered. Informs the view that the new selected cell
   * is null, meaning that no cell should be highlighted.
   */
  private void unselectCell() {
    this.isCellSelected = false;
    this.selectedCell = null;
    this.view.selectCell(null);
  }

  /**
   * Private method for handling which action to take once we know which cell
   * in the view has been selected. Clicking a non-selected cell selects it. Clicking a
   * selected cell unselects it. If coord is null, this is a non-grid click so all
   * cells are unhighlighted.
   * @param coord   The selected cell Coord.
   */
  private void selectCell(Coord coord) {
    if (coord == null) {
      unselectCell();
    }
    else if (!this.isCellSelected) {
      updateSelectedCell(coord);
    }
    else if (this.selectedCell.equals(coord)) {
      unselectCell();
    }
    else {
      updateSelectedCell(coord);
    }
  }

  /**
   * A feature that will allow a mouse listener to call this method when it detects
   * the mouse being pressed on the relevant panel of the view. This will select the
   * cell that was clicked on in the view if the click was in a valid location. Clicking
   * on a selected cell should un-select the cell.
   * @param mouseX    X coordinate of mouse click.
   * @param mouseY    Y coordinate of mouse click.
   */
  public void clickAt(int mouseX, int mouseY) {
    Coord clicked = this.view.getCellAt(mouseX, mouseY);
    if (clicked != null) {
      System.out.println(clicked);
    }
    else {
      System.out.println("Null");
    }
    if (clicked == null) {
      this.selectCell(null);
      //discard this out of bounds click
    }
    else {
      this.selectCell(new Coord(clicked.col, clicked.row));
    }
  }

  @Override
  public void submit(String text) {
    if (this.selectedCell != null) {
      SingleCell newCell = stringToSingleCell(text);
      this.model.assignCoord(this.selectedCell, newCell);
      view.updateCellValue(this.model.evaluateCell(this.selectedCell).toString(),
          selectedCell.row, selectedCell.col);
    }
  }

  @Override
  public void cancel() {
    this.clickDeleteBox();
  }

  @Override
  public void addRow() {
    view.increaseRow();
  }

  @Override
  public void addCol() {
    view.increaseCol();
  }

  @Override
  public void save(String text) {
    return;
  }

  @Override
  public void open(String text) {
    return;
  }

  @Override
  public void displayFormula(int row, int col) {
    if (row > 0 & col > 0) {
      String content = this.model.getInputOfCell(new Coord(col, row));
      this.view.setUserInputField(content);
    }
  }

  /**
   * To add a new Worksheet button.
   */
  @Override
  public void addNewWorkSheet() {
    view.addWorksheet();
    view.repaintView();
  }

  @Override
  public void move(String direction) {
  }

  /**
   * A feature that will allow a listener to call this method when the undo button in
   * our view has been pressed. This will reset the data in the text panel to be
   * the contents of the currently selected cell, canceling any pending changes.
   */
  public void clickDeleteBox() {
    this.view.resetChanges();
    this.isCellSelected = false;
    this.selectedCell = null;
  }

  /**
   * Renders the given view. Will throw an IOException if there are issues with
   * objects such as appendable in the process. Nothing will happen if the view
   * is null, meaning one has not been assigned to the controller yet.
   * @throws IOException    Throws IOException if there is an issue with appendable.
   */
  public void renderView() throws IOException {
    if (this.view == null) {
      return;
    }
    else {
      this.view.render();
    }
  }
}
