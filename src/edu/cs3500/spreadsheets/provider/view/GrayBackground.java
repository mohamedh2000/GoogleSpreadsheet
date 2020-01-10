package edu.cs3500.spreadsheets.provider.view;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;


/**
 * Class used to make a cell have a gray background. We use this on the row header so users can see
 * it is a header rather than an editable cell.
 * This template was taken from the oracle website:
 * https://docs.oracle.com/javase/tutorial/uiswing/components/table.html.
 */
public class GrayBackground extends DefaultTableCellRenderer {
  /**
   * Constructor to create a gray Background which sets the horizontal alignment.
   */
  GrayBackground() {
    setHorizontalAlignment(JLabel.CENTER);
  }

  @Override
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                 boolean hasFocus, int row, int column) {
    if (table != null) {
      JTableHeader header = table.getTableHeader();
      if (header != null) {
        setBackground(header.getBackground());
        setForeground(header.getForeground());
      }
    }
    setValue(value);
    return this;
  }
}