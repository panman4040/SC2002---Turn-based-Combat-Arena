package arena.ui;

import arena.domain.entity.Player;
import arena.domain.entity.Combatant;
import arena.domain.entity.PlayerType;
import arena.domain.action.Action;
import arena.engine.LevelType;
import arena.engine.BattleContext;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI implements GameSetupUI, BattleUI {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    // Game Setup Display UI
    @Override
    public PlayerType choosePlayerClass() {
        displayMessage("\n=== CHOOSE YOUR CLASS ===");

        PlayerType[] options = PlayerType.values();

        // Print the player options
        for (int i = 0; i < options.length; i++) {
            PlayerType currentType = options[i];
            displayMessage((i + 1) + ". " + currentType.getName() + ": " + currentType.getDescription());
            displayMessage("[HP: " + currentType.getMaxHp() + 
            " | Attack: " + currentType.getBaseAttack() + 
            " | Defense: " + currentType.getBaseDefense() + 
            " | Special Skill: " + currentType.getSpecialSkill() + "]");
        }

        // Getting user input
        int choice = -1;
        while (choice < 1 || choice > options.length) {
            displayMessage("\nSelect your fighter (1-" + options.length + "): ");

            String input = scanner.nextLine().trim();
            
            try {
                choice = Integer.parseInt(input);
                if (choice < 1 || choice > options.length) {
                    displayMessage("Invalid choice. Please pick a number between 1 and " + options.length);
                }
            } catch (NumberFormatException e) {
                // If they type something else rather than a number
                displayMessage("Invalid input. Please enter the number corresponding to the class");
            }
        }

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

        // Getting user input
        int choice = -1;
        while (choice < 1 || choice > options.length) {
            displayMessage("\nSelect the level (1-" + options.length + "): ");

            String input = scanner.nextLine().trim();
            
            try {
                choice = Integer.parseInt(input);
                if (choice < 1 || choice > options.length) {
                    displayMessage("Invalid choice. Please pick a number between 1 and " + options.length);
                }
            } catch (NumberFormatException e) {
                // If they type something else rather than a number
                displayMessage("Invalid input. Please enter the number corresponding to the level");
            }
        }

        displayMessage("You have chosen: " + options[choice - 1].getName() + "!\n");
        return options[choice - 1];
    }

    // Allows the player to choose items before game
    @Override
    public void chooseItemsStart(Player player) {

    }

    // Battle Display UI
    @Override
    public void displayBattleState(BattleContext context) {

    }

    @Override
    public void displayTurnOrder(List<Combatant> combatants) {

    }

    @Override
    public Action getPlayerAction(Player player, BattleContext context) {

    }
}
