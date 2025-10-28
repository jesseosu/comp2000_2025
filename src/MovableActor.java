import java.util.List;
import java.util.Random;

public abstract class MovableActor<T extends Actor> implements IMovable {
    protected T actor;
    protected Grid grid;
    protected List<Actor> allActors;
    protected Random random;
    protected int moveCounter;
    
    public MovableActor(T actor, Grid grid, List<Actor> allActors) {
        this.actor = actor;
        this.grid = grid;
        this.allActors = allActors;
        this.random = new Random();
        this.moveCounter = 0;
    }
    
    public T getActor() {
        return actor;
    }
    
    @Override
    public void move() {
        moveCounter++;
        if (moveCounter >= getMoveInterval()) {
            moveCounter = 0;
            performMove();
        }
    }
    
    protected void performMove() {
        if (getActor().isActive()) {
            Cell newCell = grid.getRandomAdjacentCell(getActor().getCell(), allActors);
            getActor().setCell(newCell);
        }
    }
    
    @Override
    public abstract int getMoveInterval();
}