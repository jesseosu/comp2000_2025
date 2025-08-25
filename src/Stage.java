import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Stage {
  private final Grid grid;
  private final List<Actor> actors = new ArrayList<>();

  public Stage() {
    grid = new Grid();
    actors.add(new Cat(grid.cellAtColRow(10, 0).get()));
    actors.add(new Dog(grid.cellAtColRow(0, 15).get()));
    actors.add(new Bird(grid.cellAtColRow(12, 9).get()));
  }

  public void paint(Graphics g, Point mouseLoc) {

    grid.paint(g, mouseLoc);
    for (Actor a : actors) {
      a.paint(g);
    }


    g.setColor(Color.BLACK);
    g.drawString("Hover over a cell →", 750, 50);

    Optional<Cell> maybeCell = grid.cellAtPoint(mouseLoc);
    if (maybeCell.isPresent()) {
      Cell cell = maybeCell.get();
      g.setColor(Color.DARK_GRAY);
      g.drawString("Cell Info:", 750, 80);
      g.drawString("X: " + cell.x, 750, 100);
      g.drawString("Y: " + cell.y, 750, 120);
      g.drawString("Size: " + Cell.size + "px", 750, 140);
    } else {
      g.setColor(Color.RED);
      g.drawString("No cell here!", 750, 100);
    }
  }

  public void addActor(Actor actor) {
    actors.add(actor);
  }
}
