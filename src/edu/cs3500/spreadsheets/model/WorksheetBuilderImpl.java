package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.IdentifyInput;
import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.Sexp;

/**
 * An implementation of the worksheet builder that uses our RegularWorksheet class.
 */
public class WorksheetBuilderImpl implements WorksheetReader.WorksheetBuilder<RegularWorksheet> {

  private RegularWorksheet worksheet;

  /**
   * A constructor that takes no arguments and create a new Regular Worksheet
   * that is stored as a private.
   */
  public WorksheetBuilderImpl() {
    this.worksheet = new RegularWorksheet();
  }

  /**
   * Creates a new cell at the given coordinates and fills in its raw contents.
   * @param col the column of the new cell (1-indexed)
   * @param row the row of the new cell (1-indexed)
   * @param contents the raw contents of the new cell: may be {@code null}, or any string.
   *                 Strings beginning with an {@code =} character should be
   *                 treated as formulas; all other strings should be treated as number or
   *                 boolean values if possible, and string values otherwise.
   * @return this {@link WorksheetBuilderImpl}
   */
  @Override
  public WorksheetReader.WorksheetBuilder<RegularWorksheet> createCell(int col,
                                                                       int row, String contents) {
    Coord coord = new Coord(col, row);

    IdentifyInput inputHandle = new IdentifyInput();

    Parser parse = new Parser();
    Sexp result = parse.parse(contents);

    SingleCell cell = result.accept(inputHandle);

    this.worksheet.assignCoord(coord, cell);

    return this;
  }

  /**
   * Finalizes the construction of the worksheet and returns it.
   * @return the fully-filled-in worksheet
   */
  @Override
  public RegularWorksheet createWorksheet() {
    return worksheet;
  }
}
