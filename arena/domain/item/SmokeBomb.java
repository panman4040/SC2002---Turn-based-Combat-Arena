package arena.domain.item;

import arena.domain.effect.SmokeBombInvulnerability;
import arena.domain.entity.Combatant;
import arena.engine.BattleContext;

public class SmokeBomb implements Item {

    /** No-arg constructor as per UML. */
    public SmokeBomb() {}

    @Override
    public void use(Combatant user, BattleContext context) {
        // Invulnerable for current turn + next turn = 2 turns
        user.addStatusEffect(new SmokeBombInvulnerability(2));
    }

    @Override
    public String getName() {
        return "Smoke Bomb";
    }
}