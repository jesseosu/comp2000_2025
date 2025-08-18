import java.awt.Color;
import java.awt.Graphics;

public abstract class Actor {
  protected final Cell cell;
  protected final Color color;

  public Actor(Cell cell, Color color) {
    this.cell = cell;
    this.color = color;
  }

  public abstract void paint(Graphics g);

  protected void paintDisc(Graphics g) {
    int pad = 6;
    int x = cell.x + pad;
    int y = cell.y + pad;
    int d = Cell.size - 2 * pad;

    g.setColor(color);
    g.fillOval(x, y, d, d);
    g.setColor(Color.BLACK);
    g.drawOval(x, y, d, d);
  }
}
