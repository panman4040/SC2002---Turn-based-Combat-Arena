package arena.domain.action;

import arena.domain.entity.Combatant;
import arena.domain.item.Item;
import arena.engine.BattleContext;

public class UseItem implements Action {

    private final String name;
    private final Item itemToUse;

    public UseItem(Item itemToUse) {
        this.itemToUse = itemToUse;
        this.name = itemToUse.getName();
    }

    @Override
    public String execute(Combatant user, BattleContext context) {
        itemToUse.use(user, context);
        return String.format("%s uses %s!", user.getName(), name);
    }

    @Override
    public String getName() {
        return name;
    }
}
