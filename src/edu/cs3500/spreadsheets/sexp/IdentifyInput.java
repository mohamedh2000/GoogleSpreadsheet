package edu.cs3500.spreadsheets.sexp;

import edu.cs3500.spreadsheets.model.BoolCell;
import edu.cs3500.spreadsheets.model.Enums.ValueType;
import edu.cs3500.spreadsheets.model.SingleReferenceCell;
import java.util.ArrayList;
import java.util.List;
import edu.cs3500.spreadsheets.model.LeftFunctionCell;
import edu.cs3500.spreadsheets.model.LessThanCell;
import edu.cs3500.spreadsheets.model.NumberCell;
import edu.cs3500.spreadsheets.model.ProductCell;
import edu.cs3500.spreadsheets.model.ReferenceCell;
import edu.cs3500.spreadsheets.model.SingleCell;
import edu.cs3500.spreadsheets.model.StringCell;
import edu.cs3500.spreadsheets.model.SumCell;

/**
 * Implements the Visitor for the Sexp.
 */
public class IdentifyInput implements SexpVisitor<SingleCell> {
  /**
   * Process a boolean value.
   * @param b the value
   * @return the desired result
   */
  public SingleCell visitBoolean(boolean b) {
    return new BoolCell(b, ValueType.Boolean);
  }

  /**
   * Process a numeric value.
   * @param d the value
   * @return the desired result
   */
  public SingleCell visitNumber(double d) {
    return new NumberCell(d, ValueType.Double);
  }

  /**
   * Process a list value.
   * @param l the contents of the list (not yet visited)
   * @return the desired result
   */
  public SingleCell visitSList(List<Sexp> l) {
    SingleCell operation = l.get(0).accept(this);
    ArrayList<SingleCell> contents = new ArrayList<>();

    for (Sexp item : l.subList(1,l.size())) {
      contents.add(item.accept(this));
    }

    if (operation.getValue(null, null).equals("SUM")) {
      return new SumCell(contents);
    }
    else if (operation.getValue(null, null).equals("PRODUCT")) {
      return new ProductCell(contents);
    }
    else if (operation.getValue(null, null).equals("<")) {
      return new LessThanCell(contents);
    }
    else if (operation.getValue(null, null).equals("LEFT")) {
      return new LeftFunctionCell(contents);
    }
    else {
      throw new IllegalArgumentException("Not a supported operation" +
          operation.getValue(null, null).toString());
    }
  }

  /**
   * Process a symbol.
   * @param s the value
   * @return the desired result
   */
  public SingleCell visitSymbol(String s) {

    int indexOfSemi = s.indexOf(":");

    if (indexOfSemi != -1) {
      return new ReferenceCell(s.substring(0, indexOfSemi),
              s.substring(indexOfSemi + 1, s.length()));
    }
    else if (s.matches(".*\\d.*")) {
      return new SingleReferenceCell(s);
    }

    return new StringCell(s, ValueType.String);
  }

  /**
   * Process a string value.
   * @param s the value
   * @return the desired result
   */
  public SingleCell visitString(String s) {
    return new StringCell(s, ValueType.String);
  }
}
