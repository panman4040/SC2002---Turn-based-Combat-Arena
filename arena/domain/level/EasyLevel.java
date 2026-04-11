package arena.domain.level;

import arena.domain.entity.Enemy;
import arena.domain.entity.Goblin;

import java.util.ArrayList;
import java.util.List;

public class EasyLevel extends Level {

    public EasyLevel() {
        super(1, "Easy", "3 Goblins");
    }

    @Override
    public List<Enemy> getInitialEnemies() {
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(new Goblin("Goblin A"));
        enemies.add(new Goblin("Goblin B"));
        enemies.add(new Goblin("Goblin C"));
        return enemies;
    }

    @Override
    public List<Enemy> getBackupEnemies() {
        return new ArrayList<>();
    }
}