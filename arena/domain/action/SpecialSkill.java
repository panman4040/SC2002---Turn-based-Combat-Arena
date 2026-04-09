package arena.domain.action;

import arena.domain.entity.Combatant;

public abstract class SpecialSkill implements Action {

    private final String name;
    private final int cooldownDuration;

    protected SpecialSkill(String name, int cooldownDuration) {
        this.name             = name;
        this.cooldownDuration = cooldownDuration;
    }

    public boolean requireTarget() { 
        return false;
    }

    public Action withTarget(Combatant target) {
        return this;
    }

    public int getCooldownDuration() {
        return cooldownDuration;
    }

    @Override
    public String getName() {
        return name;
    }
}
 