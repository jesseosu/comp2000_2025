import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

<<<<<<< Updated upstream
public class Cell extends Rectangle {
  static int size = 35;
  char col;
  int row;

  public Cell(char inCol, int inRow, int x, int y) {
    super(x, y, size, size);
    col = inCol;
    row = inRow;
  }
=======
public class Cell {
    int x;
    int y;
    static int size = 35;

    private TerrainType terrain;
    private Random random = new Random();
>>>>>>> Stashed changes

    public Cell(int inX, int inY) {
        x = inX;
        y = inY;
        // Random terrain for each cell
        double rand = random.nextDouble();
        if (rand < 0.6) this.terrain = TerrainType.GRASS;
        else if (rand < 0.7) this.terrain = TerrainType.BUSH;
        else if (rand < 0.8) this.terrain = TerrainType.TREE;
        else if (rand < 0.85) this.terrain = TerrainType.WATER;
        else if (rand < 0.9) this.terrain = TerrainType.ROCK;
        else this.terrain = TerrainType.FLOWERS;
    }

<<<<<<< Updated upstream
  @Override
  public boolean contains(Point p) {
    if(p != null) {
      return super.contains(p);
    } else {
      return false;
    }
  }

  public int leftOfComparison(Cell c) {
    return Integer.compare(col, c.col);
  }

  public int aboveComparison(Cell c) {
    return Integer.compare(row, c.row);
  }
}
=======
    public void setTerrain(TerrainType terrain) { 
        this.terrain = terrain; 
    }
    
    public TerrainType getTerrain() { 
        return terrain; 
    }

    public void paint(Graphics g, Point mousePos) {
        // Draw terrain background
        g.setColor(terrain.getColor());
        g.fillRect(x, y, size, size);
        
        // Add texture based on terrain type
        addTerrainTexture(g);
        
        // Highlight if mouse is over
        if (contains(mousePos)) {
            g.setColor(new Color(255, 255, 255, 100)); // Semi-transparent white
            g.fillRect(x, y, size, size);
        }
        
        // Draw cell border
        g.setColor(new Color(100, 100, 100, 100));
        g.drawRect(x, y, size, size);
    }
    
    private void addTerrainTexture(Graphics g) {
        switch (terrain) {
            case GRASS:
                g.setColor(new Color(100, 200, 100));
                for (int i = 0; i < 3; i++) {
                    int x1 = x + 5 + i * 10;
                    g.drawLine(x1, y + size - 5, x1, y + size - 15);
                }
                break;
            case BUSH:
                g.setColor(new Color(20, 80, 20));
                g.fillOval(x + 5, y + 5, 10, 10);
                g.fillOval(x + 20, y + 8, 8, 8);
                g.fillOval(x + 10, y + 18, 12, 12);
                break;
            case TREE:
                g.setColor(new Color(101, 67, 33)); // Brown trunk
                g.fillRect(x + 15, y + 20, 5, 15);
                g.setColor(new Color(0, 80, 0)); // Dark green leaves
                g.fillOval(x + 5, y + 5, 25, 20);
                break;
            case WATER:
                g.setColor(new Color(100, 180, 255));
                for (int i = 0; i < 2; i++) {
                    g.drawArc(x + 5 + i * 15, y + 20, 10, 5, 0, 180);
                }
                break;
            case ROCK:
                g.setColor(new Color(120, 120, 120));
                int[] xPoints = {x + 10, x + 25, x + 20, x + 5};
                int[] yPoints = {y + 10, y + 15, y + 25, y + 20};
                g.fillPolygon(xPoints, yPoints, 4);
                break;
            case FLOWERS:
                g.setColor(Color.YELLOW);
                g.fillOval(x + 10, y + 10, 6, 6);
                g.setColor(Color.PINK);
                g.fillOval(x + 20, y + 15, 5, 5);
                g.setColor(Color.WHITE);
                g.fillOval(x + 15, y + 22, 4, 4);
                break;
        }
    }

    public boolean contains(Point p) {
        if (p == null) return false;
        return x < p.x && x + size > p.x && y < p.y && y + size > p.y;
    }
}
>>>>>>> Stashed changes
