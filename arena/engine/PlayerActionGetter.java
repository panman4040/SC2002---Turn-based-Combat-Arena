package arena.engine;

import arena.domain.action.Action;
import arena.domain.entity.Combatant;
import arena.domain.entity.Player;
import arena.ui.InterfaceBattleUI;

public class PlayerActionGetter implements ActionGetter {

    private final InterfaceBattleUI ui;

    public PlayerActionGetter(InterfaceBattleUI ui) {
        this.ui = ui;
    }

    @Override
    public Action getAction(Combatant source, BattleContext context) {
        if (!(source instanceof Player)) {
            throw new IllegalArgumentException(
                "PlayerActionGetter can only be used with Player combatants, "
                + "but received: " + source.getClass().getSimpleName());
        }
        return ui.getPlayerAction((Player) source, context);
    }
}
