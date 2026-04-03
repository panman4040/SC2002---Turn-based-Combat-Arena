package arena.ui;

import arena.domain.entity.Player;
import arena.domain.action.Action;
import arena.engine.BattleContext;
import arena.engine.Level;

public interface GameUI {
    void displayMessage(String message);
    void displayBattleState(BattleContext context);
    Action getPlayerAction(Player player, BattleContext context);
    Level chooseDifficulty();
}