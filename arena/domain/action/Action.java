package arena.domain.action;

import arena.domain.entity.Combatant;
import arena.engine.BattleContext;

public interface Action {

    String execute(Combatant source, BattleContext context);
    
    String getName();

    // default: do nothing
    default void onSelectedBy(Combatant source) {
    }
}
