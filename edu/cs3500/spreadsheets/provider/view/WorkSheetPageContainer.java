package edu.cs3500.spreadsheets.provider.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.table.TableModel;

import edu.cs3500.spreadsheets.controller.FeatureInterface;
import edu.cs3500.spreadsheets.provider.model.ISpreadsheetViewOnly;
import edu.cs3500.spreadsheets.view.IView;

/**
 * A New WorksheetPageContainer that will hold all the new buttons where if one is clicked it will
 * switch the table view to the sheet information of that page. It can also add new pages.
 */
class WorkSheetPageContainer extends JPanel {
  private JButton plus;
  public HashMap<Integer, HolderButton> listOfPages;
  private int i;
  private final IView view;
  private HolderButton firstPage;

  WorkSheetPageContainer(IView view, ISpreadsheetViewOnly ss) {
    this.view = view;
    i = 1;
    this.addButtons();
    this.listOfPages = new HashMap<>();
    firstPage = new HolderButton(i,ss);

    JButton temp = firstPage.workSheetPage;
    temp.setBackground(Color.CYAN);
    temp.setOpaque(true);

    listOfPages.put(i, firstPage);
    i += 1;
    addPages();
  }

  /**
   * Add a plus button. When clicked it should add a new page that can be switched to.
   */
  private void addButtons() {
    plus = new JButton("+");
    add(plus);
  }

  /**
   * Add to the container Every JButton in each page. Adds an eventhandler class so that I can add
   * actionlisteners to every one of the new pages added.
   * Had to feed Event Handler the current View which is bad design but couldn't find a workaround.
   * I did make the view final so that it cannot be mutated.
   */
  private void addPages() {
    //adds button to the container
    ArrayList<JButton> array = new ArrayList<>();
    for(Map.Entry<Integer, HolderButton> hb : listOfPages.entrySet()) {
      array.add(hb.getValue().workSheetPage);
    }

    //add Actionlistener to every new button made
    EventHandling handler = new EventHandling(view,array);

    for (Map.Entry<Integer, HolderButton> hb : listOfPages.entrySet()) {
      this.add(hb.getValue().workSheetPage);
      hb.getValue().workSheetPage.addActionListener(handler);
    }
  }

  /**
   * Be able to add a new Page.
   */
  void addWorkSheet() {
    HolderButton newPage = new HolderButton(i);
    listOfPages.put(i, newPage);
    i += 1;
    addPages();
    validate();
    repaint();
  }

  void addFeatures(FeatureInterface f) {
    plus.addActionListener(evt -> f.addNewWorkSheet());
  }

  /**
   * Get the table model by tracing back the buttonLabel number.
   * @param buttonLabel The button label of the JButton pressed.
   * @return Using the button label, return the TableModel the Table has to now display.
   */
  TableModel getTableModel(String buttonLabel) {
    int numToLookFor = Integer.parseInt(buttonLabel);
    return listOfPages.get(numToLookFor).model;
  }

  public void changeCurrentButtonColor(String buttonLabel) {
    for (Map.Entry<Integer, HolderButton> hb : listOfPages.entrySet()) {
      if(hb.getValue().workSheetPage.getBackground().equals(Color.CYAN)) {
        hb.getValue().workSheetPage.setBackground(Color.white);
        hb.getValue().workSheetPage.setOpaque(false);
      }
    }
    JButton newButton =
            listOfPages.get(Integer.parseInt(buttonLabel)).workSheetPage;
    newButton.setBackground(Color.CYAN);
    newButton.setOpaque(true);
    repaint();
    validate();
  }

  public void updateHolderModel(ISpreadsheetViewOnly ss, TableModel table) {
    for(Map.Entry<Integer, HolderButton> hb : listOfPages.entrySet()) {
      if(hb.getValue().workSheetPage.getBackground().equals(Color.CYAN)) {
        hb.getValue().ss = ss;
        hb.getValue().model = table;
      }
    }
    repaint();
    validate();
  }

}
