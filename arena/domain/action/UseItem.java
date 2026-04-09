package arena.domain.action;

import arena.domain.entity.Combatant;
import arena.domain.item.Item;
import arena.domain.item.ItemContainer;
import arena.engine.BattleContext;

public class UseItem implements Action {

    private final String name;
    private final ItemContainer inventory;
    private final Item itemToUse;

    public UseItem(Item itemToUse, ItemContainer inventory) {
        this.itemToUse = itemToUse;
        this.name = itemToUse.getName();
        this.inventory = inventory;
    }

    @Override
    public String execute(Combatant user, BattleContext context) {
        String result = String.format("%s uses %s!: %s", user.getName(), name, 
        itemToUse.use(user, context));

        inventory.removeItem(itemToUse);
        return result;
    }

    @Override
    public String getName() {
        return name;
    }
}
