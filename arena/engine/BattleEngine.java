package arena.engine;

import java.util.List;

import arena.domain.entity.Combatant;
import arena.ui.GameUI;

public class BattleEngine {
    private BattleContext context;
    private TurnOrderStrategy turnStrategy;
    private GameUI ui;

    public BattleEngine(BattleContext context, TurnOrderStrategy turnStrategy, GameUI ui) {
        this.context = context;
        this.turnStrategy = turnStrategy;
        this.ui = ui;
    }
    
    // Return true if player wins, false if loses
    public boolean runBattle() {
        // Runs round continuously until either player die or all enemies die
        while (!context.isPlayerDead() && !context.isAllEnemiesDead()) {
            runRound();
        }

        return !context.isPlayerDead();
    }

    // TODO: Implement runRound()
    private runRound() {

    }

    // TODO: Implement processTurn()

    // TODO: Implement tickAllEffects()
}
