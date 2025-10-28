import java.awt.Color;
import java.awt.Polygon;
<<<<<<< Updated upstream
import java.util.ArrayList;

public class Bird extends Actor {
  public static final int birbMoves = 3;

  public Bird(Cell inLoc, boolean isBot) {
    super(inLoc, Color.GREEN, isBot, birbMoves);
  }

  protected void setPoly() {
    display = new ArrayList<Polygon>();
    Polygon wing1 = new Polygon();
    wing1.addPoint(loc.x + 5, loc.y + 5);
    wing1.addPoint(loc.x + 15, loc.y + 17);
    wing1.addPoint(loc.x + 5, loc.y + 17);
    Polygon wing2 = new Polygon();
    wing2.addPoint(loc.x + 30, loc.y + 5);
    wing2.addPoint(loc.x + 20, loc.y + 17);
    wing2.addPoint(loc.x + 30, loc.y + 17);
    Polygon body = new Polygon();
    body.addPoint(loc.x + 15, loc.y + 10);
    body.addPoint(loc.x + 20, loc.y + 10);
    body.addPoint(loc.x + 20, loc.y + 25);
    body.addPoint(loc.x + 15, loc.y + 25);
    display.add(body);
    display.add(wing1);
    display.add(wing2);
  }
}
=======
import java.awt.Color;

public class Bird extends Actor {
    public Bird(Cell inLoc) {
        super(inLoc, Color.GREEN);
    }
    
    @Override
    protected void createShapes() {
        shapes.clear();
        
        int centerX = loc.x + Cell.size / 2;
        int centerY = loc.y + Cell.size / 2;
        
        Polygon wing1 = new Polygon();
        wing1.addPoint(centerX - 10, centerY - 10);
        wing1.addPoint(centerX,      centerY + 2);
        wing1.addPoint(centerX - 10, centerY + 2);

        Polygon wing2 = new Polygon();
        wing2.addPoint(centerX + 10, centerY - 10);
        wing2.addPoint(centerX,      centerY + 2);
        wing2.addPoint(centerX + 10, centerY + 2);

        Polygon body = new Polygon();
        body.addPoint(centerX - 2, centerY - 5);
        body.addPoint(centerX + 2, centerY - 5);
        body.addPoint(centerX + 2, centerY + 5);
        body.addPoint(centerX - 2, centerY + 5);

        shapes.add(wing1);
        shapes.add(wing2);
        shapes.add(body);
    }
}
>>>>>>> Stashed changes
