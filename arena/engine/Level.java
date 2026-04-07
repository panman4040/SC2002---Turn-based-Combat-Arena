package arena.engine;


import arena.domain.entity.Enemy;
import arena.domain.entity.Goblin;
import arena.domain.entity.Wolf;
import java.util.ArrayList;
import java.util.List;

public class Level {
    private final int levelNumber;
    private final String difficultyName;
    private final List<Enemy> initialEnemies;
    private final List<Enemy> backupEnemies;

    private Level(int levelNumber, String difficultyName,
                  List<Enemy> initialEnemies, List<Enemy> backupEnemies) {
        this.levelNumber = levelNumber;
        this.difficultyName = difficultyName;
        this.initialEnemies = initialEnemies;
        this.backupEnemies = backupEnemies;
    }

    public static Level createEasy() {
        List<Enemy> initialEnemies = new ArrayList<>();
        List<Enemy> backupEnemies = new ArrayList<>();

        initialEnemies.add(new Goblin("Goblin A"));
        initialEnemies.add(new Goblin("Goblin B"));
        initialEnemies.add(new Goblin("Goblin C"));

        return new Level(1, "Easy", initialEnemies, backupEnemies);
    }

    public static Level createMedium() {
        List<Enemy> initialEnemies = new ArrayList<>();
        List<Enemy> backupEnemies = new ArrayList<>();

        initialEnemies.add(new Goblin("Goblin A"));
        initialEnemies.add(new Wolf("Wolf A"));

        backupEnemies.add(new Wolf("Wolf B"));
        backupEnemies.add(new Wolf("Wolf C"));

        return new Level(2, "Medium", initialEnemies, backupEnemies);
    }

    public static Level createHard() {
        List<Enemy> initialEnemies = new ArrayList<>();
        List<Enemy> backupEnemies = new ArrayList<>();

        initialEnemies.add(new Goblin("Goblin A"));
        initialEnemies.add(new Goblin("Goblin B"));

        backupEnemies.add(new Goblin("Goblin C"));
        backupEnemies.add(new Wolf("Wolf A"));
        backupEnemies.add(new Wolf("Wolf B"));

        return new Level(3, "Hard", initialEnemies, backupEnemies);
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public String getDifficultyName() {
        return difficultyName;
    }

    // Return COPY instead of original
    public List<Enemy> getInitialEnemies() {
        return new ArrayList<>(initialEnemies);
    }

    // Return COPY instead of original
    public List<Enemy> getBackupEnemies() {
        return new ArrayList<>(backupEnemies);
    }
}

