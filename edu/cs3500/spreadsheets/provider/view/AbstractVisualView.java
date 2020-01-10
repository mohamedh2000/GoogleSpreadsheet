package edu.cs3500.spreadsheets.provider.view;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

import edu.cs3500.spreadsheets.provider.model.ISpreadsheetViewOnly;

/**
 * To represent an abstract visual view. This class extends DefaultView and implements some repeated
 * methods to reduce the amount of duplication in the visual view and visual view with edit classes.
 * We chose to implement it like this so that when we create new visual views, they can simply
 * override methods in this class.
 */
public abstract class AbstractVisualView extends DefaultView {

  protected String name;
  protected int windowWidth;
  protected int windowHeight;
  protected JFrame jf;
  protected SpreadsheetTable table;

  /**
   * Constructor for abstract visual view.
   *
   * @param filename filename of spreadsheet
   * @param ss       spreadsheet model
   * @param ww       window width
   * @param wh       window height
   */
  public AbstractVisualView(String filename, ISpreadsheetViewOnly ss, int ww, int wh) {
    super(ss);
    this.windowHeight = wh;
    this.windowWidth = ww;
    this.name = filename;
    this.table = new SpreadsheetTable(ss, windowWidth, windowHeight);
  }

  /**
   * Sets up the JFrame and other settings so view can be rendered.
   */
  protected void renderSetup() {
    jf = new JFrame();
    jf.setTitle(name);
    jf.setSize(this.windowWidth, this.windowHeight);
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jf.addComponentListener(new ComponentAdapter() {
      public void componentResized(ComponentEvent componentEvent) {
        Rectangle r = jf.getBounds();
        table.getTable()
                .setPreferredScrollableViewportSize(new Dimension(r.width - 150, r.height - 150));
        table.getTable().setFillsViewportHeight(true);

        jf.setPreferredSize(jf.getSize());

        System.out.println("w:" + r.width + "h:" + r.height);
      }
    });
  }

}