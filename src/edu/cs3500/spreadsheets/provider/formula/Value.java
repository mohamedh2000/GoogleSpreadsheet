package edu.cs3500.spreadsheets.provider.formula;

/**
 * To represent a type of value that a user can input into a cell. Because a formula can result into
 * a value, it extends formula. A value can be one of the following: 1) Str 2) Num 3) Bool 4) Blank
 */
public interface Value extends Formula {

  /**
   * Accepts a value visitor and gets the corresponding value/output to be used.
   *
   * @param visitor type of ValueVisitor (SumVisitor/ProductVisitor/etc)
   * @return evaluated output that will be used in other objects (namely in Product/Sum/etc.)
   */
  <R> R accept(ValueVisitor<R> visitor);

}


