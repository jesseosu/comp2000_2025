import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
<<<<<<< Updated upstream
import java.util.List;

public abstract class Actor implements Pulse {
  Color baseColor, color;
  Cell loc;
  List<Polygon> display;
  boolean bot;
  int moves;
  int turns;
  MoveStrategy mover;

  protected Actor(Cell inLoc, Color inColor, boolean isBot, int inMoves) {
    loc = inLoc;
    baseColor = inColor;
    color = inColor;
    bot = isBot;
    moves = inMoves;
    turns = 1;
    setPoly();
  }

  public void paint(Graphics g) {
    for(Polygon p: display) {
      g.setColor(color);
      g.fillPolygon(p);
      g.setColor(Color.GRAY);
      g.drawPolygon(p);
    }
  }

  protected abstract void setPoly();

  public boolean isBot() {
    return bot;
  }

  public void setLocation(Cell inLoc) {
    loc = inLoc;
    if(loc.row % 2 == 0) {
      mover = new MoveRandomly();
    } else {
      mover = new MoveLeft();
    }
    setPoly();
  }

  public void pulsate(char phase, int percentage) {
    // Adjust color saturation according to the beat
    float[] hsbValues = new float[3];
    Color.RGBtoHSB(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), hsbValues);
    hsbValues[1] = ((float) percentage) / 100.0f;
    color = Color.getHSBColor(hsbValues[0], hsbValues[1], hsbValues[2]);
  }
}
=======
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public abstract class Actor {
    protected Cell loc;
    protected List<Polygon> shapes = new ArrayList<>();
    protected boolean active = true;
    protected Color color;

    public Actor(Cell inLoc, Color color) {
        this.loc = inLoc;
        this.color = color;
        createShapes();
    }

    protected abstract void createShapes();

    public boolean isActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public Cell getCell() {
        return loc;
    }
    
    public void setCell(Cell cell) {
        this.loc = cell;
        createShapes();
    }

    public void paint(Graphics g) {
        if (!active) return;
        
        g.setColor(color);
        for (Polygon shape : shapes) {
            g.fillPolygon(shape);
        }
        
        g.setColor(Color.BLACK);
        for (Polygon shape : shapes) {
            g.drawPolygon(shape);
        }
    }
    
    public boolean contains(Point p) {
        if (p == null || !active) return false;
        
        // Check if point is inside any of the shapes using more accurate detection
        for (Polygon shape : shapes) {
            if (shape.contains(p.x, p.y)) {
                return true;
            }
        }
        return false;
    }
}
>>>>>>> Stashed changes
