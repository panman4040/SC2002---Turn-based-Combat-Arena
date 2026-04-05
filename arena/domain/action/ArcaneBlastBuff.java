package arena.domain.effect;

import arena.domain.entity.Combatant;

public class ArcaneBlastBuff extends StatusEffect {

    private final int attackBonus;

    public ArcaneBlastBuff(int attackBonus) {
        super(1); // lasts one round
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
