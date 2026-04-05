package arena.domain.item;

import arena.domain.effect.SmokeBombInvulnerability;
import arena.domain.entity.Combatant;
import arena.engine.BattleContext;

public class SmokeBomb implements Item {

    /** No-arg constructor as per UML. */
    public SmokeBomb() {}

    @Override
    public void use(Combatant user, BattleContext context) {
        user.addStatusEffect(new SmokeBombInvulnerability());
    }

    @Override
    public String getName() {
        return "Smoke Bomb";
    }
}