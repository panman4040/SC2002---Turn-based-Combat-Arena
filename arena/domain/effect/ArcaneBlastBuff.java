package arena.domain.effect;

public class ArcaneBlastBuff extends StatusEffect {

    private final int attackBonus;
    private static final int PERMANENT = Integer.MAX_VALUE;

    public ArcaneBlastBuff(int attackBonus) {
        super(PERMANENT); // lasts until end of level
        this.attackBonus = attackBonus;
    }

    @Override
    public int modifyAttack(int attack) {
        return attack + attackBonus;
    }

    @Override
    public String getName() {
        return "Arcane Blast (+" + attackBonus + " ATK)";
    }
}
