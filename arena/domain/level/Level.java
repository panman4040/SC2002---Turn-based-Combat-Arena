package arena.domain.level;

import arena.domain.entity.Enemy;
import java.util.List;

public abstract class Level {
    private final int levelNumber;
    private final String difficultyName;
    private final String description;

    protected Level(int levelNumber, String difficultyName, String description) {
        this.levelNumber = levelNumber;
        this.difficultyName = difficultyName;
        this.description = description;
    }

    public int getLevelNumber() { return levelNumber; }
    public String getDifficultyName() { return difficultyName; }
    public String getDescription() { return description; }

    public abstract List<Enemy> getInitialEnemies();
    public abstract List<Enemy> getBackupEnemies();
}