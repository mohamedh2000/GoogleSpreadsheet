package edu.cs3500.spreadsheets.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Our custom MouseListener for our controllers to use in order to interact
 * with the view by acting as a FeatureInterface that's informed of clicks
 * on the View panels that implement this listener.
 */
public class CustomMouseListener implements MouseListener {

  private FeatureInterface feature;

  /**
   * Our constructor that takes in a FeatureInterface to talk to every
   * time there is an event.
   * @param feature   The FeatureInterface (controller) that we interact with.
   */
  public CustomMouseListener(FeatureInterface feature) {
    this.feature = feature;
  }

  /**
   * Invoked when the mouse button has been clicked (pressed and released) on a component.
   *
   * @param e the event to be processed
   */
  @Override
  public void mouseClicked(MouseEvent e) {
    this.feature.clickAt(e.getX(), e.getY());
  }

  /**
   * Invoked when a mouse button has been pressed on a component.
   *
   * @param e the event to be processed
   */
  @Override
  public void mousePressed(MouseEvent e) {
    return;
  }

  /**
   * Invoked when a mouse button has been released on a component.
   *
   * @param e the event to be processed
   */
  @Override
  public void mouseReleased(MouseEvent e) {
    return;
  }

  /**
   * Invoked when the mouse enters a component.
   *
   * @param e the event to be processed
   */
  @Override
  public void mouseEntered(MouseEvent e) {
    return;
  }

  /**
   * Invoked when the mouse exits a component.
   *
   * @param e the event to be processed
   */
  @Override
  public void mouseExited(MouseEvent e) {
    return;
  }
}
