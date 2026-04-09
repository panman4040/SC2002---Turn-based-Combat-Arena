package arena.domain.item;

import arena.domain.entity.Combatant;
import arena.engine.BattleContext;


public interface Item {

    String use(Combatant user, BattleContext context);

    String getName();
}
