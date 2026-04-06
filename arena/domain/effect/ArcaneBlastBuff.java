package arena.domain.effect;

public class ArcaneBlastBuff extends StatusEffect {

    private final int attackBonus;

    public ArcaneBlastBuff(int attackBonus) {
        super(1000); // lasts one round
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
