package arena.domain.item;

import arena.domain.effect.BerserkerBrewEffect;
import arena.domain.entity.Combatant;
import arena.engine.BattleContext;

public class BerserkerBrew implements Item {
    private static final int DURATION = 3;
    private static final int ATTACK_BONUS = 20;
    private static final int DEFENSE_DROP = 10;

    public BerserkerBrew() {}

    @Override
    public String use(Combatant user, BattleContext context) {
        user.addStatusEffect(new BerserkerBrewEffect(DURATION, ATTACK_BONUS, DEFENSE_DROP));

        return String.format("%s is intoxicated for %d turns: +%d attack, -%d defense!",
            user.getName(), DURATION, ATTACK_BONUS, DEFENSE_DROP);
    }

    @Override
    public String getName() {
        return "Berserker Brew";
    }
}

