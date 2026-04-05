package arena.domain.action;

import arena.domain.entity.Combatant;
import arena.domain.effect.Stun;
import arena.engine.BattleContext;

public class ShieldBash extends SpecialSkill {
    @Override
    public void execute(Combatant actor, Combatant target, BattleContext context) {
        if (!isAvailable())
            return;

        int dmg = Math.max(0, actor.getAttack() - target.getDefense());
        target.takeDamage(dmg);
        target.addEffect(new Stun());

        triggerCooldown();
    }
}