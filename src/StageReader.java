import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class StageReader {

  public static Stage readStage(String path) {
    Path p = Path.of(path);

    List<String> lines;
    try {
      lines = Files.readAllLines(p);
    } catch (IOException ioe) {
      System.err.println("[StageReader] Failed to read '" + path + "': " + ioe.getMessage());
      System.err.println("[StageReader] Falling back to default Stage().");
      return new Stage(); // fallback so the app still runs (Task 14)
    }

    Stage stage = new Stage();
    // Clear the default actors so the file fully controls placement
    stage.actors.clear();

    for (int i = 0; i < lines.size(); i++) {
      String raw = lines.get(i);
      String line = raw == null ? "" : raw.trim();

      if (line.isEmpty() || line.startsWith("#")) continue;

      try {
        placeActorFromLine(stage, line);
      } catch (StageFormatException sfe) {
        // Log and continue processing other lines
        System.err.println("[StageReader] Line " + (i + 1) + ": " + sfe.getMessage());
      } catch (Exception unexpected) {
        // Catch-all guard so one bad surprise doesn't kill the whole stage load
        System.err.println("[StageReader] Line " + (i + 1) + ": Unexpected error: " + unexpected);
      }
    }

    return stage;
  }

  private static void placeActorFromLine(Stage stage, String line) throws StageFormatException {
    int eq = line.indexOf('=');
    if (eq <= 0 || eq == line.length() - 1) {
      throw new StageFormatException("Invalid format (expected 'ColRow=Actor'): '" + line + "'");
    }

    String lhs = line.substring(0, eq).trim();   // e.g., "J4" or "S15"
    String rhs = line.substring(eq + 1).trim();  // e.g., "cat"

    if (lhs.isEmpty() || rhs.isEmpty()) {
      throw new StageFormatException("Missing coordinate or actor in line: '" + line + "'");
    }

    // Parse coordinate: first char letter => column, remaining chars => row (0-based)
    char colChar = lhs.charAt(0);
    if (!Character.isLetter(colChar)) {
      throw new StageFormatException("Column must be a letter A..Z: '" + lhs + "'");
    }
    int col = Character.toUpperCase(colChar) - 'A';

    String rowPart = lhs.substring(1);
    if (rowPart.isEmpty()) {
      throw new StageFormatException("Row index missing after column letter: '" + lhs + "'");
    }

    int row;
    try {
      row = Integer.parseInt(rowPart);
    } catch (NumberFormatException nfe) {
      throw new StageFormatException("Row must be an integer, got '" + rowPart + "'");
    }

    // Resolve cell (Optional<Cell>)
    Optional<Cell> maybeCell = stage.grid.cellAtColRow(col, row);
    if (maybeCell.isEmpty()) {
      throw new StageFormatException("Cell out of bounds or not present: col=" + col + ", row=" + row);
    }
    Cell cell = maybeCell.get();

    // Create actor
    Actor actor = makeActor(rhs, cell);
    stage.actors.add(actor);
  }

  private static Actor makeActor(String actorName, Cell at) throws StageFormatException {
    String key = actorName.toLowerCase(Locale.ROOT);
    return switch (key) {
      case "cat"  -> new Cat(at);
      case "dog"  -> new Dog(at);
      case "bird" -> new Bird(at);
      default -> throw new StageFormatException("Unknown actor '" + actorName + "'. Expected one of: cat, dog, bird.");
    };
  }
}
