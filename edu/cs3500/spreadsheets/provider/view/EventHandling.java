package edu.cs3500.spreadsheets.provider.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.*;

import edu.cs3500.spreadsheets.view.IView;

/**
 * Use this class to add actionListeners to new pages added.
 */
public class EventHandling implements ActionListener {
  private ArrayList<JButton> array;
  private final IView view;

  EventHandling(IView view, ArrayList<JButton> array) {
    this.view = view;
    this.array = array;
  }

  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {

    for (JButton jButton : array) {
      if (jButton.getBackground().equals(Color.CYAN)) {
        view.changeCurrentButtonColor(jButton.getText());
      }
    }

    for (JButton jButton : array) {
      if (e.getSource().equals(jButton)) {
        view.changeTableView(jButton.getText());
      }
    }

  }
}
