package arena.engine;

import arena.domain.action.Action;
import arena.domain.entity.Combatant;
import arena.ui.GameUI;

public class PlayerActionGetter implements ActionGetter {
    private final GameUI ui;

    public PlayerActionGetter(GameUI ui) {
        this.ui = ui;
    }

    @Override
    public Action getAction(Combatant source, BattleContext context) {
        return ui.getPlayerAction(source, context);
    }
}
