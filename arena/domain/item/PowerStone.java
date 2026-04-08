package arena.domain.item;

import arena.domain.entity.Combatant;
import arena.engine.BattleContext;

public class PowerStone implements Item {

    public PowerStone() {}

    @Override
    public void use(Combatant user, BattleContext context) {
        // Trigger the special skill effect once without affecting cooldown
        user.triggerSpecial(context);
    }

    @Override
    public String getName() {
        return "Power Stone";
    }
}
