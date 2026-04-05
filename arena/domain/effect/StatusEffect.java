package arena.domain.effect;

import arena.domain.entity.Combatant;

public interface StatusEffect {
    void onApply(Combatant target);

    void onTurnStart(Combatant target);

    boolean isExpired();
}