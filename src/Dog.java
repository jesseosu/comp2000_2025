import java.awt.Color;
import java.awt.Polygon;
<<<<<<< Updated upstream
import java.util.ArrayList;

public class Dog extends Actor {
  public static final int dogMoves = 1;

  public Dog(Cell inLoc, boolean isBot) {
    super(inLoc, Color.YELLOW, isBot, dogMoves);
  }

  protected void setPoly() {
    display = new ArrayList<Polygon>();
    Polygon ear1 = new Polygon();
    ear1.addPoint(loc.x + 5, loc.y + 5);
    ear1.addPoint(loc.x + 15, loc.y + 5);
    ear1.addPoint(loc.x + 5, loc.y + 15);
    Polygon ear2 = new Polygon();
    ear2.addPoint(loc.x + 20, loc.y + 5);
    ear2.addPoint(loc.x + 30, loc.y + 5);
    ear2.addPoint(loc.x + 30, loc.y + 15);
    Polygon face = new Polygon();
    face.addPoint(loc.x + 8, loc.y + 7);
    face.addPoint(loc.x + 27, loc.y + 7);
    face.addPoint(loc.x + 27, loc.y + 25);
    face.addPoint(loc.x + 8, loc.y + 25);
    display.add(face);
    display.add(ear1);
    display.add(ear2);
  }
}
=======
import java.awt.Color;

public class Dog extends Actor {
    public Dog(Cell inLoc) {
        super(inLoc, Color.YELLOW);
    }
    
    @Override
    protected void createShapes() {
        shapes.clear();
        
        int centerX = loc.x + Cell.size / 2;
        int centerY = loc.y + Cell.size / 2;
        
        Polygon ear1 = new Polygon();
        ear1.addPoint(centerX - 12, centerY - 12);
        ear1.addPoint(centerX - 2, centerY - 12);
        ear1.addPoint(centerX - 12, centerY - 2);

        Polygon ear2 = new Polygon();
        ear2.addPoint(centerX + 12, centerY - 12);
        ear2.addPoint(centerX + 2, centerY - 12);
        ear2.addPoint(centerX + 12, centerY - 2);

        Polygon face = new Polygon();
        face.addPoint(centerX - 7, centerY - 8);
        face.addPoint(centerX + 7, centerY - 8);
        face.addPoint(centerX + 7, centerY + 8);
        face.addPoint(centerX - 7, centerY + 8);

        shapes.add(ear1);
        shapes.add(ear2);
        shapes.add(face);
    }
}
>>>>>>> Stashed changes
