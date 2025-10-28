import java.awt.Color;
import java.awt.Polygon;
<<<<<<< Updated upstream
import java.util.ArrayList;

public class Cat extends Actor {
  public static final int catMoves = 2;

  public Cat(Cell inLoc, boolean isBot) {
    super(inLoc, Color.BLUE, isBot, catMoves);
  }

  protected void setPoly() {
    display = new ArrayList<Polygon>();
    Polygon ear1 = new Polygon();
    ear1.addPoint(loc.x + 11, loc.y + 5);
    ear1.addPoint(loc.x + 15, loc.y + 15);
    ear1.addPoint(loc.x + 7, loc.y + 15);
    Polygon ear2 = new Polygon();
    ear2.addPoint(loc.x + 22, loc.y + 5);
    ear2.addPoint(loc.x + 26, loc.y + 15);
    ear2.addPoint(loc.x + 18, loc.y + 15);
    Polygon face = new Polygon();
    face.addPoint(loc.x + 5, loc.y + 15);
    face.addPoint(loc.x + 29, loc.y + 15);
    face.addPoint(loc.x + 17, loc.y + 30);
    display.add(face);
    display.add(ear1);
    display.add(ear2);
  }
}
=======
import java.awt.Color;

public class Cat extends Actor {
    public Cat(Cell inLoc) {
        super(inLoc, Color.BLUE);
    }
    
    @Override
    protected void createShapes() {
        shapes.clear();
        
        int centerX = loc.x + Cell.size / 2;
        int centerY = loc.y + Cell.size / 2;
        
        Polygon ear1 = new Polygon();
        ear1.addPoint(centerX - 8, centerY - 12);
        ear1.addPoint(centerX - 4, centerY - 2);
        ear1.addPoint(centerX - 12, centerY - 2);

        Polygon ear2 = new Polygon();
        ear2.addPoint(centerX + 8, centerY - 12);
        ear2.addPoint(centerX + 12, centerY - 2);
        ear2.addPoint(centerX + 4, centerY - 2);

        Polygon face = new Polygon();
        face.addPoint(centerX - 10, centerY);
        face.addPoint(centerX + 10, centerY);
        face.addPoint(centerX, centerY + 10);

        shapes.add(ear1);
        shapes.add(ear2);
        shapes.add(face);
    }
}
>>>>>>> Stashed changes
