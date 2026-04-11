package arena.engine;

import arena.domain.entity.Combatant;
import arena.domain.entity.Enemy;
import arena.domain.entity.Player;
import java.util.ArrayList;
import java.util.List;

public class BattleContext {

    private final Player player;
    private final List<Enemy> activeEnemies;
    private final List<Enemy> backupEnemies;
    private int roundNumber;

    public BattleContext(Player player, List<Enemy> activeEnemies, List<Enemy> backupEnemies) {
        this.player = player;
        this.activeEnemies = new ArrayList<>(activeEnemies);
        this.backupEnemies = (backupEnemies != null) ? new ArrayList<>(backupEnemies) : new ArrayList<>();
        this.roundNumber = 1;
    }

    public Player getPlayer() {
        return player;
    }

    // Return currently alive enemies AND backup enemies
    public List<Enemy> getAllEnemies() {
        List<Enemy> all = new ArrayList<>(activeEnemies);
        all.addAll(backupEnemies);
        return all;
    }

    // Return alive enemies currently
    public List<Enemy> getAliveEnemies() {
        List<Enemy> alive = new ArrayList<>();
        for (Enemy enemy : activeEnemies) {
            if (enemy.isAlive()) {
                alive.add(enemy);
            }
        }
        return alive;
    }

    public List<Combatant> getAllCombatants() {
        List<Combatant> combatants = new ArrayList<>();
        if (player.isAlive()) {
            combatants.add(player);
        }
        for (Enemy enemy : activeEnemies) {
            if (enemy.isAlive()) {
                combatants.add(enemy);
            }
        }
        return combatants;
    }

    public boolean isPlayerDead() {
        return !player.isAlive();
    }

    public boolean isAllEnemiesDead() {
        for (Enemy enemy : activeEnemies) {
            if (enemy.isAlive()) {
                return false;
            }
        }
        return true;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public boolean hasBackupEnemies() {
        return !backupEnemies.isEmpty();
    }

    public void removeDead() {
        activeEnemies.removeIf(enemy -> !enemy.isAlive());
    }

    public boolean triggerBackupSpawn() {
        if (backupEnemies.isEmpty()) {
            return false;
        }

        activeEnemies.addAll(backupEnemies);
        backupEnemies.clear();
        return true;
    }

    public void incrementRound() {
        roundNumber++;
    }
}
