package arena.domain.action;

import arena.domain.effect.ArcaneBlastBuff;
import arena.domain.entity.Combatant;
import arena.domain.entity.Enemy;
import arena.engine.BattleContext;


public class ArcaneBlast extends SpecialSkill {

    private static final int ATTACK_BONUS = 10;

    public ArcaneBlast() {
        super("Arcane Blast", 3);
    }

    @Override
    public String execute(Combatant user, BattleContext context) {

        int atk = user.getEffectiveAttack(); 

        StringBuilder result = new StringBuilder();

        result.append(
            "Wizard --> Arcane Blast --> All Enemies: "
            );

        for(Enemy enemy : context.getAliveEnemies()){
            int def    = enemy.getEffectiveDefense();
            int damage = Math.max(0, atk - def);

            int hpBefore = enemy.getHp();
            enemy.takeDamage(damage);
            int hpAfter  = enemy.getHp();
            result.append(String.format(
            "%s HP: %d -> %d:%n",
            enemy.getName(), hpBefore, hpAfter
            ));
            if (!enemy.isAlive()){
                result.append(" X ELIMINATED");
            }
            result.append(String.format("(dmg:%d-%d=%d)", atk, def, damage));
            if (!enemy.isAlive()){
                result.append(String.format(" | ATK: %d → %d (+10 per Arcane Blast kill)", atk, atk +10));
                user.addStatusEffect(new ArcaneBlastBuff(ATTACK_BONUS));
            }

        }
        return result.toString().trim();
    }

}
