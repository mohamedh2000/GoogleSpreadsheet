package edu.cs3500.spreadsheets.view;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Shape;
import java.awt.Rectangle;
import java.awt.Graphics;
import edu.cs3500.spreadsheets.model.GetOnlyModel;

import edu.cs3500.spreadsheets.model.Coord;
import javax.swing.JPanel;

/**
 * A StandAlonePanel class that will display a static area of a Read-Only spreadsheet. This will
 * always display a 25x16 portion of the spreadsheet, and has methods for moving the row and col
 * that represent the top-leftmost cell it will draw, as everything else is centered around that.
 */
public class StandalonePanel extends JPanel {

  private GetOnlyModel sheet;
  private int rows = 25;
  private int cols = 16;

  private int col;
  private int row;

  private int selectedCol;
  private int selectedRow;

  private int height;
  private int width;
  private int heightInc;
  private int widthInc;

  /**
   * Public constructor that takes in a read-only model to display.
   * @param sheet   A read-only model to display.
   */
  public StandalonePanel(GetOnlyModel sheet) {
    super();
    this.setMinimumSize(new Dimension(180, 120));
    this.setPreferredSize(new Dimension(600,400));

    this.col = 1;
    this.row = 1;

    this.sheet = sheet;

  }

  /**
   * Returns the maximum row of all assigned cells.
   * @return maximum row of all assigned cells.
   */
  int getMaxRow() {
    return this.sheet.getMaxRow();
  }

  /**
   * Returns the maximum column of all assigned cells.
   * @return maximum column of all assigned cells.
   */
  int getMaxCol() {
    return this.sheet.getMaxCol();
  }

  /**
   * Sets the row of the top-leftmost cell that this panel will render from the spreadsheet.
   * @param newRow    The new row that we will begin rendering at.
   */
  void setStartRow(int newRow) {
    this.row = newRow + 1;
  }

  /**
   * Sets the col of the top-leftmost cell that this panel will render from the spreadsheet.
   * @param newCol    The new col that we will begin rendering at.
   */
  void setStartCol(int newCol) {
    this.col = newCol + 1;
  }

  /**
   * Returns the current column of the top leftmost cell.
   * @return  Col of corner cell.
   */
  public int getCol() {
    return this.col;
  }

  /**
   * Returns the current row of the top leftmost cell.
   * @return  Row of corner cell.
   */
  public int getRow() {
    return this.row;
  }

  /**
   * Selects the cell at (col, row) and updates the local variables tracking that. This
   * cell will be highlighted when painted, so we call Repaint to update it as soon
   * as it changes.
   * @param row   New selected row.
   * @param col   New Selected col.
   */
  public void selectCell(int row, int col) {
    this.selectedCol = col;
    this.selectedRow = row;
    repaint();
  }

  /**
   * Private helper method that will paint the black lines that break our panels,
   * area into a grid-like layout.
   * @param g   Graphics object that will help render.
   */
  private void paintLines(Graphics2D g) {
    g.setColor(Color.BLACK);

    for (int row = 1; row <= rows; row++) {
      g.drawLine(0,  heightInc * row, width, heightInc * row);
    }

    for (int col = 1; col <= cols; col++) {
      g.drawLine(widthInc * col,  0, widthInc * col, height);
    }

  }

  /**
   * Private helper method that will paint the gray zones that represent the areas where the
   * column headers and row numbers will be.
   * @param g   Graphics object that will help render.
   */
  private void drawGrayZones(Graphics2D g) {
    g.setColor(Color.lightGray);

    g.fillRect(0, 0, width, heightInc);

    g.fillRect(0, 0, widthInc, height);
  }

  /**
   * Private helper method that will paint the column headers and row numbers that are
   * currently visible in the spreadsheet.
   * @param g   Graphics object that will help render.
   */
  private void paintHeaders(Graphics2D g) {
    g.setColor(Color.BLACK);

    int realRow;
    int realCol;

    for (int row = 0; row < rows; row++) {
      realRow = this.row + row;

      Shape oldClip = g.getClip();
      Shape newClip = new Rectangle(0,heightInc * (row + 1), widthInc, heightInc);
      g.setClip(newClip);
      g.drawString(Integer.toString(realRow),
              (int) (widthInc / 4), (int) (heightInc * (row + 1.9)));
      g.setClip(oldClip);
    }

    for (int col = 0; col <= cols; col++) {
      realCol = this.col + col;

      Shape oldClip = g.getClip();
      Shape newClip = new Rectangle(widthInc * (col + 1),0, widthInc, heightInc);
      g.setClip(newClip);
      g.drawString(Coord.colIndexToName(realCol), (int) (widthInc * (col + 1.4)),
              (int) (heightInc / 1.5));
      g.setClip(oldClip);
    }
  }

  /**
   * Private helper method that will paint all the strings stored in the currently visible cells
   * of the spreadsheet.
   * @param g   Graphics object that will help render.
   */
  private void paintStrings(Graphics2D g) {
    g.setColor(Color.BLACK);

    int a;
    int b;
    int realRow;
    int realCol;
    String output;

    for (a = 0; a < cols; a++) {
      for (b = 0; b < rows; b++) {
        realCol = a + this.col;
        realRow = b + this.row;

        output = this.sheet.evaluateCell(new Coord(realCol, realRow)).toString();

        Shape oldClip = g.getClip();
        Shape newClip = new Rectangle(widthInc * (a + 1),heightInc * (b + 1),
            widthInc, heightInc);
        g.setClip(newClip);


        if (realCol == this.selectedCol & realRow == this.selectedRow) {
          g.setColor(Color.RED);
          g.fillRect(0, 0, width, height);
          g.setColor(Color.BLACK);
        }

        g.drawString(output, (int) (widthInc * (a + 1.1)), (int) (heightInc * (b + 1.9)));
        g.setClip(oldClip);
      }
    }
  }

  /**
   * Private helper method that will clear the screen in between each paint iteration.
   * @param g   Graphics object that will help render.
   */
  private void clearScreen(Graphics2D g) {
    g.setColor(Color.WHITE);
    g.fillRect(0,0,getWidth(), getHeight());
  }

  /**
   * Overwriting the generic JPanel paint function so that we can handle it ourselves. First sets
   * the heightInc and widthInc for all private functions to use. These represent the height of
   * each row and width of each column. Then it clears the screen, drays the grey zones, draws
   * the grid lines, paints the headers in the grey zones, and paints the string values of cells.
   * @param g   Graphics object that will help render.
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    this.height = getHeight();
    this.width = getWidth();

    this.heightInc = Math.floorDiv(height, this.rows + 1);
    this.widthInc = Math.floorDiv(width, this.cols + 1);

    Graphics2D g2 = (Graphics2D) g;

    clearScreen(g2);
    drawGrayZones(g2);
    paintLines(g2);
    paintHeaders(g2);
    paintStrings(g2);
  }

}