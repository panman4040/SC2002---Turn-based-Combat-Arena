package arena.domain.item;

import arena.domain.effect.SmokeBombInvulnerability;
import arena.domain.entity.Combatant;
import arena.engine.BattleContext;

public class SmokeBomb implements Item {
    
    public SmokeBomb() {}

    @Override
    public String use(Combatant user, BattleContext context) {
        // Invulnerable for current turn + next turn = 2 turns
        user.addStatusEffect(new SmokeBombInvulnerability(2));
        return "Enemy attacks deal 0 damage this turn + next";
    }

    @Override
    public String getName() {
        return "Smoke Bomb";
    }
}