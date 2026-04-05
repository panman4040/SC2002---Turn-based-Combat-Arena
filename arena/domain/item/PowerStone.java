package arena.domain.item;

import arena.domain.effect.ArcaneBlastBuff;
import arena.domain.entity.Combatant;
import arena.engine.BattleContext;

public class PowerStone implements Item {

    private static final int ATTACK_BONUS = 25;

    public PowerStone() {}

    @Override
    public void use(Combatant user, BattleContext context) {
        user.addStatusEffect(new ArcaneBlastBuff(ATTACK_BONUS));
    }

    @Override
    public String getName() {
        return "Power Stone";
    }
}
