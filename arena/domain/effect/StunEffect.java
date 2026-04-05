package arena.domain.effect;

import arena.domain.entity.Combatant;

ic class StunEffect extends StatusEffect {

    public StunEffect(int duration) {
        super(duration);
    }

    @Override
    public boolean preventAction() {
        return true;
    }

    @Override
    public String getName() {
        return "Stunned (" + duration + " turn" + (duration == 1 ? "" : "s") + " remaining)";
    }
}