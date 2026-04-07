package arena.domain.effect;

public class StunEffect extends StatusEffect {

    public StunEffect(int duration) {
        super(duration);
    }

    @Override
    public boolean preventAction() {
        return true;
    }

    @Override
    public String getName() {
        return "Stunned (" + getDuration() + " turn" + (getDuration() == 1 ? "" : "s") + " remaining)";
    }
}