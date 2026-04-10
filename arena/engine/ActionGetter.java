package arena.engine;

import arena.domain.action.Action;
import arena.domain.entity.Combatant;

public interface ActionGetter {
    Action getAction(Combatant source, BattleContext context);
}
