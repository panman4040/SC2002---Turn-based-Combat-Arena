package arena.domain.action;

import arena.domain.entity.Combatant;
import arena.engine.BattleContext;

public abstract class SpecialSkill implements Action {
    protected int cooldown = 0;
    protected final int MAX_COOLDOWN = 3;

    public boolean isAvailable() {
        return cooldown == 0;
    }

    public void triggerCooldown() {
        cooldown = MAX_COOLDOWN;
    }

    public void reduceCooldown() {
        if (cooldown > 0)
            cooldown--;
    }
}