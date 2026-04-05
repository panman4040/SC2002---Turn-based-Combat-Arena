package arena.domain.action;

import arena.domain.entity.Combatant;
import arena.domain.item.Item;
import arena.engine.BattleContext;

public class UseItem implements Action {
    private Item item;

    public UseItem(Item item) {
        this.item = item;
    }

    @Override
    public void execute(Combatant actor, Combatant target, BattleContext context) {
        item.use(actor, context);
    }
}