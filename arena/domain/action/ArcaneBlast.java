package arena.domain.action;

import arena.domain.entity.*;
import arena.engine.BattleContext;

public class ArcaneBlast extends SpecialSkill {
    @Override
    public void execute(Combatant actor, Combatant target, BattleContext context) {
        if (!isAvailable())
            return;

        for (Combatant enemy : context.getEnemies()) {
            if (!enemy.isAlive())
                continue;

            int dmg = Math.max(0, actor.getAttack() - enemy.getDefense());
            enemy.takeDamage(dmg);

            if (!enemy.isAlive() && actor instanceof Wizard) {
                ((Wizard) actor).increaseAttack(10);
            }
        }

        triggerCooldown();
    }
}