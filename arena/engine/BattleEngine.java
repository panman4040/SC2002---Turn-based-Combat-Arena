package arena.engine;

import arena.domain.action.Action;
import arena.domain.entity.Combatant;
import arena.ui.GameUI;
import java.util.List;

public class BattleEngine {
    private final BattleContext context;
    private final TurnOrderStrategy turnStrategy;
    private final GameUI ui;

    public BattleEngine(BattleContext context, TurnOrderStrategy turnStrategy, GameUI ui) {
        this.context = context;
        this.turnStrategy = turnStrategy;
        this.ui = ui;
    }
    
    // Return true if player wins, false if loses
    public boolean runBattle() {
        ui.displayMessage("=== Battle Start ===");
        ui.displayMessage("Round 1 begins!");

        // Runs round continuously until either player die or all enemies die
        while (!context.isPlayerDead() && !context.isAllEnemiesDead()) {
            runRound();

            // Check whether can spawn backup enemies or not
            if (context.isAllEnemiesDead() && context.triggerBackupSpawn()) {
                ui.displayMessage("Backup enemies appear!");
            }
        }

        return !context.isPlayerDead();
    }

    // Round logic
    private void runRound() {
        ui.displayMessage("\n=== Round " + context.getRoundNumber() + " ===");
        // UI displays the current round number

        // Get and display the turn order for this round
        // context.getAllCombatants
        List<Combatant> combatants = turnStrategy.determineTurnOrder(context.getAllCombatants());

        ui.displayTurnOrder(combatants);
        // e.g: "Turn Order: Wolf (SPD 35) → Warrior (SPD 30) → Goblin (SPD 25)"

        // Process turns
        for (Combatant combatant : combatants) {
            processTurn(combatant);

            // Remove dead enemies IMMEDIATELY after round
            context.removeDead();

            // Break the round early if one side is wiped out
            if (context.isPlayerDead() || context.isAllEnemiesDead()) 
                break;
        }

        ui.displayBattleState(context);

        // End of round updates
        context.incrementRound();
        for (Combatant combatant : combatants) {
            combatant.tickNonStunEffects();
        }
    }

    // Turn logic
    private void processTurn(Combatant combatant) {
        // Return early if current combatant is dead, though
        // removeDead() is called but the local combatant list is not modified yet
        if (!combatant.isAlive()) return;

        ui.displayMessage("\n" + combatant.getName() + "'s turn:");

        // Decrement combatant's special skill cooldown
        combatant.reduceSpecialCooldown();

        // Apply turn effects before combatant is allowed to do anything
        // e.g: ArcaneBlastBuff
        combatant.applyEffects();

        if (!combatant.isAlive()){
            ui.displayMessage(combatant.getName() + " was defeated before acting.");
        }
        // Combatant IS stunned, tick the stun effect before skipping the turn
        if (!combatant.canAct()) {
            ui.displayMessage(combatant.getName() + " cannot act this turn.");

            // Tick the stun effect
            combatant.tickStunEffects();
            return;
        }

        // Combatant is NOT stunned, proceed normally
        Action action = combatant.getActionGetter().getAction(combatant, context);

        // Display the turn result
        String result = action.execute(combatant, context);
        ui.displayMessage(result);
        // e.g: Goblin A → BasicAttack → Warrior: HP: 260 → 245 (dmg: 35−20=15)
    }
}
