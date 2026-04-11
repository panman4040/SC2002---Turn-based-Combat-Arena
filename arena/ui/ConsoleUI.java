package arena.ui;

import arena.domain.action.Action;
import arena.domain.action.BasicAttack;
import arena.domain.action.Defend;
import arena.domain.action.SpecialSkill;
import arena.domain.action.UseItem;
import arena.domain.effect.StatusEffect;
import arena.domain.entity.Combatant;
import arena.domain.entity.Enemy;
import arena.domain.entity.Player;
import arena.domain.entity.PlayerType;
import arena.domain.item.Item;
import arena.domain.item.ItemFactory;
import arena.domain.item.ItemType;
import arena.engine.BattleContext;
import arena.engine.LevelType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleUI implements InterfaceConsoleUI {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    // Helper function that keeps prompting until satisfied
    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            }
            catch (NumberFormatException e) {
                displayMessage("Invalid input. Please enter a number.");
            }
        }
    }

    // Helper function that keeps prompting until within range
    private int readIntInRange(String prompt, int min, int max) {
        while (true) {
            int value = readInt(prompt);
            if (value >= min && value <= max) return value;
            displayMessage("Invalid choice. Please pick a number between " + min + " and " + max + ".");
        }
    }

    // Helper function that chooses target
    private Enemy chooseTarget(BattleContext context) {
        List<Enemy> enemies = context.getAliveEnemies();
        if (enemies.size() == 1) return enemies.get(0);

        displayMessage("Choose your target:");
        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            displayMessage((i + 1) + ". " + e.getName() + " (HP: " + e.getHp() + ")");
        }

        int choice = readIntInRange("Select target (1-" + enemies.size() + "): ", 1, enemies.size());
        return enemies.get(choice - 1);
    }

    // Auto select if only 1, else let player choose
    private Item chooseItem(Player player) {
        List<Item> items = player.getInventory().getItems();
        if (items.size() == 1) return items.get(0);

        displayMessage("Choose an item:");
        for (int i = 0; i < items.size(); i++) {
            displayMessage((i + 1) + ". " + items.get(i).getName());
        }

        int choice = readIntInRange("Select item (1-" + items.size() + "): ", 1, items.size());
        return items.get(choice - 1);
    }

    // Game Setup UI 

    @Override
    public PlayerType choosePlayerClass() {
        displayMessage("\n=== CHOOSE YOUR CLASS ===");

        PlayerType[] options = PlayerType.values();

        for (int i = 0; i < options.length; i++) {
            PlayerType currentType = options[i];
            displayMessage((i + 1) + ". " + currentType.getName() + ": " + currentType.getDescription());
            displayMessage("[HP: " + currentType.getMaxHp() +
                    " | Attack: " + currentType.getBaseAttack() +
                    " | Defense: " + currentType.getBaseDefense() +
                    " | Special Skill: " + currentType.getSpecialSkill() + "]");
        }

        int choice = readIntInRange("\nSelect your fighter (1-" + options.length + "): ", 1, options.length);

        displayMessage("You have chosen: " + options[choice - 1].getName() + "!\n");
        return options[choice - 1];
    }

    @Override
    public LevelType chooseDifficulty() {
        displayMessage("=== CHOOSE YOUR DIFFICULTY === ");

        LevelType[] options = LevelType.values();

        for (int i = 0; i < options.length; i++) {
            displayMessage((i + 1) + ". " + options[i].getName() + ": " + options[i].getDescription());
        }

        int choice = readIntInRange("\nSelect the level (1-" + options.length + "): ", 1, options.length);

        displayMessage("You have chosen: " + options[choice - 1].getName() + "!\n");
        return options[choice - 1];
    }

    @Override
    public void chooseItemsStart(Player player) {
        displayMessage("\n=== CHOOSE 2 STARTING ITEMS ===");

        ItemType[] options = ItemType.values();

        for (int selection = 1; selection <= 2; selection++) {
            displayMessage("\n-- Item Choice " + selection + " of 2 --");

            for (int i = 0; i < options.length; i++) {
                ItemType currentType = options[i];
                displayMessage((i + 1) + ". " + currentType.getName() + ": " + currentType.getDescription());
            }

            int choice = readIntInRange("\nSelect item " + selection + " (1-" + options.length + "): ", 1, options.length);

            ItemType chosenType = options[choice - 1];
            Item item = ItemFactory.createItem(chosenType);
            player.getInventory().addItem(item);
            displayMessage("Added " + item.getName() + " to your inventory!");
        }

        displayMessage("\nGood choice soldier. Your bag is full.");
    }

    @Override
    public void displayWelcomeScreen() {
        displayMessage("========================================");
        displayMessage("     WELCOME TO THE COMBAT ARENA!      ");
        displayMessage("========================================\n");
    }

    @Override
    public void displayVictoryScreen(Player player, int totalRounds) {
        displayMessage("\n========================================");
        displayMessage("              VICTORY!                 ");
        displayMessage("========================================");
        displayMessage("Congrats champ! You aced it.");
        displayMessage(String.format("Statistics: Remaining HP: %d/%d | Total Rounds: %d",
                player.getHp(), player.getMaxHp(), totalRounds));
    }

    @Override
    public void displayDefeatScreen(int enemiesRemaining, int totalRounds) {
        displayMessage("\n========================================");
        displayMessage("              DEFEAT                   ");
        displayMessage("========================================");
        displayMessage("Defeated. Don't give up!");
        displayMessage(String.format("Statistics: Enemies remaining: %d | Total Rounds Survived: %d",
                enemiesRemaining, totalRounds));
    }

    @Override
    public int promptPostGame(boolean allowReplay) {
        displayMessage("\n=== WHAT WOULD YOU LIKE TO DO? ===");
        if (allowReplay) {
            displayMessage("1. Replay with the same settings");
            displayMessage("2. Start a new game");
            displayMessage("3. Exit");
            return readIntInRange("Enter choice (1-3): ", 1, 3);
        }

        else {
            displayMessage("1. Start a new game");
            displayMessage("2. Exit");
            return readIntInRange("Enter choice (1-2): ", 1, 2);
        }
    }

    // Battle Display UI
    @Override
    public void displayLevelSummary(BattleContext context) {
        displayMessage("\n=== LEVEL SUMMARY ===");
        Player player = context.getPlayer();

        // Print Player Stats
        displayMessage("Player: " + player.getName() +
                ", Player Stats: HP: " + player.getMaxHp() +
                ", ATK: " + player.getEffectiveAttack() +
                ", DEF: " + player.getEffectiveDefense() +
                ", SPD: " + player.getSpeed());

        // Print Items
        List<Item> items = player.getInventory().getItems();
        String itemString = "";

        if (items.isEmpty()) {
            itemString = "None";
        }
        else {
            for (int i = 0; i < items.size(); i++) {
                itemString += items.get(i).getName();
                if (i < items.size() - 1) {
                    itemString += " + ";
                }
            }
        }
        displayMessage("\nItems: " + itemString);

        // Print Enemies 
        List<Enemy> enemies = context.getAllEnemies();
        String enemiesList = "";

        for (int i = 0; i < enemies.size(); i++) {
            enemiesList += enemies.get(i).getName();
            // Add a " + " unless it's the very last enemy
            if (i < enemies.size() - 1) {
                enemiesList += " + ";
            }
        }
        displayMessage("\nEnemies Present: " + enemiesList);

        // Print Unique Enemy Stats
        displayMessage(""); // spacing

        List<String> alreadyPrintedTypes = new ArrayList<>();

        for (int i = 0; i < enemies.size(); i++) {
            Combatant enemy = enemies.get(i);
            String typeName = enemy.getClass().getSimpleName();

            // Print if list hasn't contained this enemy type
            if (!alreadyPrintedTypes.contains(typeName)) {
                displayMessage(typeName + " Stats: HP: " + enemy.getMaxHp() +
                        ", ATK: " + enemy.getEffectiveAttack() +
                        ", DEF: " + enemy.getEffectiveDefense() +
                        ", SPD: " + enemy.getSpeed());

                alreadyPrintedTypes.add(typeName);
            }
        }
    }

    @Override
    public void displayRoundStart(BattleContext context) {
        displayMessage("\n=== ROUND " + context.getRoundNumber() + " ===");
    }

    @Override
    public String formatCombatantStats(Combatant combatant) {
        StringBuilder sb = new StringBuilder();
        int wholeLevelThreshold = 2000000000; // essentially infinite

        sb.append("- ").append(combatant.getName())
                .append(" HP: ").append(combatant.getHp()).append("/").append(combatant.getMaxHp());

        List<StatusEffect> effects = combatant.getStatusEffects();

        if (!effects.isEmpty()) {
            sb.append(" | Status: [");
            for (int i = 0; i < effects.size(); i++) {
                StatusEffect effect = effects.get(i);
                int duration = effect.getDuration();

                if (duration > wholeLevelThreshold) {
                    // Infinite effects such as ArcaneBlast still ticks, so need to have threshold value
                    sb.append(effect.getName()).append(" (whole level)");
                }
                else {
                    sb.append(effect.getName()).append(" (").append(duration).append(" turn)");
                }

                if (i < effects.size() - 1) sb.append(", ");
            }
            sb.append("]");
        }

        return sb.toString();
    }

    @Override
    public void displayBattleState(BattleContext context) {
        displayMessage("\n=== Battle State ===");
        displayMessage("End of Round " + context.getRoundNumber() + ": ");

        displayMessage("\n== Player ==");
        Player player = context.getPlayer();
        displayMessage(formatCombatantStats(player));

        displayMessage("\n== Remaining Enemies ==");
        for (Combatant enemy : context.getAliveEnemies()) {
            displayMessage(formatCombatantStats(enemy));
        }

        displayMessage("\n= Remaining Inventory Items =");
        Map<String, Integer> itemFrequencyInInv = new HashMap<>();
        for (Item item : player.getInventory().getItems()) {
            itemFrequencyInInv.merge(item.getName(), 1, Integer::sum);
        }
        itemFrequencyInInv.forEach((itemName, count) -> {
            displayMessage("- " + itemName + " (x" + count + ")");
        });
    }

    @Override
    public void displayTurnOrder(List<Combatant> combatants) {
        System.out.print("Turn Order: ");
        for (int i = 0; i < combatants.size(); i++) {
            Combatant c = combatants.get(i);
            System.out.print(c.getName() + " (SPD " + c.getSpeed() + ")");
            if (i < combatants.size() - 1) System.out.print(" -> ");
        }
        System.out.println();
    }

    @Override
    public Action getPlayerAction(Player player, BattleContext context) {
        while (true) {
            SpecialSkill skill = player.getSpecialSkill();

            // Build labels so disabled options are clearly marked before the player chooses
            String specialLabel = "3. " + skill.getName()
                    + (player.isSpecialReady() ? "" : " (cooldown: " + player.getSpecialCooldown() + " turns)");
            String itemLabel = "4. Use Item"
                    + (player.getInventory().isEmpty() ? " (none left)" : "");

            displayMessage("\n=== YOUR TURN ===");
            displayMessage("1. Basic Attack");
            displayMessage("2. Defend");
            displayMessage(specialLabel);
            displayMessage(itemLabel);

            int choice = readIntInRange("Select action (1-4): ", 1, 4);

            switch (choice) {
                case 1:
                    return new BasicAttack(chooseTarget(context));

                case 2:
                    return new Defend();

                case 3:
                    if (!player.isSpecialReady()) {
                        displayMessage("Special skill is on cooldown. Choose another action.");
                        break;
                    }
                    if (skill.requireTarget()) {
                        skill.setTarget(chooseTarget(context));
                    }
                    // Start cooldown on use
                    player.setSpecialCooldown(skill.getCooldownDuration());
                    return skill;

                case 4:
                    if (player.getInventory().isEmpty()) {
                        displayMessage("No items left. Choose another action.");
                        break;
                    }
                    Item chosen = chooseItem(player);
                    player.getInventory().removeItem(chosen);

                    if (chosen.requireTarget(player)) {
                        chosen.setTarget(chooseTarget(context));
                    }

                    return new UseItem(chosen);
            }
        }
    }
}
