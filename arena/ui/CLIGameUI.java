package arena.ui;
import java.util.List;
import java.util.Scanner;

import arena.domain.entity.Combatant; //waiting implementation
import arena.domain.entity.Enemy;
import arena.domain.entity.Player; // waiting implementation

import arena.domain.action.Action;
import arena.domain.action.BasicAttack;
import arena.domain.action.Defend;
import arena.domain.action.UseItem;
import arena.domain.item.Item;

import arena.engine.BattleContext;
import arena.engine.Level; // waiting implementation


public class CLIGameUI implements GameUI {

    private Scanner scanner;
    public CLIGameUI() {
        scanner = new Scanner(System.in);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    // Battle State for (runRound)
    public void displayBattleState(BattleContext context){
        System.out.println("=== Battle State ===");
        System.out.print("End of Round" + context.getRoundNumber() + ": ");

        //for player
        Player player = context.getPlayer();
        System.out.print(player.getName() + " HP: " + "/" + player.getMaxHp());

        //for enemy if alive
        for (Combatant enemy : context.getAliveEnemies()){
            System.out.print(" | " + enemy.getName() + " HP: " + enemy.getHp());
        }

        //items
        int powerStoneCount = 0;
        int potionCount = 0;

        for (var item : player.getItems()){
            if (item.getName().equals("Power Stone")){
                powerStoneCount++;
            }
            else if (item.getName().equals("Potion")){
                potionCount++;
            }
        }
        System.out.print(" | Power Stone: " + powerStoneCount);
        System.out.print(" | Potion " + potionCount);

        System.out.print(" | Special Skills Cooldown: " + player.getCooldownDuration() + " round");
        System.out.println(); // Impt: getter for special skills cooldown is not present in player. For now assume itll be added
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
        // Player action selection


        public Action getPlayerAction (Player player, BattleContext context){

            while (true) {
                System.out.println("Choose action:");
                System.out.println("1. Basic Attack");
                System.out.println("2. Defend");
                System.out.println("3. Use Item");
                System.out.println("4. Special Skill");
                System.out.print("Enter choice: ");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1: {
                        List<Enemy> enemies = context.getAliveEnemies();

                        if (enemies.isEmpty()) {
                            System.out.println("No enemies available.");
                            break;
                        }

                        System.out.println("Choose target:");
                        for (int i = 0; i < enemies.size(); i++) {
                            Enemy enemy = enemies.get(i);
                            System.out.println((i + 1) + ". " + enemy.getName() + " HP: " + enemy.getHp());
                        }

                        int targetChoice = scanner.nextInt();

                        if (targetChoice >= 1 && targetChoice <= enemies.size()) {
                            return new BasicAttack(enemies.get(targetChoice - 1));
                        } else {
                            System.out.println("Invalid target.");
                        }
                        break;
                    }

                    case 2:
                        return new Defend();

                    case 3: {
                        List<Item> items = player.getItems();

                        if (items.isEmpty()) {
                            System.out.println("No items available.");
                            break;
                        }

                        System.out.println("Choose item:");
                        for (int i = 0; i < items.size(); i++) {
                            System.out.println((i + 1) + ". " + items.get(i).getName());
                        }

                        int itemChoice = scanner.nextInt();

                        if (itemChoice >= 1 && itemChoice <= items.size()) {
                            return new UseItem(items.get(itemChoice - 1));
                        } else {
                            System.out.println("Invalid item.");
                        }
                        break;
                    }

                    case 4:
                        if (player.isSpecialReady()) {
                            return player.getSpecialSkill(); // must be compatible with Action
                        } else {
                            System.out.println("Special skill is on cooldown.");
                        }
                        break;

                    default:
                        System.out.println("Invalid choice. Enter 1-4.");
                }
            }
        }
