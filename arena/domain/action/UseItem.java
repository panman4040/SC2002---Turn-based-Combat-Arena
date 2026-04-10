package arena.domain.action;

import arena.domain.entity.Combatant;
import arena.domain.item.Item;
import arena.engine.BattleContext;

public class UseItem implements Action {

    private final Item itemToUse;

    public UseItem(Item itemToUse) {
        this.itemToUse = itemToUse;
    }

    @Override
    public String execute(Combatant user, BattleContext context) {
        return String.format("%s uses %s!: %s",
            user.getName(), itemToUse.getName(), itemToUse.use(user, context));
    }

    @Override
    public String getName() {
        return itemToUse.getName();
    }
}
