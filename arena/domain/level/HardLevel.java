package arena.domain.level;

import arena.domain.entity.Enemy;
import arena.domain.entity.Goblin;
import arena.domain.entity.Wolf;

import java.util.ArrayList;
import java.util.List;

public class HardLevel extends Level {

    public HardLevel() {
        super(3, "Hard", "2 Goblins | Backup: 1 Goblin + 2 Wolves");
    }

    @Override
    public List<Enemy> getInitialEnemies() {
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(new Goblin("Goblin A"));
        enemies.add(new Goblin("Goblin B"));
        return enemies;
    }

    @Override
    public List<Enemy> getBackupEnemies() {
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(new Goblin("Goblin C"));
        enemies.add(new Wolf("Wolf A"));
        enemies.add(new Wolf("Wolf B"));
        return enemies;
    }
}