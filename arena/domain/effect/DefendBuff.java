package arena.domain.effect;


public class DefendBuff extends StatusEffect {

    private final int defenseBonus;

    public DefendBuff(int duration, int defenseBonus) {
        super(duration); 
        this.defenseBonus = defenseBonus;
    }

    @Override
    public int modifyIncomingDamage(int damage) {
        return Math.max(0, damage - defenseBonus);
    }

    @Override
    public String getName() {
        return "Defend (+" + defenseBonus + " DEF)";
    }
}