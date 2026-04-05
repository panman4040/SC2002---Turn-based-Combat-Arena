package arena.ui;
import java.util.List;
import java.util.Scanner;
import arena.domain.entity.Combatant;
public class CLIGameUI {

    private Scanner scanner;

    public CLIGameUI() {
        scanner = new Scanner(System.in);
    }
    public void displayMessage(String message) {
        System.out.println(message);
    }

    // Match BattleEngine from here
    // For (runBattle)
    public void displayStartingInfo() {
        System.out.println("=== Battle Start ===");
    }

    // For (runBattle)
    public void displayBackupSpawn() {
        System.out.println("Backup enemies have appeared!");
    }

    //  Round number (runRound)
    public void displayRoundNumber(int roundNumber) {
        System.out.println("\n=== Round " + roundNumber + " ===");
    }

    //  Turn order (runRound)
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

    // Skipped turn (processTurn)
    public void displayTurnSkipped(Combatant combatant) {
        System.out.println(combatant.getName() + " is unable to act. Turn skipped.");
    }

    // Turn result (processTurn)
    public void displayTurnResult(String result) {
        System.out.println(result);
    }

    // End of round (runRound)
    public void displayEndOfRound() {
        System.out.println("=== End of Round ===");
    }

    // User Input
    // Difficulty selection
    public int chooseDifficulty() {
        System.out.println("Choose difficulty:");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");
        return scanner.nextInt();
    }
    // Player action selection
    public int getPlayerAction() {
        System.out.println("Choose action:");
        System.out.println("1. Basic Attack");
        System.out.println("2. Defend");
        System.out.println("3. Use Item");
        System.out.println("4. Special Skill");
        return scanner.nextInt();
    }
}
