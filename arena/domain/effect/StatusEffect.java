package arena.domain.effect;

import arena.domain.entity.Combatant;

public abstract class StatusEffect {

    protected int duration;

    protected StatusEffect(int duration) {
        this.duration = duration;
    }

    public void apply(Combatant target) { /* no-op by default */ }

    public boolean preventAction() { return false; }

    public int modifyIncomingDamage(int damage) { return damage; }

    public int modifyAttack(int attack) { return attack; }


    public final void tick() {
        duration--;
    }

    public final boolean isExpired() {
        return duration <= 0;
    }

    public abstract String getName();
}
