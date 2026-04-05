package arena.domain.item;

import arena.domain.entity.Combatant;
import arena.domain.effect.SmokeBombInvulnerability;
import arena.engine.BattleContext;

public class SmokeBomb implements Item {
    @Override
    public void use(Combatant user, BattleContext context) {
        user.addEffect(new SmokeBombInvulnerability());
    }
}
