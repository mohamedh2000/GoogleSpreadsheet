package edu.cs3500.spreadsheets.controller;

import static org.junit.Assert.assertTrue;

import edu.cs3500.spreadsheets.model.GetOnlyModel;
import edu.cs3500.spreadsheets.model.MockModel;
import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.view.IView;
import edu.cs3500.spreadsheets.view.MockView;
import java.awt.Button;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * Tests the Controller with against a mock model and view to ensure that the proper
 * commands are being passed and the order of processes is correct.
 */
public class BasicControllerTest {

  /**
   * Runs the tests for the controller.
   * @param args    generic for main, not used
   */
  public static void main(String[] args) {

    Appendable modelAp = new StringBuilder();
    Worksheet model = new MockModel(modelAp);
    ControllerInterface controller = new BasicController(model);

    Appendable viewAp = new StringBuilder();
    IView view = new MockView((GetOnlyModel) model, viewAp);

    controller.setView(view);

    // Check to make sure the model hasn't been touched yet
    assertTrue(modelAp.toString().isEmpty());

    // Check that the Controller was passed to view as a Feature
    assertTrue(viewAp.toString().contains("Hook up controller"));

    // Check that the MouseListener was passed to view
    assertTrue(viewAp.toString().contains("Hook up mouse listener"));

    try {
      controller.renderView();
      // Check that the view had it's render method called
      assertTrue(viewAp.toString().contains("Render View"));
    }
    catch (IOException e) {
      throw new IllegalStateException("Test Failed");
    }


    controller.clickAt(10, 20);
    // Check that the view had it's getCellAt(x, y) method called
    assertTrue(viewAp.toString().contains("ViewGet cell 10, 20"));


    controller.clickCheckBox("13");
    // Check that the model was told to assign Cell (7,13) as it's the hard coded
    // selected cell in the mocks
    assertTrue(modelAp.toString().contains("Assign 7, 13"));

    //Check that the view was asked to select Cell (7,13) as it's hard coded as the example
    // selected cell to highlight. Then it should also Repaint itself.
    assertTrue(viewAp.toString().contains("Select Cell 7, 13"));
    assertTrue(viewAp.toString().contains("Repaint View."));


    controller.clickDeleteBox();
    // Check that the view was told to reset it mutable features
    assertTrue(viewAp.toString().contains("Reset view"));


    MockView mockView = (MockView) view;
    mockView.simulateCheck();
    mockView.simulateUndo();
    mockView.simulateUndo();


    // Check that the view was told to reset itself twice because it told
    // the controller twice that it's undo box was clicked
    assertTrue(viewAp.toString().contains("Reset viewReset view"));

    // Create a fake mouse Event to test listener
    MouseEvent ev = new MouseEvent(new Button(), 0,0,0,7,
        13,0,true);

    mockView.getMouseListener().mouseClicked(ev);

    // Check if view was asked to get the Cell 7,13
    assertTrue(viewAp.toString().contains("Get cell 7, 13"));

  }

}