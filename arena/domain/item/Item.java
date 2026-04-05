package arena.domain.item;

import arena.domain.entity.Combatant;
import arena.engine.BattleContext;

public interface Item {
    void use(Combatant user, BattleContext context);
}