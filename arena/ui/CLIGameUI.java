package arena.ui;
import arena.domain.action.Action;
import arena.domain.action.BasicAttack;
import arena.domain.action.Defend;
import arena.domain.action.ShieldBash;
import arena.domain.action.UseItem;
import arena.domain.entity.Combatant;
import arena.domain.entity.Enemy;
import arena.domain.entity.Player;
import arena.domain.entity.PlayerFactory;
import arena.engine.BattleContext;
import arena.domain.item.ItemFactory;
import arena.engine.Level;
import arena.engine.LevelFactory;

import java.util.List;
import java.util.Scanner;


public class CLIGameUI implements GameUI {

    private Scanner scanner;

    public CLIGameUI() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    // Battle State for (runRound)
    @Override
    public void displayBattleState(BattleContext context) {
        System.out.println("=== Battle State ===");
        System.out.print("End of Round " + context.getRoundNumber() + ": ");

        //for player
        Player player = context.getPlayer();
        System.out.print(player.getName() + " HP: " + player.getHp() + "/" + player.getMaxHp());

        //for enemy if alive
        for (Combatant enemy : context.getAliveEnemies()) {
            System.out.print(" | " + enemy.getName() + " HP: " + enemy.getHp());
        }

        //items
        int powerStoneCount = 0;
        int potionCount = 0;
        int smokeBombCount = 0;

        for (var item : player.getItems()) {
            switch (item.getName()) {
                case "Potion":      potionCount++;     break;
                case "Power Stone": powerStoneCount++; break;
                case "Smoke Bomb":  smokeBombCount++;  break;
            }
        }
        System.out.print(" | Potion: " + potionCount);
        System.out.print(" | Power Stone: " + powerStoneCount);
        System.out.print(" | Smoke Bomb: " + smokeBombCount);
        System.out.print(" | Special Skills Cooldown: " + player.getSpecialCooldown() + " rounds");
        System.out.println();
    }

    //choosePlayerClass
    @Override
    public Player choosePlayerClass(){
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║        TURN-BASED COMBAT ARENA       ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println();
        System.out.println("=== Player Classes ===");
        PlayerFactory.printOptions();

        while (true) {
            System.out.print("Choose your class: ");
            int choice = readInt();
            try {
                return PlayerFactory.create(choice);
            } 
            catch (IllegalArgumentException e) {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    //chooseItems
    @Override
    public void chooseItemsStart(Player player) {
        System.out.println("\n=== Item Selection (pick 2, duplicates allowed) ===");
        ItemFactory.printOptions();

        for (int i = 1; i <= 2; i++) {
            while (true) {
                System.out.print("Choose item " + i + ": ");
                int choice = readInt();
                try {
                    player.getInventory().addItem(ItemFactory.create(choice));
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid choice. Please enter 1–3.");
                }
            }
        }
    }
    //  Turn order for (runRound)
    @Override
    public void displayTurnOrder(List<Combatant> combatants) {
        System.out.print("Turn Order: ");
        for (int i = 0; i < combatants.size(); i++) {
            Combatant c = combatants.get(i);
            System.out.print(c.getName() + " (SPD " + c.getSpeed() + ")");
            if (i < combatants.size() - 1) {
                System.out.print(" → ");
            }
        }
        System.out.println();
    }

    // User Input
    // Difficulty selection
    @Override
    public Level chooseDifficulty() {
        System.out.println("\n=== Difficulty ===");
        LevelFactory.printOptions();

        while (true) {
            System.out.print("Choose difficulty: ");
            int choice = readInt();
            try {
                return LevelFactory.create(choice);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    // Helper: let player choose an enemy target
    private Enemy chooseTarget(BattleContext context) {
        List<Enemy> enemies = context.getAliveEnemies();
        if (enemies.size() == 1) {
            return enemies.get(0);
        }
        System.out.println("Choose a target:");
        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            System.out.printf("  %d. %s (HP: %d)%n", i + 1, e.getName(), e.getHp());
        }
        while (true) {
            System.out.print("Enter choice: ");
            int choice = readInt();
            if (choice >= 1 && choice <= enemies.size()) {
                return enemies.get(choice - 1);
            }
            System.out.println("Invalid choice. Please enter 1–" + enemies.size() + ".");
        }
    }

    // Helper: let player choose an item
    private int chooseItem(Player player) {
        List<? extends arena.domain.item.Item> items = player.getItems();
        if (items.size() == 1) {
            return 0;
        }
        System.out.println("Choose an item:");
        for (int i = 0; i < items.size(); i++) {
            System.out.printf("  %d. %s%n", i + 1, items.get(i).getName());
        }
        while (true) {
            System.out.print("Enter choice: ");
            int choice = readInt();
            if (choice >= 1 && choice <= items.size()) {
                return choice - 1;
            }
            System.out.println("Invalid choice. Please enter 1–" + items.size() + ".");
        }
    }

    // Player action selection
    @Override
    public Action getPlayerAction(Player player, BattleContext context) {
        while (true) {
            System.out.println("Choose an action:");
            System.out.println("1. Basic Attack");
            System.out.println("2. Defend");
            System.out.println("3. Use Item" + (!player.hasItems() ? " (none left)" : ""));
            System.out.println("4. Special Skill" + (!player.isSpecialReady() ? " (cooldown: " + player.getSpecialCooldown() + ")" : ""));
            System.out.print("Enter choice: ");

            int choice = readInt();

            switch (choice) {
                case 1:
                    Enemy target = chooseTarget(context);
                    return new BasicAttack(target);

                case 2:
                    return new Defend();

                case 3:
                    if (player.hasItems()) {
                        int itemIndex = chooseItem(player);
                        return new UseItem(player.removeItem(itemIndex));
                    } else {
                        System.out.println("No items available.");
                        break;
                    }

                case 4:
                    if (player.isSpecialReady()) {
                        // Warrior needs a target for Shield Bash
                        if (player instanceof Warrior) {
                            Enemy specialTarget = chooseTarget(context);
                            return new ShieldBash(specialTarget);
                        } else {
                            // Wizard's Arcane Blast hits all enemies — no target needed
                            return player.getSpecialSkill();
                        }
                    } else {
                        System.out.println("Special skill is on cooldown.");
                        break;
                    }

                default:
                    System.out.println("Invalid choice. Please enter 1–4.");
            }
        }
    }

    private int readInt() {
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.print("Please enter a number: ");
        }
        int val = scanner.nextInt();
        scanner.nextLine();
        return val;
    }
}

