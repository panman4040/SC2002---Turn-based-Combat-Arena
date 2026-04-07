package arena.domain.effect;


public class DefendBuff extends StatusEffect {

    private final int defenseBonus;

    public DefendBuff(int duration, int defenseBonus) {
        super(duration); 
        this.defenseBonus = defenseBonus;
    }

    @Override
    public int modifyDefense(int baseDefense) {
        return baseDefense + defenseBonus;
    }

    @Override
    public String getName() {
        return "Defend (+" + defenseBonus + " DEF)";
    }
}