import java.awt.Color;

public enum TerrainType {
    GRASS(new Color(144, 238, 144)),      // Light green
    BUSH(new Color(34, 139, 34)),         // Forest green
    TREE(new Color(0, 100, 0)),           // Dark green
    WATER(new Color(64, 164, 223)),       // Blue water
    ROCK(new Color(169, 169, 169)),       // Gray
    FLOWERS(new Color(255, 192, 203));    // Pink

    private final Color color;

    TerrainType(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}