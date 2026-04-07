package arena.domain.effect;

import arena.domain.entity.Combatant;

public abstract class StatusEffect {

    private int duration;

    protected StatusEffect(int duration) {
        this.duration = duration;
    }

    // Safeguard method for future status effects where
    // effects are applied continuously, e.g: poison, healing, etc.
    public void apply(Combatant target) { /* no-op by default */ }

    public boolean preventAction() { return false; }

    public int modifyDefense(int baseDefense) { return baseDefense; }

    public int modifyAttack(int baseAttack) { return baseAttack; }


    public void tick() {
        duration--;
    }

    public boolean isExpired() {
        return duration <= 0;
    }

    public abstract String getName();

    public int getDuration() {
        return duration;
    }
}
