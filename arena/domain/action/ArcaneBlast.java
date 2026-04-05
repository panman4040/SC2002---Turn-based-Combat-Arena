package arena.domain.action;

import arena.domain.effect.ArcaneBlastBuff;
import arena.domain.entity.Combatant;
import arena.engine.BattleContext;


public class ArcaneBlast extends SpecialSkill {

    private static final int ATTACK_BONUS = 20;

    private final Combatant target;

    public ArcaneBlast(Combatant target) {
        super("Arcane Blast", 3);
        this.target = target;
    }

    @Override
    public String execute(Combatant user, BattleContext context) {
        
        user.addStatusEffect(new ArcaneBlastBuff(ATTACK_BONUS));

        int atk    = user.getEffectiveAttack(); 
        int def    = target.getEffectiveDefense();
        int damage = Math.max(0, atk - def);

        int hpBefore = target.getCurrentHp();
        target.takeDamage(damage);
        int hpAfter  = target.getCurrentHp();

        return String.format(
            "%s channels Arcane Blast! Attack surges by +%d → %s: HP %d → %d (%d dmg)",
            user.getName(), ATTACK_BONUS, target.getName(), hpBefore, hpAfter, damage
        );
    }
}
