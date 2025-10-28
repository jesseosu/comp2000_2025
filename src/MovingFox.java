import java.util.List;

public class MovingFox extends MovableActor<Fox> {
    public MovingFox(Fox fox, Grid grid, List<Actor> allActors) {
        super(fox, grid, allActors);
    }
    
    @Override
    public int getMoveInterval() {
        return 35;
    }
}