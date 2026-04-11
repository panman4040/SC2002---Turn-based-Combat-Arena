package arena.domain.effect;

public class BerserkerBrewEffect extends StatusEffect {
    private final int attackBonus;
    private final int defenseDrop;

    public BerserkerBrewEffect(int duration, int attackBonus, int defenseDrop) {
        super(duration);
        this.attackBonus = attackBonus;
        this.defenseDrop = defenseDrop;
    }

    @Override
    public int modifyAttack(int baseAttack) {
        return baseAttack + attackBonus;
    }

    @Override
    public int modifyDefense(int baseDefense) {
        return baseDefense - defenseDrop;
    }

    @Override
    public String getName() {
        return "Intoxicated";
    }
}
