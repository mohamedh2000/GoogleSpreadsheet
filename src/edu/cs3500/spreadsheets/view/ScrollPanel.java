package edu.cs3500.spreadsheets.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import javax.swing.JScrollBar;
import javax.swing.JPanel;

/**
 * A ScrollPanel class that was created as a custom means for implemetning scrolling
 * in our GUIView. It will hold a StaticPanel that will print the Spreadsheet, and two scrollbars
 * that will allow users to scroll around the sheet.
 */
public class ScrollPanel extends JPanel {

  private JScrollBar horizontal;
  private JScrollBar vertical;

  private StandalonePanel staticPanel;

  /**
   * A public constructor that takes in a staticPanel to use for rendering the model.
   * @param staticPanel   Panel that draws a model.
   */
  public ScrollPanel(StandalonePanel staticPanel) {
    super();

    this.staticPanel = staticPanel;

    this.setSize(this.staticPanel.getSize());

    setupScrollBars();
    addComponents();
    addListeners();
  }

  /**
   * Helper function to create the two scrollbars and set the maximum scroll on each axis
   * to at least show the farther assigned cell that the staticPanel is rendering.
   */
  private void setupScrollBars() {
    int setCol = 21;
    int setRow = 986;

    int maxCol = this.staticPanel.getMaxCol();
    int maxRow = this.staticPanel.getMaxRow();

    if (maxRow > 1000) {
      setRow = maxRow - 14;
    }
    if (maxCol > 21) {
      setCol = maxCol - 5;
    }


    horizontal = new JScrollBar(0);
    vertical = new JScrollBar(1);

    this.horizontal.setMaximum(setCol);
    this.vertical.setMaximum(setRow);
  }

  /**
   * Helper function that will add the components using a GridBagLayout and a Constraints object
   * to add all panels and scrollbars into the correct location.
   */
  private void addComponents() {
    this.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    c.ipadx = 0;
    c.ipady = 0;

    c.fill = GridBagConstraints.BOTH;
    c.gridx = 0;
    c.gridy = 0;
    c.weightx = 1;
    c.weighty = 1;
    this.add(staticPanel, c);

    c.fill = GridBagConstraints.VERTICAL;
    c.gridx = 1;
    c.gridy = 0;
    c.weightx = 0;
    c.weighty = 0;
    this.add(vertical, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 1;
    this.add(horizontal, c);
  }

  /**
   * Private function that adds 2 listeners to the 2 scrollbars so we know when they move. This
   * will allow us to tell the staticPanel that it is being moved and will change the Coord of
   * it's top-leftmost displayed cell to simulate "scrolling".
   */
  private void addListeners() {
    this.horizontal.addAdjustmentListener(new AdjustmentListener() {
      @Override
      public void adjustmentValueChanged(AdjustmentEvent e) {
        if (e.getValue() >= horizontal.getMaximum() - 15) {
          horizontal.setMaximum(horizontal.getMaximum() + 26);
        }
        staticPanel.setStartCol(e.getValue());
        repaint();
      }
    });

    this.vertical.addAdjustmentListener(new AdjustmentListener() {
      @Override
      public void adjustmentValueChanged(AdjustmentEvent e) {
        if (e.getValue() >= vertical.getMaximum() - 15) {
          vertical.setMaximum(vertical.getMaximum() + 1000);
        }
        staticPanel.setStartRow(e.getValue());
        repaint();
      }
    });
  }

}
