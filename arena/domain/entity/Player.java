package arena.domain.entity;

import arena.domain.action.SpecialSkill;
import arena.domain.item.Inventory;
import arena.domain.item.Item;
import arena.engine.BattleContext;

import java.util.List;

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

    public Inventory getInventory() {
        return inventory;
    }

    // Convenience delegations so callers don't need to go through getInventory()
    public boolean hasItems() {
        return !inventory.isEmpty();
    }

    public List<Item> getItems() {
        return inventory.getItems();
    }

    public Item removeItem(int index) {
        return inventory.removeItem(index);
    }

    public void addItem(Item item) {
        inventory.addItem(item);
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
    public void triggerSpecial(BattleContext context) {
        specialSkill.execute(this, context);
    }

    @Override
    public void reduceSpecialCooldown() {
        if (specialCooldown > 0) specialCooldown--;
    }
}
