import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

public abstract class Actor {
  protected Cell loc;                 // where the actor lives (top-left anchor)
  protected final List<Polygon> shapes = new ArrayList<>(); // geometry parts

  // Base paint: fill then outline each polygon
  public void paint(Graphics g) {
    for (Polygon p : shapes) {
      g.setColor(Color.LIGHT_GRAY);   // neutral fill (not used to distinguish)
      g.fillPolygon(p);
      g.setColor(Color.BLACK);        // common outline
      g.drawPolygon(p);
    }
  }
}
