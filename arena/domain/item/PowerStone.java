package arena.domain.item;

import arena.domain.entity.Combatant;
import arena.engine.BattleContext;

public class PowerStone implements Item {
    private Combatant target;

    public PowerStone() {}

    @Override
    public boolean requireTarget(Combatant user) {
        return user.getSpecialSkill().requireTarget();
    }

    @Override
    public void setTarget(Combatant target) {
        this.target = target;
    }

    @Override
    public String use(Combatant user, BattleContext context) {
        // Trigger the special skill effect once without affecting cooldown
        user.getSpecialSkill().setTarget(target);
        return String.format("Special Skill Activates!\n" + "%s", user.getSpecialSkill().execute(user, context));
    }

    @Override
    public String getName() {
        return "Power Stone";
    }
}
