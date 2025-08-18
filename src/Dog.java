import java.awt.Color;
import java.awt.Graphics;

public class Dog extends Actor {
  public Dog(Cell cell) {
    super(cell, Color.YELLOW);
  }
  @Override public void paint(Graphics g) { paintDisc(g); }
}
