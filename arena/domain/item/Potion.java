package arena.domain.item;

import arena.domain.entity.Combatant;
import arena.engine.BattleContext;


public class Potion implements Item {

    private static final int HEAL_AMOUNT = 100;

    public Potion() {}

    @Override
    public String use(Combatant user, BattleContext context) {
        int hpBefore = user.getHp();
        user.heal(HEAL_AMOUNT);
        int hpAfter = user.getHp();
        return String.format("HP: %d -> %d (+%d)", hpBefore, hpAfter, hpAfter - hpBefore);
    }

    @Override
    public String getName() {
        return "Potion";
    }
}