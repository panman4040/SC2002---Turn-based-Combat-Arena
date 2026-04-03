package arena.engine;

import java.util.List;

import arena.domain.effect.StatusEffect;
import arena.domain.entity.Combatant;
import arena.domain.action.Action; 
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
        // UI PLACEHOLDER: UI displays starting information (Player, Items, Level,
        // Stats) here

        // Runs round continuously until either player die or all enemies die
        while (!context.isPlayerDead() && !context.isAllEnemiesDead()) {
            runRound();

            // Check whether can spawn backup enemies or not
            if (context.isAllEnemiesDead() && context.triggerBackupSpawn()) {
                // UI PLACEHOLDER: Display backup spawn information
            }
        }

        return !context.isPlayerDead();
    }

    // Round logic
    private void runRound() {
        // UI PLACEHOLDER: (can make this look nicer, this is a prototype for now)
        // UI displays the current round number

        // Get and display the turn order for this round
        // context.getAllCombatants
        List<Combatant> combatants = turnStrategy.determineTurnOrder(context.getAllCombatants());

        // UI PLACEHOLDER: UI displays the turn order for this round here
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

        // UI PLACEHOLDER: UI displays end-of-round information here

        // End of round updates
        tickAllEffects();
        context.incrementRound();
    }

    // Turn logic
    private void processTurn(Combatant combatant) {
        // Return early if current combatant is dead, though
        // removeDead() is called but the local combatant list is not modified yet
        if (!combatant.isAlive()) return;

        // Apply turn effects before combatant is allowed to do anything
        // e.g: ArcaneBlastBuff
        combatant.applyEffects();

        // Combatant IS stunned, tick the stun effect before skipping the turn
        if (!combatant.canAct()) {
            // UI PLACEHOLDER: UI displays that the combatant's turn is skipped

            // Tick the stun effect here, rather than waiting for tickAllEffects() to
            // avoid ticking the effect twice

            combatant.getStatusEffects().removeIf(effect -> {
                if (effect.preventAction()) {
                    effect.tick();
                    return effect.isExpired();
                }
                return false;
            });
            return;
        }

        // Combatant is NOT stunned, proceed normally
        Action action = combatant.chooseAction(context, ui);

        // Display the turn result
        String result = action.execute(combatant, context);
        // UI PLACEHOLDER: Display the turn result
        // e.g: Goblin A → BasicAttack → Warrior: HP: 260 → 245 (dmg: 35−20=15)

        // Decrement combatant's special skill cooldown
        combatant.reduceSpecialCooldown();
    }

    // End-of-round effect ticking
    private void tickAllEffects() {
        // Tick the status effects on every living combatant and
        // remove those that expire
        for (Combatant combatant: context.getAllCombatants()) {
            List<StatusEffect> effects = combatant.getStatusEffects();
            effects.removeIf(effect -> {
                // Stunned effects are ticked in processTurn(), so we skip
                if (effect.preventAction()) return false;
                effect.tick();
                return effect.isExpired();
            });
        }
    }
}
