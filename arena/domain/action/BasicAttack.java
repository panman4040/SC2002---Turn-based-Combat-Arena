package arena.domain.action;

import arena.domain.entity.Combatant;
import arena.engine.BattleContext;


public class BasicAttack implements Action {

    private final Combatant target;

    public BasicAttack(Combatant target) {
        this.target = target;
    }

    @Override
    public String execute(Combatant source, BattleContext context) {
        int atk    = source.getEffectiveAttack();
        int def    = target.getEffectiveDefense();
        int damage = Math.max(0, atk - def);

        int hpBefore = target.getHp();
        target.takeDamage(damage);
        int hpAfter = target.getHp();

        // need to decide what to return for string
        return String.format(
            "%s -> Basic Attack -> %s: HP %d -> %d (ATK %d - DEF %d = %d dmg)",
            source.getName(), target.getName(), hpBefore, hpAfter, atk, def, damage
        );
    }

    @Override
    public String getName() {
        return "Basic Attack";
    }
}
