package edu.cs3500.spreadsheets.provider.view;

import javax.swing.*;
import javax.swing.table.TableModel;

import edu.cs3500.spreadsheets.model.RegularWorksheet;
import edu.cs3500.spreadsheets.provider.model.ISpreadsheetViewOnly;

class HolderButton extends JPanel {
  JButton workSheetPage;
  ISpreadsheetViewOnly ss;
  TableModel model;

  HolderButton(int i) {
    this.addButtons(i);
    this.emptyModel();
  }

  HolderButton(int i, ISpreadsheetViewOnly ss) {
    this.ss = ss;
    this.addButtons(i);
    SpreadsheetTable table = new SpreadsheetTable(ss, 0,0);
    this.model = table.getTable().getModel();
  }

  ISpreadsheetViewOnly getSheet() {
    return ss;
  }

  private void addButtons(int i) {
    workSheetPage = new JButton(Integer.toString(i));
    this.add(workSheetPage);
  }

  private void emptyModel() {
    this.ss = new RegularWorksheet();
    SpreadsheetTable table2 = new SpreadsheetTable(ss,0,0);
    this.model = table2.getTable().getModel();
  }

}
