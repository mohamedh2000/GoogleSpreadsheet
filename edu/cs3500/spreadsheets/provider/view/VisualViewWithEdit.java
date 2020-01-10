package edu.cs3500.spreadsheets.provider.view;

import java.awt.*;
import java.awt.event.MouseListener;

import edu.cs3500.spreadsheets.controller.FeatureInterface;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.RegularWorksheet;
import edu.cs3500.spreadsheets.provider.model.ISpreadsheetViewOnly;
import edu.cs3500.spreadsheets.view.IView;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 * To represent a visual view that allows the user to see a textbox so they can edit the contents of
 * a cell. The view also contains buttons that allow the user to add more columns/rows to the view.
 */
public class VisualViewWithEdit extends AbstractVisualView implements IView {
  private EditBoxAndExpand editUI;
  private SaveOpen saveopen;
  private JPanel container;
  private WorkSheetPageContainer workSheetPanel;

  /**
   * Constructor for visual view edit.
   * @param name name of file
   * @param ss spreadsheet model
   * @param windowWidth window width
   * @param windowHeight window height
   */
  public VisualViewWithEdit(String name, ISpreadsheetViewOnly ss, int windowWidth,
                            int windowHeight) {
    super(name, ss, windowWidth, windowHeight);
    this.editUI = new EditBoxAndExpand();
    this.saveopen = new SaveOpen();
    this.container = new JPanel(new GridBagLayout());
    this.workSheetPanel = new WorkSheetPageContainer(this,ss);
  }

  @Override
  public void render() {
    super.renderSetup();
    workSheetPanel.setLayout(new FlowLayout(10));

    container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
    container.add(saveopen);
    container.add(editUI);
    container.add(table);
    container.add(workSheetPanel);
    jf.add(container, BorderLayout.CENTER);
    jf.setVisible(true);
  }

  /**
   * To change the view of the Table. It will get the Tablemodel from the worksheetPanel, and then
   * it'll retrieve the new Worksheet model as well. From there it'll change the table sheet as well
   * as the table model.
   * @param buttonLabel The Button that was pressed.
   */
  @Override
  public void changeTableView(String buttonLabel) {
    workSheetPanel.updateHolderModel(ss,table.getModel());
    changeCurrentButtonColor(buttonLabel);
    TableModel modelToChange = workSheetPanel.getTableModel(buttonLabel);
    this.ss = workSheetPanel.listOfPages.get(Integer.parseInt(buttonLabel)).getSheet();
    table.changeSheet(this.ss);
    table.getTable().setModel(modelToChange);
    table.validate();
    repaintView();
  }

  @Override
  public void changeCurrentButtonColor(String buttonLabel) {
    workSheetPanel.changeCurrentButtonColor(buttonLabel);
    repaintView();
  }

  /**
   * Adds the features for each panel.
   * @param f The Feature interface.
   */
  @Override
  public void addFeatures(FeatureInterface f) {
    this.saveopen.addFeatures(f);
    this.table.addFeatures(f);
    this.editUI.addFeatures(f);
    this.workSheetPanel.addFeatures(f);
  }

  /**
   * Returns the Cell at the given X and Y coordinates. In non-GUI views, this will return null as
   * there is no string at numerical values X and Y.
   *
   * @param x The X coordinate.
   * @param y The Y coordinate.
   * @return The Coord of the cell at coordinates X and Y.
   */
  @Override
  public Coord getCellAt(int x, int y) {
    JTable tempTable = this.table.getTable();
    Point point = new Point(x, y);

    int row = tempTable.rowAtPoint(point) + 1;
    int col = tempTable.columnAtPoint(point);

    System.out.println(String.format("Row: %d, Col: %d", row,col));

    if (row <= 0 | col <= 0) {
      return null;
    }

    return new Coord(col, row);
  }

  /**
   * Sets the given mouseListener to listen to for mouseEvents in the relevent field of it's view.
   * For GUI-views this is the grid representing the sheet cells, for non-GUI views this listener is
   * discarded.
   *
   * @param listener The mouseListener.
   */
  @Override
  public void setMouseListener(MouseListener listener) {
    this.table.getTable().addMouseListener(listener);
  }

  /**
   * Selects the cell at the Given coordinate in the view. For GUI-cells this will select one of the
   * cells in the grid they display. For non-GUI views, this will be discarded, as highlighted cells
   * won't be interacted with given no GUI.
   *
   * @param coord The coord of the cell to select.
   */
  @Override
  public void selectCell(Coord coord) {
    if (coord == null) {
      this.editUI.setTextbox("");
    }
    else {
      setFormulaDisplay(this.ss.getFormulaAtCoord(coord));
    }
    repaintView();
  }

  /**
   * A method for having the View rePaint itself. For non-GUI views this won't do anything as render
   * serves that function for those views, while we need to call rePaint on GUI views to refresh the
   * client-facing output.
   */
  @Override
  public void repaintView() {
    this.container.repaint();
  }

  /**
   * Reset any viewer-mutable segments of the view.
   */
  @Override
  public void resetChanges() {
    JTable tempTable = this.table.getTable();
    setFormulaDisplay(this.ss.getFormulaAtCoord(new Coord(tempTable.getSelectedColumn(),
            tempTable.getSelectedRow() + 1)));
  }

  /**
   * Sets the user interactable field to the given String,
   * for non-GUI views this should do nothing since nothing
   * is interactable.
   * @param context   String to set the field to.
   */
  public void setUserInputField(String context) {
    this.setFormulaDisplay(context);
  }

  @Override
  public void setFormulaDisplay(String formula) {
    editUI.setTextbox(formula);
  }

  @Override
  public void updateCellValue(String value, int row, int col) {
    this.table.updateCellValue(value, row, col);
  }

  @Override
  public void addWorksheet() {
    this.workSheetPanel.addWorkSheet();
    repaintView();
  }

  @Override
  public void increaseRow() {
    this.table.increaseRow();
    ss.addRow();
    repaintView();
  }

  @Override
  public void increaseCol() {
    this.table.increaseCol();
    ss.addCol();
    repaintView();
  }

  @Override
  public void close() {
    this.jf.setVisible(false);
  }

}

