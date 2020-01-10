package edu.cs3500.spreadsheets.view;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;

import edu.cs3500.spreadsheets.model.BoolCell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Enums;
import edu.cs3500.spreadsheets.model.NumberCell;
import edu.cs3500.spreadsheets.model.ReferenceCell;
import edu.cs3500.spreadsheets.model.RegularWorksheet;
import edu.cs3500.spreadsheets.model.SingleCell;
import edu.cs3500.spreadsheets.model.StringCell;
import edu.cs3500.spreadsheets.model.SumCell;

import static junit.framework.TestCase.assertEquals;

/**
 * To test the TextualView Class.
 */
public class TextualViewModelTest {

  @Test
  public void render() throws FileNotFoundException {
    ArrayList<SingleCell> arr = new ArrayList<SingleCell>();

    RegularWorksheet sheet = new RegularWorksheet();
    sheet.assignCoord(new Coord(1,1),
            new NumberCell(50.0, Enums.ValueType.Double));
    sheet.assignCoord(new Coord(1,3),
            new NumberCell(50.0, Enums.ValueType.Double));
    sheet.assignCoord(new Coord(1, 2),
            new BoolCell(true, Enums.ValueType.Boolean));
    sheet.assignCoord(new Coord(3, 5),
            new StringCell("ILoveDogs", Enums.ValueType.String));
    sheet.assignCoord(new Coord(1, 3),
            new BoolCell(true, Enums.ValueType.Boolean));
    sheet.assignCoord(new Coord(5, 3),
            new BoolCell(false, Enums.ValueType.Boolean));
    sheet.assignCoord(new Coord(50,100),
            new ReferenceCell("A1", "A2"));

    arr.add(new NumberCell(50.0, Enums.ValueType.Double));
    arr.add(new NumberCell(50.0, Enums.ValueType.Double));

    sheet.assignCoord(new Coord(100, 50),
            new SumCell(arr));

    StringBuilder str = new StringBuilder();

    TextualViewModel view = new TextualViewModel(sheet, str);
    view.render();

    StringBuilder str2 = new StringBuilder();
    for (Map.Entry<Coord, SingleCell> entry : sheet.getData().entrySet()) {
      String value = entry.getValue().inputForm();
      str2.append(value);
      str2.append("\n");
    }
    assertEquals(str.toString(), str2.toString());

  }
}