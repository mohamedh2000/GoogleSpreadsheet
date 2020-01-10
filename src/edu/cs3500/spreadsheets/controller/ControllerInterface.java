package edu.cs3500.spreadsheets.controller;

import edu.cs3500.spreadsheets.view.IView;
import java.io.IOException;

/**
 * An interface for our controllers that also extends the FeatureInterface
 * as we build our controller to also act as a Feature Class for interacting
 * with listeners.
 */
public interface ControllerInterface extends FeatureInterface {

  /**
   * Set's the view of the controller to be the one passed in. This controller will
   * then pass itself as a feature interface to the given view so that it can interact
   * with the view listeners.
   * @param view    The new view to be assigned to the controller.
   */
  void setView(IView view);

  /**
   * Renders the given view. Will throw an IOException if there are issues with
   * objects such as appendable in the process. Nothing will happen if the view
   * is null, meaning one has not been assigned to the controller yet.
   * @throws IOException    Throws IOException if there is an issue with appendable.
   */
  void renderView() throws IOException;
}
