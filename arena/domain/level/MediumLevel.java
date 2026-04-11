package arena.domain.level;

import arena.domain.entity.Enemy;
import arena.domain.entity.Goblin;
import arena.domain.entity.Wolf;

import java.util.ArrayList;
import java.util.List;

public class MediumLevel extends Level {

    public MediumLevel() {
        super(2, "Medium", "1 Goblin + 1 Wolf | Backup: 2 Wolves");
    }

    @Override
    public List<Enemy> getInitialEnemies() {
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(new Goblin("Goblin A"));
        enemies.add(new Wolf("Wolf A"));
        return enemies;
    }

    @Override
    public List<Enemy> getBackupEnemies() {
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(new Wolf("Wolf B"));
        enemies.add(new Wolf("Wolf C"));
        return enemies;
    }
}