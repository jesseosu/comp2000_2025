import java.awt.Color;
import java.awt.Graphics;

public class Bird extends Actor {
  public Bird(Cell cell) {
    super(cell, Color.GREEN);
  }
  @Override public void paint(Graphics g) { paintDisc(g); }
}
