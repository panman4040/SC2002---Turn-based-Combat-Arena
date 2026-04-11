package arena.domain.action;

import arena.domain.entity.Combatant;

public abstract class SpecialSkill implements Action {

    private final String name;
    private final int cooldownDuration;
    private Combatant target;

    protected SpecialSkill(String name, int cooldownDuration) {
        this.name = name;
        this.cooldownDuration = cooldownDuration;
        this.target = null;
    }

    public boolean requireTarget() { 
        return false;
    }

    public void setTarget(Combatant target) {
        this.target = target;
    }

    public int getCooldownDuration() {
        return cooldownDuration;
    }

    @Override
    public String getName() {
        return name;
    }

    public Combatant getTarget() { return target; }
}
 