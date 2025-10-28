import java.util.List;

public class MovingCat extends MovableActor<Cat> {
    public MovingCat(Cat cat, Grid grid, List<Actor> allActors) {
        super(cat, grid, allActors);
    }
    
    @Override
    public int getMoveInterval() {
        return 30;
    }
}