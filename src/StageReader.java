import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class StageReader {
  public static Stage readStage(String path) throws IOException {
    Stage stage = new Stage();
    List<String> lines = Files.readAllLines(Paths.get(path));
    return new Stage();
  }
} 