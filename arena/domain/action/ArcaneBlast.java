package arena.domain.action;

import arena.domain.effect.ArcaneBlastBuff;
import arena.domain.entity.Combatant;
import arena.domain.entity.Enemy;
import arena.engine.BattleContext;


public class ArcaneBlast extends SpecialSkill {

    private static final int ATTACK_BONUS_PER_KILL = 10;
    private int cumulativeAttackBonus = 0;

    public ArcaneBlast() {
        super("Arcane Blast", 3); // cooldown 3 turns including current
    }

    @Override
    public String execute(Combatant user, BattleContext context) {
        StringBuilder result = new StringBuilder();
        result.append(String.format("%s -> Arcane Blast -> All Enemies: ", user.getName()));
        result.append('\n');

        for (Enemy enemy: context.getAliveEnemies()) {
            // Re-fetch ATK each iteration so kills within the same blast stack
            int atk = user.getEffectiveAttack();
            int def = enemy.getEffectiveDefense();
            int damage = Math.max(0, atk - def);

            int hpBefore = enemy.getHp();
            enemy.takeDamage(damage);
            int hpAfter = enemy.getHp();

            result.append(String.format(
                "%s HP: %d -> %d (dmg: %d-%d=%d)",
                enemy.getName(), hpBefore, hpAfter, atk, def, damage
            ));

            if (!enemy.isAlive()) {
                cumulativeAttackBonus += ATTACK_BONUS_PER_KILL;

                result.append(" ELIMINATED");
                result.append(String.format(
                    " | ATK: %d -> %d (+%d per Arcane Blast kill)",
                    atk, user.getEffectiveAttack() + cumulativeAttackBonus, ATTACK_BONUS_PER_KILL
                ));
            }

            result.append('\n');
        }

        result.append(String.format("Total ATK Bonus: +%d", cumulativeAttackBonus));

        // Remove old buff before applying new one
        user.clearEffectsByType(ArcaneBlastBuff.class);
        user.addStatusEffect(new ArcaneBlastBuff(cumulativeAttackBonus));

        return result.toString();
    }
}
