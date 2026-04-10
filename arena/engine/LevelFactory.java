package arena.engine;

import java.util.ArrayList;
import java.util.List;

public class LevelFactory {

    private static final List<Level> templates = new ArrayList<>();

    static {
        templates.add(new EasyLevel());
        templates.add(new MediumLevel());
        templates.add(new HardLevel());
    }

    public static Level create(int choice) {
        switch (choice) {
            case 1: return new EasyLevel();
            case 2: return new MediumLevel();
            case 3: return new HardLevel();
            default: throw new IllegalArgumentException("Invalid difficulty: " + choice);
        }
    }

    public static void printOptions() {
        for (int i = 0; i < templates.size(); i++) {
            Level l = templates.get(i);
            System.out.printf("%d. %s – %s%n", i + 1, l.getDifficultyName(), l.getDescription());
        }
    }
}