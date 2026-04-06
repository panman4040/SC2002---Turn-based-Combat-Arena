package arena.domain.entity;

import java.util.ArrayList;
import java.util.List;

import arena.domain.action.Action;
import arena.domain.action.SpecialSkill;
import arena.domain.item.Item;
import arena.engine.BattleContext;
import arena.ui.GameUI;

public abstract class Player extends Combatant {
    private List<Item> items;
    private int specialCooldown;
    private SpecialSkill specialSkill;

    protected Player(String name, int maxHp, int baseAttack, int baseDefense, int speed, SpecialSkill specialSkill) {
        super(name, maxHp, baseAttack, baseDefense, speed);
        this.specialSkill = specialSkill;
        this.items = new ArrayList<>();
        this.specialCooldown = 0;
    }

    public SpecialSkill getSpecialSkill() {
        return specialSkill;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public boolean hasItems() {
        return !items.isEmpty();
    }

    public Item removeItem(int index) {
        return items.remove(index);
    }

    public List<Item> getItems() {
        return items;
    }

    public boolean isSpecialReady() {
        return specialCooldown == 0;
    }

    @Override
    public void triggerSpecial(BattleContext context) {
        ((Action) specialSkill).execute(this, context);
    }

    @Override
    public void reduceSpecialCooldown() {
        if (specialCooldown > 0) specialCooldown--;
    }

    @Override
    public Action chooseAction(BattleContext context, GameUI ui) {
        Action action = ui.getPlayerAction(this, context);
        if (action instanceof SpecialSkill) {
            specialCooldown = 3;
        }
        return action;
    }
}
