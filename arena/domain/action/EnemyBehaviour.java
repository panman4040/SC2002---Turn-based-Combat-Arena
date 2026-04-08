package arena.domain.action;

import arena.engine.BattleContext;
import arena.ui.GameUI;

public interface EnemyBehaviour {
    Action chooseAction(BattleContext context, GameUI ui);
}