package edu.cs3500.spreadsheets.provider.formula;

import java.util.HashSet;

import edu.cs3500.spreadsheets.model.Coord;

/**
 * To represent a Formula, which is used to represent the values stored within the cells. A formula
 * is characterized when a user inputs an equal sign (eg. "= SUM 1 2"). A formula is one of: 1) a
 * value 2) a reference to a rectangular region of cells 3) a function.
 */
public interface Formula {

  /**
   * Evaluates the formula and returns a value representing its type (Str/Bool/Num).
   *
   * @return returns output/result of the formula after it is evaluated.
   */
  Value evaluate();


  /**
   * Since cycles may be detected differently depending on the type of value/formula it is (eg.
   * single blank cells cannot have cycles, this method allows more flexibility to detect where
   * cycles are.
   *
   * @param currentCoord current coordinate
   * @return boolean to represent if there is a cycle at that coordinate
   */
  boolean cyclePresent(Coord currentCoord, HashSet<Coord> visited);

  /**
   * Accepts the visitor and visits the formula and outputs its corresponding value, which can then
   * be evaluated.
   *
   * @param visitor visitor type for the formula (LessthanVisitor/SumVisitor/etc.)
   * @param <R>     an object representing formula output
   */
  <R> R accept(FormulaVisitor<R> visitor);


  /**
   * Checks if the given Formula is a valid one. E.g Must not be self-referencing, a function
   * mismatch or a badly formatted cell reference.
   */
  void validateFormula();


  /**
   * Gets the set of coordinates that are dependent on another cell.
   * @return set of coordinates that are dependent on another cell
   */
  HashSet<Coord> getDependent();

}
