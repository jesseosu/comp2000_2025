import java.awt.Color;
import java.awt.Graphics;

public class Cat extends Actor {
  public Cat(Cell cell) {
    super(cell, Color.BLUE);
  }
  @Override public void paint(Graphics g) { paintDisc(g); }
}
