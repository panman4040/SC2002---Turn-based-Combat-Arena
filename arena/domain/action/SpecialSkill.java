package arena.domain.action;

public abstract class SpecialSkill implements Action {

    private final String name;
    private final int cooldownDuration;

    protected SpecialSkill(String name, int cooldownDuration) {
        this.name             = name;
        this.cooldownDuration = cooldownDuration;
    }

    @Override
    public String getName() {
        return name;
    }
}
