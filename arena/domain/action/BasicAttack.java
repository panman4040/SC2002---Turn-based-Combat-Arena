package arena.domain.action;

import arena.domain.entity.Combatant;
import arena.engine.BattleContext;

public class BasicAttack implements Action {
    @Override
    public void execute(Combatant actor, Combatant target, BattleContext context) {
        int dmg = Math.max(0, actor.getAttack() - target.getDefense());
        target.takeDamage(dmg);
    }
}