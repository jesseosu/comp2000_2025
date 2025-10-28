import java.awt.Polygon;
import java.awt.Color;

public class Wolf extends Actor {
    public Wolf(Cell inLoc) {
        super(inLoc, Color.GRAY);
    }
    
    @Override
    protected void createShapes() {
        shapes.clear();
        
        int centerX = loc.x + Cell.size / 2;
        int centerY = loc.y + Cell.size / 2;
        
        Polygon ear1 = new Polygon();
        ear1.addPoint(centerX - 11, centerY - 12);
        ear1.addPoint(centerX - 6, centerY - 5);
        ear1.addPoint(centerX - 13, centerY - 7);

        Polygon ear2 = new Polygon();
        ear2.addPoint(centerX + 11, centerY - 12);
        ear2.addPoint(centerX + 13, centerY - 7);
        ear2.addPoint(centerX + 6, centerY - 5);

        Polygon head = new Polygon();
        head.addPoint(centerX - 9, centerY - 5);
        head.addPoint(centerX + 9, centerY - 5);
        head.addPoint(centerX + 9, centerY + 8);
        head.addPoint(centerX, centerY + 10);
        head.addPoint(centerX - 9, centerY + 8);

        shapes.add(ear1);
        shapes.add(ear2);
        shapes.add(head);
    }
}