package arena.domain.action;

import arena.domain.entity.Combatant;
import arena.engine.BattleContext;
import arena.ui.GameUI;

public class BasicAttackBehaviour implements EnemyBehaviour {

    @Override
    public Action chooseAction(BattleContext context, GameUI ui) {
        // Enemies always target the player
        Combatant player = context.getPlayer();
        return new BasicAttack(player);
    }
}