package arena.domain.item;

import arena.domain.entity.Combatant;
import arena.domain.action.SpecialSkill;
import arena.engine.BattleContext;

public class PowerStone implements Item {
    private SpecialSkill skill;

    public PowerStone(SpecialSkill skill) {
        this.skill = skill;
    }

    @Override
    public void use(Combatant user, BattleContext context) {
        skill.execute(user, null, context); // no cooldown change
    }
}
