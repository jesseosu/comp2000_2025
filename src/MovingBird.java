import java.util.List;

public class MovingBird extends MovableActor<Bird> {
    public MovingBird(Bird bird, Grid grid, List<Actor> allActors) {
        super(bird, grid, allActors);
    }
    
    @Override
    public int getMoveInterval() {
        return 60;
    }
}