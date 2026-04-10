package arena.domain.entity;

import arena.domain.action.SpecialSkill;
import arena.domain.item.Inventory;
import arena.engine.BattleContext;

public abstract class Player extends Combatant {
    private final Inventory inventory;
    private int specialCooldown;
    private final SpecialSkill specialSkill;

    protected Player(String name, int maxHp, int baseAttack, int baseDefense, int speed, SpecialSkill specialSkill) {
        super(name, maxHp, baseAttack, baseDefense, speed);
        this.specialSkill = specialSkill;
        this.inventory = new Inventory();
        this.specialCooldown = 0;
    }

    public SpecialSkill getSpecialSkill() {
        return specialSkill;
    }

    // Return the original so that future additions/removals are allowed
    public Inventory getInventory() {
        return inventory;
    }

    public boolean isSpecialReady() {
        return specialCooldown == 0;
    }

    public int getSpecialCooldown() {
        return specialCooldown;
    }

    @Override
    public void triggerSpecial(BattleContext context) {
        specialSkill.execute(this, context);
    }

    @Override
    public void reduceSpecialCooldown() {
        if (specialCooldown > 0) specialCooldown--;
    }
}
