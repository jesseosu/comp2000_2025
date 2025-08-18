import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Cell {
  int x;
  int y;
  static int size = 35;

  private Color fillColor = Color.WHITE;  // default background

  public Cell(int inX, int inY) {
    x = inX;
    y = inY;
  }

  public void setColor(Color c) { this.fillColor = c; }

  public void paint(Graphics g, Point mousePos) {
    if (contains(mousePos)) {
      g.setColor(Color.GRAY);
    } else {
      g.setColor(fillColor);
    }
    g.fillRect(x, y, size, size);
    g.setColor(Color.BLACK);
    g.drawRect(x, y, size, size);
  }

  public boolean contains(Point p) {
    if (p == null) return false;
    return x < p.x && x + size > p.x && y < p.y && y + size > p.y;
  }
}
