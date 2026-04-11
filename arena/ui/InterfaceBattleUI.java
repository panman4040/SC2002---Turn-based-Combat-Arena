package arena.ui;

import arena.domain.action.Action;
import arena.domain.entity.Combatant;
import arena.domain.entity.Player;
import arena.engine.BattleContext;
import java.util.List;

public interface InterfaceBattleUI extends InterfaceDisplayUI {
    void displayLevelSummary(BattleContext context);
    void displayRoundStart(BattleContext context);
    String formatCombatantStats(Combatant combatant);
    void displayBattleState(BattleContext context);
    void displayTurnOrder(List<Combatant> combatants);
    Action getPlayerAction(Player player, BattleContext context);
}