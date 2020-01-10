package edu.cs3500.spreadsheets.view;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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
import static junit.framework.TestCase.assertTrue;

/**
 * Test the SaveFileClass.
 */
public class SaveFileClassTest {

  @Test
  public void render() throws IOException {
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

    PrintWriter writer =
            new PrintWriter(String.valueOf(new FileReader("src/forTest.txt")));
    new SaveFileClass(sheet, writer, "src/forTest.txt").render();

    BufferedReader b1 = new BufferedReader(new FileReader("src/forTest.txt"));
    String line;
    String fullInput = "";

    while ((line = b1.readLine()) != null) {
      fullInput += line;
      fullInput += "\n";
    }

    StringBuilder str = new StringBuilder();

    for (Map.Entry<Coord, SingleCell> entry : sheet.getData().entrySet()) {
      Coord key = entry.getKey();
      String value = entry.getValue().inputForm();
      str.append(key + " = " + value + "\n");
    }

    String str1 = str.toString().trim();
    String fullInput2 = fullInput.trim();
    assertTrue(fullInput2.contains(str1));
    assertEquals(str1, fullInput2);

  }
}