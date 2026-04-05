package arena.domain.action;

import arena.domain.entity.Combatant;
import arena.engine.BattleContext;

public interface Action {
    void execute(Combatant actor, Combatant target, BattleContext context);
}