package arena.engine;

public class LevelFactory {
    public static Level createLevel(LevelType type) {
        return switch (type) {
            case EASY -> new EasyLevel();
            case MEDIUM -> new MediumLevel();
            case HARD -> new HardLevel();
        };
    }
}