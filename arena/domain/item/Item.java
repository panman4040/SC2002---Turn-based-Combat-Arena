package arena.domain.item;

import arena.domain.entity.Combatant;
import arena.engine.BattleContext;


public interface Item {

    String use(Combatant user, BattleContext context);
    default boolean requireTarget(Combatant user) {
        return false;
    }

    default void setTarget(Combatant target) { /* no-op by default */}

    String getName();
}
