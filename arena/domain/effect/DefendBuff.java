package arena.domain.effect;

import arena.domain.entity.Combatant;

public class DefendBuff implements StatusEffect {
    private int turns = 2;

    @Override
    public void onApply(Combatant target) {
        target.increaseDefense(10);
    }

    @Override
    public void onTurnStart(Combatant target) {
        turns--;
        if (turns == 0) {
            target.increaseDefense(-10);
        }
    }

    @Override
    public boolean isExpired() {
        return turns <= 0;
    }
}