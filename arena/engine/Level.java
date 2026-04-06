package arena.engine;


import java.util.ArrayList;
import java.util.List;

import arena.domain.entity.Enemy;
import arena.domain.entity.Goblin;
import arena.domain.entity.Wolf;

public class Level {
    private int levelNumber;
    private String difficultyName;
    private List<Enemy> initialEnemies;
    private List<Enemy> backupEnemies;

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

        initialEnemies.add(new Goblin());
        initialEnemies.add(new Wolf());

        return new Level(1, "Easy", initialEnemies, backupEnemies);
    }

    public static Level createMedium() {
        List<Enemy> initialEnemies = new ArrayList<>();
        List<Enemy> backupEnemies = new ArrayList<>();

        initialEnemies.add(new Goblin());
        initialEnemies.add(new Wolf());

        backupEnemies.add(new Goblin());

        return new Level(2, "Medium", initialEnemies, backupEnemies);
    }

    public static Level createHard() {
        List<Enemy> initialEnemies = new ArrayList<>();
        List<Enemy> backupEnemies = new ArrayList<>();

        initialEnemies.add(new Goblin());
        initialEnemies.add(new Goblin());

        backupEnemies.add(new Wolf());
        backupEnemies.add(new Wolf());

        return new Level(3, "Hard", initialEnemies, backupEnemies);
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public String getDifficultyName() {
        return difficultyName;
    }

    public List<Enemy> getInitialEnemies() {
        return initialEnemies;
    }

    public List<Enemy> getBackupEnemies() {
        return backupEnemies;
    }
}

