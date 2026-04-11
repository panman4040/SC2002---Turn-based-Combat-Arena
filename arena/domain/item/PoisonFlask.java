package arena.domain.item;

import arena.domain.effect.PoisonEffect;
import arena.domain.entity.Combatant;
import arena.engine.BattleContext;

public class PoisonFlask implements Item {
    private Combatant target;
    private static final int DURATION = 3;
    private static final int POISON_DAMAGE = 5;

    public PoisonFlask() {}

    @Override
    public boolean requireTarget(Combatant user) {
        return true;
    }

    @Override
    public void setTarget(Combatant target) {
        this.target = target;
    }

    @Override
    public String use(Combatant user, BattleContext context) {
        target.addStatusEffect(new PoisonEffect(DURATION, POISON_DAMAGE));

        return String.format("%s is poisoned for %d turns, taking %d damage each turn!",
            target.getName(), DURATION, POISON_DAMAGE);
    }

    @Override
    public String getName() {
        return "Poison Flask";
    }
}

