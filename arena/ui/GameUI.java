package arena.ui;

import arena.domain.action.Action;
import arena.domain.entity.Combatant;
import arena.domain.entity.Player;
import arena.engine.BattleContext;
import arena.engine.Level;
import java.util.List;

public interface GameUI {
    void displayMessage(String message);
    void displayBattleState(BattleContext context);
    void displayTurnOrder(List<Combatant> combatants);
    Player choosePlayer();
    Action getPlayerAction(Player player, BattleContext context);
    Level chooseDifficulty();
}