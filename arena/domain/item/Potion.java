package arena.domain.item;

import arena.domain.entity.Combatant;
import arena.engine.BattleContext;


public class Potion implements Item {

    private static final int HEAL_AMOUNT = 100;

    public Potion() {}

    @Override
    public void use(Combatant user, BattleContext context) {
        user.heal(HEAL_AMOUNT);
    }

    @Override
    public String getName() {
        return "Potion";
    }
}