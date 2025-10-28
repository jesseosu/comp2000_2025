import java.util.List;

public class MovingWolf extends MovableActor<Wolf> {
    public MovingWolf(Wolf wolf, Grid grid, List<Actor> allActors) {
        super(wolf, grid, allActors);
    }
    
    @Override
    public int getMoveInterval() {
        return 25;
    }
}