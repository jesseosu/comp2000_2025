import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Stage {
<<<<<<< Updated upstream
  Grid grid;
  List<Actor> listOfPlayers;
  List<Cell> cellOverlay;
  Optional<Actor> playerInAction;

  GameState currentState;
  Beat beat;

  public Stage() {
    grid = new Grid();
    listOfPlayers = new ArrayList<Actor>();
    cellOverlay = new ArrayList<Cell>();
    playerInAction = Optional.empty();
    currentState = new ChoosingActor();
    beat = new AnimationBeat();
  }

  public void addPlayer(Actor player) {
    listOfPlayers.add(player);
    if(player.isBot()) {
      beat.punchIn(player);
    }
  }

  public void paint(Graphics g, Point mouseLoc) {
    // do we have bot moves to make?
    currentState.paint(g, this);
    grid.paint(g, mouseLoc);
    // Blue cell selection overlay with 50% transparency
    grid.paintOverlay(g, cellOverlay, new Color(0f, 0f, 1f, 0.5f));

    beat.ticktock();
    for(Actor player: listOfPlayers) {
      player.paint(g);
    }
    draw_sidepanel(g, mouseLoc);
  }

  private void draw_sidepanel(Graphics g, Point mouseLoc) {
    // lots of magic numbers here
    // they are used to calculate the coordinates of where to draw on the information panel
    final int hTab = 10;
    final int blockVT = 35;
    final int margin = 21*blockVT;
    int yLoc = 20;

    // state display
    g.setColor(Color.DARK_GRAY);
    g.drawString(currentState.toString(), margin, yLoc);
    yLoc = yLoc + blockVT;
    Optional<Cell> underMouse = grid.cellAtPoint(mouseLoc);
    if(underMouse.isPresent()) {
      Cell hoverCell = underMouse.get();
      g.setColor(Color.DARK_GRAY);
      String coord = String.valueOf(hoverCell.col) + String.valueOf(hoverCell.row);
      g.drawString(coord, margin, yLoc);
    }

    // agent display
    final int vTab = 15;
    final int labelIndent = margin + hTab;
    final int valueIndent = margin + 3*blockVT;
    yLoc = yLoc + 2*blockVT;
    for(int i = 0; i < listOfPlayers.size(); i++){
      Actor a = listOfPlayers.get(i);
      yLoc = yLoc + 2*blockVT;
      g.drawString(a.getClass().getName(), margin, yLoc);
      g.drawString("location:", labelIndent, yLoc+vTab);
      g.drawString(Character.toString(a.loc.col) + Integer.toString(a.loc.row), valueIndent, yLoc+vTab);
      g.drawString("player type:", labelIndent, yLoc+2*vTab);
      g.drawString(a.isBot() ? "Bot" : "Human", valueIndent, yLoc+2*vTab);
      if(a.isBot() && a.mover != null) {
        g.drawString("mover:", labelIndent, yLoc+3*vTab);
        g.drawString(a.mover.getClass().getName(), valueIndent, yLoc+3*vTab);
      }
    }    
  }

  public List<Cell> getClearRadius(Cell from, int size) {
    List<Cell> init = grid.getRadius(from, size);
    for(Actor player: listOfPlayers) {
      init.remove(player.loc);
    }
    return init;
  }

  public void mouseClicked(int x, int y) {
    currentState.mouseClick(x, y, this);
  }
}
=======
    final Grid grid;
    final List<Actor> actors = new ArrayList<>();
    final List<IMovable> movableActors = new ArrayList<>();
    private GameState gameState = GameState.PLAYING;
    private int activeActorsCount;
    
    public Stage() {
        this.grid = new Grid();
        this.activeActorsCount = 5;

        // Create and add unique characters with polygon shapes
        Cat cat = new Cat(grid.getRandomCell());
        Dog dog = new Dog(grid.getRandomCell());
        Bird bird = new Bird(grid.getRandomCell());
        Fox fox = new Fox(grid.getRandomCell());
        Wolf wolf = new Wolf(grid.getRandomCell());
        
        actors.add(cat);
        actors.add(dog);
        actors.add(bird);
        actors.add(fox);
        actors.add(wolf);
        
        // Create movable actors
        movableActors.add(new MovingCat(cat, grid, actors));
        movableActors.add(new MovingDog(dog, grid, actors));
        movableActors.add(new MovingBird(bird, grid, actors));
        movableActors.add(new MovingFox(fox, grid, actors));
        movableActors.add(new MovingWolf(wolf, grid, actors));
        
        preventInitialOverlap();
    }
    
    private void preventInitialOverlap() {
        for (int i = 0; i < actors.size(); i++) {
            for (int j = i + 1; j < actors.size(); j++) {
                Actor a1 = actors.get(i);
                Actor a2 = actors.get(j);
                if (a1.getCell().x == a2.getCell().x && a1.getCell().y == a2.getCell().y) {
                    a2.setCell(grid.getRandomCell());
                    j--; // Check this actor again
                }
            }
        }
    }
    
    public GameState getGameState() {
        return gameState;
    }
    
    public void handleClick(Point mousePos) {
        if (gameState != GameState.PLAYING || mousePos == null) return;
        
        System.out.println("Click at: " + mousePos.x + ", " + mousePos.y); // Debug
        
        for (Actor actor : actors) {
            boolean contains = actor.contains(mousePos);
            System.out.println("Actor at " + actor.getCell().x + "," + actor.getCell().y + 
                             " contains click: " + contains); // Debug
                             
            if (contains && actor.isActive()) {
                actor.setActive(false);
                activeActorsCount--;
                System.out.println("Actor removed! Remaining: " + activeActorsCount); // Debug
                
                if (activeActorsCount == 0) {
                    gameState = GameState.GAME_OVER_WIN;
                }
                break;
            }
        }
    }
    
    public void update() {
        if (gameState != GameState.PLAYING) return;
        
        for (IMovable movable : movableActors) {
            movable.move();
        }
    }
    
    public void paint(Graphics g, Point mousePos) {
        grid.paint(g, mousePos);
        
        for (Actor a : actors) {
            a.paint(g);
        }
        
        if (gameState == GameState.GAME_OVER_WIN) {
            g.setColor(new Color(240, 240, 240, 200));
            g.fillRect(250, 320, 220, 100);
            g.setColor(Color.BLACK);
            g.drawRect(250, 320, 220, 100);
            g.drawString("GAME OVER - YOU WIN!", 270, 350);
        }
        
        g.drawString("Active characters: " + activeActorsCount + "/5", 10, 20);
        
        // Debug: show mouse position
        if (mousePos != null) {
            g.setColor(Color.RED);
            g.drawString("Mouse: " + mousePos.x + ", " + mousePos.y, 10, 40);
        }
    }
}
>>>>>>> Stashed changes
