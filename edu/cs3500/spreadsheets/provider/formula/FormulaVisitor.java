package edu.cs3500.spreadsheets.provider.formula;


import edu.cs3500.spreadsheets.model.SingleReferenceCell;

/**
 * Visitor that visits formulas. It is used in RectangleRef and SingleRef to evaluate the different
 * types of formulas.
 */
public interface FormulaVisitor<R> {

  /**
   * Given a rectangle of references, visit each one and output the corresponding answer.
   *
   * @param rf rectangle formula
   * @return parameterized value symbolizing output the formulas
   */
  R visitRectangleRef(SingleReferenceCell rf);

  /**
   * Given a single reference/formula, output the corresponding answer.
   *
   * @param f rectangle formula
   * @return parameterized value symbolizing output of formula
   */
  R visitFormula(Formula f);

}
