package arena.domain.action;

import arena.domain.effect.StunEffect;
import arena.domain.entity.Combatant;
import arena.engine.BattleContext;

public class ShieldBash extends SpecialSkill {

    private static final int STUN_DURATION = 2; // current turn + next turn

    private final Combatant target;

    // No argument constructor
    public ShieldBash() {
        super("Shield Bash", 3);
        this.target = null;
    }

    public ShieldBash(Combatant target) {
        super("Shield Bash", 3); // cooldown 3 turns including current
        this.target = target;
    }

    @Override
    public boolean requireTarget() {
        return true;
    }

    @Override
    public Action withTarget(Combatant target) {
        return new ShieldBash(target);
    }

    @Override
    public String execute(Combatant user, BattleContext context) {
        // Shield Bash deals BasicAttack damage (no multiplier per spec)
        int atk = user.getEffectiveAttack();
        int def = target.getEffectiveDefense();
        int damage = Math.max(0, atk - def);

        int hpBefore = target.getHp();
        target.takeDamage(damage);
        int hpAfter = target.getHp();

        StringBuilder result = new StringBuilder();
        result.append(String.format(
            "%s → Shield Bash → %s: HP %d → %d (dmg: %d−%d=%d)",
            user.getName(), target.getName(), hpBefore, hpAfter, atk, def, damage
        ));

        if (target.isAlive()) {
            target.addStatusEffect(new StunEffect(STUN_DURATION));
            result.append(String.format(" | %s STUNNED (%d turns)", target.getName(), STUN_DURATION));
        } else {
            result.append(String.format(" | %s ELIMINATED", target.getName()));
        }

        return result.toString();
    }
}