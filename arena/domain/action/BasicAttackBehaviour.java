package arena.domain.action;

import arena.domain.entity.Combatant;
import arena.engine.BattleContext;

public class BasicAttackBehaviour implements EnemyBehaviour {

    @Override
    public Action chooseAction(Combatant source, BattleContext context) {
        // Enemies always target the player
        Combatant player = context.getPlayer();
        return new BasicAttack(player);
    }
}