package arena.domain.item;

import arena.domain.entity.Combatant;
import arena.engine.BattleContext;

public class Potion implements Item {
    @Override
    public void use(Combatant user, BattleContext context) {
        user.heal(100);
    }
}