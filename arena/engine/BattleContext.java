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
        this.activeEnemies = activeEnemies;
        this.backupEnemies = backupEnemies;
        this.roundNumber = 1;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Enemy> getAliveEnemies() {
        return new ArrayList<>(activeEnemies);
    }

    // This method cleans up the activeEnemies, get rid of
    // dead enemies
    public void removeDead() {
        List<Enemy> temp = new ArrayList<>();

        // Add only alive enemies
        for (Enemy enemy : activeEnemies) {
            if (enemy.isAlive()) {
                temp.add(enemy);
            }
        }

        // Update the list of active enemies
        activeEnemies.clear();
        activeEnemies.addAll(temp);
    }

    public List<Combatant> getAllCombatants() {
        List<Combatant> combatants = new ArrayList<>();
        combatants.add(player);
        combatants.addAll(activeEnemies);

        return combatants;
    }

    public boolean triggerBackupSpawn() {
        if (backupEnemies == null || backupEnemies.isEmpty()) {
            return false; // No backup enemies exist
        }
        
        activeEnemies.addAll(backupEnemies);
        backupEnemies.clear(); // Clear backup list after spawning
        return true; // Backup spawned successfully
    }

    public boolean isPlayerDead() {
        return !player.isAlive();
    }

    // This method can now be called regardless of whether removeDead()
    // is called
    public boolean isAllEnemiesDead() {
        return activeEnemies.stream().noneMatch(Enemy::isAlive);
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void incrementRound() {
        roundNumber++;
    }
}
