import java.awt.Polygon;
import java.awt.Color;

public class Fox extends Actor {
    public Fox(Cell inLoc) {
        super(inLoc, Color.ORANGE);
    }
    
    @Override
    protected void createShapes() {
        shapes.clear();
        
        int centerX = loc.x + Cell.size / 2;
        int centerY = loc.y + Cell.size / 2;
        
        Polygon ear1 = new Polygon();
        ear1.addPoint(centerX - 10, centerY - 12);
        ear1.addPoint(centerX - 5, centerY - 2);
        ear1.addPoint(centerX - 13, centerY - 5);

        Polygon ear2 = new Polygon();
        ear2.addPoint(centerX + 10, centerY - 12);
        ear2.addPoint(centerX + 13, centerY - 5);
        ear2.addPoint(centerX + 5, centerY - 2);

        Polygon face = new Polygon();
        face.addPoint(centerX - 8, centerY);
        face.addPoint(centerX + 8, centerY);
        face.addPoint(centerX + 5, centerY + 8);
        face.addPoint(centerX, centerY + 10);
        face.addPoint(centerX - 5, centerY + 8);

        shapes.add(ear1);
        shapes.add(ear2);
        shapes.add(face);
    }
}