package arena.domain.action;

import arena.domain.entity.Combatant;
import arena.engine.BattleContext;

public interface EnemyBehaviour {
    Action chooseAction(Combatant source, BattleContext context);
}