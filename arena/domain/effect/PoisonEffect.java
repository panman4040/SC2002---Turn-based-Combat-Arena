package arena.domain.effect;

import arena.domain.entity.Combatant;

public class PoisonEffect extends StatusEffect {
    private final int poisonDamage;

    public PoisonEffect(int duration, int poisonDamage) {
        super(duration);
        this.poisonDamage = poisonDamage;
    }

    @Override
    public boolean tickOnApply() {
        return true; // tick immediately after apply(), not at end of round
    }

    @Override
    public String apply(Combatant target) {
        // Apply poison damage each turn
        int hpBefore = target.getHp();
        target.takeDamage(poisonDamage);
        int hpAfter = target.getHp();

        return String.format("%s takes %d poison damage! (HP: %d -> %d)",
            target.getName(), poisonDamage, hpBefore, hpAfter);
    }

    @Override
    public String getName() {
        return "Poisoned";
    }
}
