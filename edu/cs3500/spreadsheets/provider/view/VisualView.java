package edu.cs3500.spreadsheets.provider.view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import edu.cs3500.spreadsheets.provider.model.ISpreadsheetViewOnly;

/**
 * To represent a visual view. A user can use this to see the actual GUI/spreadsheet with all its
 * cells. It uses JTable to display the cells and we made it so that if we scroll horizontally, the
 * row column header also scrolls with the table (meaning that it is fixed). Current implementation
 * does not show formulas (shows only values).
 */
public class VisualView extends AbstractVisualView {
  /**
   * Constructor for the visual view.
   *
   * @param filename file name of the spreadsheet to be created
   * @param ss       spreadsheet model
   * @param ww       width of gui window
   * @param wh       height of gui window
   */
  public VisualView(String filename, ISpreadsheetViewOnly ss, int ww, int wh) {
    super(filename, ss, ww, wh);
  }

  @Override
  public void render() {
    super.renderSetup();
    JPanel container = new JPanel();
    container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
    container.add(table);
    jf.add(container);
    jf.setVisible(true);
  }

}
