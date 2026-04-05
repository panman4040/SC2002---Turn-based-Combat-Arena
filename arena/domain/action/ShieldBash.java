package arena.domain.action;

import arena.domain.effect.StunEffect;
import arena.domain.entity.Combatant;
import arena.engine.BattleContext;

public class ShieldBash extends SpecialSkill {

    private static final double DAMAGE_MULTIPLIER = 1.2;
    private static final int    STUN_DURATION     = 1;

    private final Combatant target;

    public ShieldBash(Combatant target) {
        super("Shield Bash", 2);
        this.target = target;
    }

    @Override
    public String execute(Combatant user, BattleContext context) {
        int rawDamage = (int) (user.getEffectiveAttack() * DAMAGE_MULTIPLIER);
        int netDamage = Math.max(0, rawDamage - target.getEffectiveDefense());

        int hpBefore = target.getCurrentHp();
        target.takeDamage(netDamage);
        int hpAfter  = target.getCurrentHp();

        boolean stunned = false;
        if (target.isAlive()) {
            target.addStatusEffect(new StunEffect(STUN_DURATION));
            stunned = true;
        }

        return String.format(
            "%s SHIELD BASHES %s! HP %d → %d (%d dmg%s)",
            user.getName(), target.getName(), hpBefore, hpAfter, netDamage,
            stunned ? ", STUNNED for 1 turn!" : ", target felled!"
        );
    }
}