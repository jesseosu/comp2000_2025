import java.util.List;

public class MovingDog extends MovableActor<Dog> {
    public MovingDog(Dog dog, Grid grid, List<Actor> allActors) {
        super(dog, grid, allActors);
    }
    
    @Override
    public int getMoveInterval() {
        return 45;
    }
}