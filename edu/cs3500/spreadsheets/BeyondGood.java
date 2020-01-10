package edu.cs3500.spreadsheets;

import edu.cs3500.spreadsheets.controller.BasicController;
import edu.cs3500.spreadsheets.controller.ControllerInterface;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.RegularWorksheet;
import edu.cs3500.spreadsheets.model.WorksheetBuilderImpl;
import edu.cs3500.spreadsheets.provider.view.VisualViewWithEdit;
import edu.cs3500.spreadsheets.view.EditView;
import edu.cs3500.spreadsheets.view.GuiView;
import edu.cs3500.spreadsheets.view.TextualViewModel;
import edu.cs3500.spreadsheets.view.SaveFileClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The main class for our program.
 */
public class BeyondGood {
  /**
   * The main entry point.
   *
   * @param args any command-line arguments
   */
  public static void main(String[] args) throws IOException {
    /*
      TODO: For now, look in the args array to obtain a filename and a cell name,
      - read the file and build a model from it, 
      - evaluate all the cells, and
      - report any errors, or print the evaluated value of the requested cell.
    */

    if (args[0].equals("-gui")) {
      GuiView view = new GuiView(new RegularWorksheet());
      view.render();
    }

    String fileName = args[1];

    WorksheetBuilderImpl builder = new WorksheetBuilderImpl();
    String[] lineParts;
    String[] coordPair;
    int colNew;
    int rowNew;

    String line;
    RegularWorksheet ourSheet = new RegularWorksheet();

    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

      while ((line = br.readLine()) != null) {

        if (line.contains("#")) {
          line = line.substring(0, line.indexOf("#"));
        }
        if (line.indexOf(" ") > line.indexOf("=") & line.contains("=")) {
          continue;
        }

        line.trim();

        if (line.isEmpty() | line.indexOf(" ") == 0) {
          continue;
        }

        lineParts = line.split(" ", 2);

        if (lineParts.length != 2) {
          continue;
        }

        lineParts[1] = lineParts[1].replace("=", "");
        lineParts[1] = lineParts[1].trim();

        if (lineParts[1].isEmpty()) {
          continue;
        }

        coordPair = lineParts[0].split("(?<=\\D)(?=\\d)");

        colNew = Coord.colNameToIndex(coordPair[0]);
        rowNew = Integer.parseInt(coordPair[1]);

        builder.createCell(colNew, rowNew, lineParts[1]);
      }

      ourSheet = builder.createWorksheet();

    } catch (Exception e) {
      System.out.println("Error here");
      System.out.println(e.getMessage());
    }


    if (args[2].equals("-save")) {
      //Find out what it meant by using textual view(textualViewModel) to save file(viewModel2)
      //I think viewModel2 did textuals job by accident
      String fileToSave = args[3];

      try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

        PrintWriter writer = new PrintWriter(String.valueOf(new FileReader(fileName)));
        TextualViewModel text = new TextualViewModel(ourSheet, writer);
        SaveFileClass saveFile = new SaveFileClass(ourSheet, writer, fileToSave);
        saveFile.render();
        text.render();

      } catch (Exception e) {
        System.out.println("Error here");
        System.out.println(e.getMessage());
      }
      return;
    }

    if (args[2].equals("-provider")) {

      try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

        ControllerInterface controller = new BasicController(ourSheet);
        VisualViewWithEdit view =
                new VisualViewWithEdit(fileName, ourSheet, 850, 500);

        controller.setView(view);
        controller.renderView();

      } catch (Exception e) {
        System.out.println("Error here");
        System.out.println(e.getMessage());
      }
    }

    if (args[2].equals("-edit")) {

      try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

        ControllerInterface controller = new BasicController(ourSheet);
        EditView view = new EditView(ourSheet);
        controller.setView(view);
        controller.renderView();

      } catch (Exception e) {
        System.out.println("Error here");
        System.out.println(e.getMessage());
      }
      return;
    }

    if (args[2].equals("-gui")) {

      try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

        GuiView view = new GuiView(ourSheet);
        view.render();

      } catch (Exception e) {
        System.out.println("Error here");
        System.out.println(e.getMessage());
      }
      return;
    }

    if (args.length > 3) {
      try {
        String evalCell = args[3];
        coordPair = evalCell.split("(?<=\\D)(?=\\d)");
        colNew = Coord.colNameToIndex(coordPair[0]);
        rowNew = Integer.parseInt(coordPair[1]);

        String output = ourSheet.evaluateCell(new Coord(colNew, rowNew)).toString();

        if (output.contains(".0")) {
          output = output.replace(".0", ".000000");
        }

      } catch (Exception e) {
        System.out.println("Error here");
        System.out.println(e.getMessage());
      }
    }
  }
}
