package arena.domain.entity;

import arena.domain.action.SpecialSkill;
import arena.domain.item.Inventory;

public abstract class Player extends Combatant {
    private final Inventory inventory;
    private int specialCooldown;

    protected Player(String name, int maxHp, int baseAttack, int baseDefense, int speed, SpecialSkill specialSkill) {
        super(name, maxHp, baseAttack, baseDefense, speed, specialSkill);
        this.inventory = new Inventory();
        this.specialCooldown = 0;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public boolean isSpecialReady() {
        return specialCooldown == 0;
    }

    public int getSpecialCooldown() {
        return specialCooldown;
    }

    public void setSpecialCooldown(int cooldown) {
        this.specialCooldown = cooldown;
    }

    @Override
    public void reduceSpecialCooldown() {
        if (specialCooldown > 0) specialCooldown--;
    }
}
