package arena.domain.action;

import arena.domain.entity.Combatant;
import arena.engine.BattleContext;
import arena.domain.effect.DefendBuff;

public class Defend implements Action {
    @Override
    public void execute(Combatant actor, Combatant target, BattleContext context) {
        actor.addEffect(new DefendBuff());
    }
}