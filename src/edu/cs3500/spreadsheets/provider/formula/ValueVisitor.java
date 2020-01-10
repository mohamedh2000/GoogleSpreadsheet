package edu.cs3500.spreadsheets.provider.formula;

/**
 * Used by the function classes and visits the various types of Values. Methods return a
 * parameterized type because output changes depending on value as well as which function uses it.
 */
public interface ValueVisitor<R> {

  /**
   * Visits a boolean and gets the corresponding output.
   *
   * @param b given boolean
   * @return type that represents the boolean, or if it should be ignored in an operation
   */
  R visitBool(boolean b);

  /**
   * Visits a string and gets the corresponding output.
   *
   * @param s given string
   * @return type that represents the string, or if it should be ignored in an operation
   */
  R visitStr(String s);

  /**
   * Visits a number and gets the corresponding output.
   *
   * @param d given double
   * @return type that represents the double, or if it should be ignored in an operation
   */
  R visitNum(double d);

  /**
   * Visits a blank and gets the corresponding output.
   *
   * @return type that represents that the current cell should be ignored in an operation
   */
  R visitBlank();


}
