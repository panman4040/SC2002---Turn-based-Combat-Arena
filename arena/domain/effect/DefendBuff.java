package arena.domain.effect;

import arena.domain.entity.Combatant;

public class DefendBuff extends StatusEffect {

    private final int defenseBonus;

    public DefendBuff(int defenseBonus) {
        super(1); // lasts one round
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