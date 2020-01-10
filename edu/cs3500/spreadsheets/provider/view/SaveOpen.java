package edu.cs3500.spreadsheets.provider.view;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.cs3500.spreadsheets.controller.FeatureInterface;

/**
 * To represent the editing textbox seen when a user needs to edit a cell. This panel also contains
 * several buttons that allows the user to add columns, submit the current edits, cancel the edits,
 * and add more rows to the spreadsheet.
 */
class SaveOpen extends JPanel {
  private JButton save;
  private JButton open;
  private JTextField textbox;

  /**
   * Constructor for the text box and button panel.
   */
  SaveOpen() {
    this.addTextbox();
    this.addButtons();
  }

  /**
   * Initializes the textbox.
   */
  private void addTextbox() {
    textbox = new JTextField(30);
    add(textbox);
  }

  /**
   * Adds the submit/cancel/addRow/addCol buttons to the panel.
   */
  private void addButtons() {
    save = new JButton("save");
    open = new JButton("open");
    add(save);
    add(open);
  }

  /**
   * Adds the functionality of each button given a Feature.
   *
   * @param f feature controller
   */
  void addFeatures(FeatureInterface f) {
    save.addActionListener(evt -> f.save(this.textbox.getText()));
    open.addActionListener(evt -> f.open(this.textbox.getText()));
    save.setActionCommand("save");
    open.setActionCommand("open");
  }
}
