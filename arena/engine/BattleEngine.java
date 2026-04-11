package arena.engine;

import arena.domain.action.Action;
import arena.domain.entity.Combatant;
import arena.ui.InterfaceBattleUI;
import java.util.List;

public class BattleEngine {
    private final BattleContext context;
    private final TurnOrderStrategy turnStrategy;
    private final InterfaceBattleUI ui;

    public BattleEngine(BattleContext context, TurnOrderStrategy turnStrategy, InterfaceBattleUI ui) {
        this.context = context;
        this.turnStrategy = turnStrategy;
        this.ui = ui;
    }

    public boolean runBattle() {
        ui.displayMessage("\n=== BATTLE START ===");

        // Run rounds until either the player or all enemies are dead
        while (!context.isPlayerDead() && !context.isAllEnemiesDead()) {
            runRound();

            // Check whether backup logic trigger or not
            if (context.isAllEnemiesDead() && context.triggerBackupSpawn()) {
                ui.displayMessage("\n!! Backup enemies have appeared !!");
            }
        }

        return !context.isPlayerDead();
    }

    private void runRound() {
        ui.displayMessage("\n=== Round " + context.getRoundNumber() + " ===");

        List<Combatant> turnOrder = turnStrategy.determineTurnOrder(context.getAllCombatants());

        ui.displayTurnOrder(turnOrder);

        for (Combatant combatant : turnOrder) {
            processTurn(combatant);

            context.removeDead();

            // Break early if player is dead or all enemies are dead
            if (context.isPlayerDead() || context.isAllEnemiesDead()) {
                break;
            }
        }

        // Display summary
        ui.displayBattleState(context);

        context.incrementRound();

        // Tick non-stun effects
        for (Combatant combatant : turnOrder) {
            if (combatant.isAlive()) {
                combatant.tickNonStunEffects();
            }
        }
    }

    private void processTurn(Combatant combatant) {
        if (!combatant.isAlive()) {
            return;
        }

        ui.displayMessage("\n" + combatant.getName() + "'s turn:");
        // Reduce special cooldown first, only meaningful for Player
        combatant.reduceSpecialCooldown();

        // Apply effects before turn
        combatant.applyEffects();

        if (!combatant.isAlive()) {
            ui.displayMessage(combatant.getName() + " was defeated before acting.");
            return;
        }

        // Check whether combatant is stunned, if yes
        // tick the stun effects before returning
        if (!combatant.canAct()) {
            ui.displayMessage(combatant.getName() + " is STUNNED! Turn skipped.");
            combatant.tickStunEffects();
            return;
        }

        // If not stunned, proceed normally
        Action action = combatant.getActionGetter().getAction(combatant, context);

        // Execute the action and display the result
        String result = action.execute(combatant, context);
        ui.displayMessage(result);
    }
}
