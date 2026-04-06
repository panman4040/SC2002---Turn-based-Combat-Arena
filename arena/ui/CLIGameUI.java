package arena.ui;
import java.util.List;
import java.util.Scanner;

import arena.domain.entity.Combatant;
import arena.domain.entity.Player;

import arena.domain.action.Action;
import arena.domain.action.BasicAttack;
import arena.domain.action.Defend;
import arena.domain.action.UseItem;
import arena.domain.item.Item;

import arena.engine.BattleContext;
import arena.engine.Level;


public class CLIGameUI implements GameUI {

    private Scanner scanner;

    public CLIGameUI() {
        scanner = new Scanner(System.in);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    // Battle State for (runRound)
    public void displayBattleState(BattleContext context) {
        System.out.println("=== Battle State ===");
        System.out.print("End of Round" + context.getRoundNumber() + ": ");

        //for player
        Player player = context.getPlayer();
        System.out.print(player.getName() + " HP: " + "/" + player.getMaxHp());

        //for enemy if alive
        for (Combatant enemy : context.getAliveEnemies()) {
            System.out.print(" | " + enemy.getName() + " HP: " + enemy.getHp());
        }

        //items
        int powerStoneCount = 0;
        int potionCount = 0;

        for (var item : player.getItems()) {
            if (item.getName().equals("Power Stone")) {
                powerStoneCount++;
            } else if (item.getName().equals("Potion")) {
                potionCount++;
            }
        }
        System.out.print(" | Power Stone: " + powerStoneCount);
        System.out.print(" | Potion " + potionCount);

        System.out.print(" | Special Skills Cooldown: " + player.getSpecialSkill().getCooldownDuration() + " round");
        System.out.println(); // now getCooldownDuration is not under player
    }

    //  Turn order for (runRound)
    public void displayTurnOrder(List<Combatant> combatants) {
        System.out.print("Turn Order: ");
        for (int i = 0; i < combatants.size(); i++) {
            Combatant c = combatants.get(i);
            System.out.print(c.getName() + " (SPD " + c.getSpeed() + ")");
            if (i < combatants.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    // User Input
    // Difficulty selection
    public Level chooseDifficulty() {
        while (true) {
            System.out.println("Choose difficulty:");
            System.out.println("1. Easy");
            System.out.println("2. Medium");
            System.out.println("3. Hard");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    return Level.createEasy();

                case 2:
                    return Level.createMedium();

                case 3:
                    return Level.createHard();

                default:
                    System.out.println("Invalid choice. Please enter 1, 2 or 3.");
            }
        }
    }
    // Player action selection


    public Action getPlayerAction(Player player, BattleContext context) {
        while (true) {
            System.out.println("Choose an action:");
            System.out.println("1. Basic Attack");
            System.out.println("2. Defend");
            System.out.println("3. Use Item");
            System.out.println("4. Special Skill");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Choose first alive enemy
                    return new BasicAttack(context.getAliveEnemies().get(0));

                case 2:
                    return new Defend();

                case 3:
                    if (player.hasItems()) {
                        // Use first item
                        return new UseItem(player.removeItem(0));
                    } else {
                        System.out.println("No items available.");
                        break;
                    }

                case 4:
                    if (player.isSpecialReady()) {
                        return player.getSpecialSkill();
                    } else {
                        System.out.println("Special skill is on cooldown.");
                        break;
                    }

                default:
                    System.out.println("Invalid choice. Please enter 1–4.");
            }
        }
    }
}