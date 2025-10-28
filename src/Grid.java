import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
<<<<<<< Updated upstream
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Grid {
  Cell[][] cells = new Cell[20][20];
  
  public Grid() {
    for(int i=0; i<cells.length; i++) {
      for(int j=0; j<cells[i].length; j++) {
        cells[i][j] = new Cell(colToLabel(i), j, 10+Cell.size*i, 10+Cell.size*j);
      }
    }
  }

  private char colToLabel(int col) {
    return (char) (col + Character.valueOf('A'));
  }

  private int labelToCol(char col) {
    return (int) (col - Character.valueOf('A'));
  }

  public void paint(Graphics g, Point mousePos) {
    for(int i=0; i<cells.length; i++) {
      for(int j=0; j<cells[i].length; j++) {
        cells[i][j].paint(g, mousePos);
      }
    }
  }

  public Optional<Cell> cellAtColRow(int c, int r) {
    if(c >= 0 && c < cells.length && r >=0 && r < cells[c].length) {
      return Optional.of(cells[c][r]);
    } else {
      return Optional.empty();
    }
  }

  public Optional<Cell> cellAtColRow(char c, int r) {
    return cellAtColRow(labelToCol(c), r);
  }

  public Optional<Cell> cellAtPoint(Point p) {
    for(int i=0; i < cells.length; i++) {
      for(int j=0; j < cells[i].length; j++) {
        if(cells[i][j].contains(p)) {
          return Optional.of(cells[i][j]);
        }
      }
    }
    return Optional.empty();
  }

  public List<Cell> getRadius(Cell from, int size) {
    int i = labelToCol(from.col);
    int j = from.row;
    Set<Cell> inRadius = new HashSet<Cell>();
    if (size > 0) {
        cellAtColRow(colToLabel(i), j - 1).ifPresent(inRadius::add);
        cellAtColRow(colToLabel(i), j + 1).ifPresent(inRadius::add);
        cellAtColRow(colToLabel(i - 1), j).ifPresent(inRadius::add);
        cellAtColRow(colToLabel(i + 1), j).ifPresent(inRadius::add);
    }

    for(Cell c: inRadius.toArray(new Cell[0])) {
        inRadius.addAll(getRadius(c, size - 1));
    }
    return new ArrayList<Cell>(inRadius);
  }

  public void paintOverlay(Graphics g, List<Cell> cells, Color color) {
    g.setColor(color);
    for(Cell c: cells) {
      g.fillRect(c.x+2, c.y+2, c.width-4, c.height-4);
    }
  }
}
=======
import java.util.List;
import java.util.Random;

public class Grid {
    Cell[][] cells = new Cell[20][20];
    private Random random = new Random();

    public Grid() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(10 + Cell.size * i, 10 + Cell.size * j);
            }
        }
    }

    public void paint(Graphics g, Point mousePos) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j].paint(g, mousePos);
            }
        }
    }
    
    public Cell getRandomCell() {
        int x = random.nextInt(cells.length);
        int y = random.nextInt(cells[0].length);
        return cells[x][y];
    }
    
    public Cell getRandomAdjacentCell(Cell currentCell, List<Actor> actors) {
        // Find the grid coordinates of the current cell
        int currentX = -1;
        int currentY = -1;
        
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j].x == currentCell.x && cells[i][j].y == currentCell.y) {
                    currentX = i;
                    currentY = j;
                    break;
                }
            }
            if (currentX != -1) break;
        }
        
        if (currentX == -1) {
            return getRandomCell(); // Fallback if cell not found
        }
        
        // Get all valid adjacent cells (8 directions) that are not occupied
        List<Cell> validAdjacentCells = new ArrayList<>();
        
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue; // Skip the current cell
                
                int newX = currentX + dx;
                int newY = currentY + dy;
                
                if (newX >= 0 && newX < cells.length && newY >= 0 && newY < cells[0].length) {
                    Cell candidateCell = cells[newX][newY];
                    
                    // Check if cell is not occupied by any other active character
                    boolean isOccupied = false;
                    for (Actor actor : actors) {
                        if (actor.isActive() && 
                            actor.getCell().x == candidateCell.x && 
                            actor.getCell().y == candidateCell.y) {
                            isOccupied = true;
                            break;
                        }
                    }
                    
                    if (!isOccupied) {
                        validAdjacentCells.add(candidateCell);
                    }
                }
            }
        }
        
        if (validAdjacentCells.isEmpty()) {
            return currentCell; // Stay in place if no valid adjacent cells
        }
        
        // Return a random valid adjacent cell
        return validAdjacentCells.get(random.nextInt(validAdjacentCells.size()));
    }
}
>>>>>>> Stashed changes
