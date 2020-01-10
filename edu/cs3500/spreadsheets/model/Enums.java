package edu.cs3500.spreadsheets.model;

/**
 * Enums to define the types of Values and Operations types.
 */
public class Enums {

  /**
   * Define the value types of our cells.
   */
  public enum ValueType {
    String, Boolean, Double;
  }

  /**
   * Define the Operation types of our cells.
   */
  public enum OperationType {
    SUM, PRODUCT, LESS, STRING, NONE;
  }
}
