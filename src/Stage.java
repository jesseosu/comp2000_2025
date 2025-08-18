import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.random.*;

public class Stage {
  final Grid grid;
  final List<Actor> actors = new ArrayList<>();

  public Stage() {
    this.grid = new Grid();

    // Choose cells
    Cell catCell  = grid.cells[2][3];
    Cell dogCell  = grid.cells[8][5];
    Cell birdCell = grid.cells[9][9];

    // Color the cells themselves
    catCell.setColor(java.awt.Color.BLUE);
    dogCell.setColor(java.awt.Color.YELLOW);
    birdCell.setColor(java.awt.Color.GREEN);

    actors.add(new Cat(catCell));
    actors.add(new Dog(dogCell));
    actors.add(new Bird(birdCell));
  }

  public void paint(Graphics g, Point mousePos) {
    grid.paint(g, mousePos);
    for (Actor a : actors) a.paint(g);
  }
}
