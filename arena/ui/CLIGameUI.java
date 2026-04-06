package arena.ui;
import java.util.List;
import java.util.Scanner;
import arena.domain.entity.Combatant; //waiting implementation
import arena.domain.entity.Player; // waiting implementation
import arena.domain.action.Action;
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

        //player info - Name, HP, MaxHp to be included soon

        //enemies info - Name,Hp, MaxHp to be included soon
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
        System.out.println("Choose difficulty:");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");
        int choice = scanner.nextInt();
        return null; // placeholder for now. will further implement soon with switch.
    }
    // Player action selection
    public Action getPlayerAction(Player player, BattleContext context) {
        System.out.println("Choose action:");
        System.out.println("1. Basic Attack");
        System.out.println("2. Defend");
        System.out.println("3. Use Item");
        System.out.println("4. Special Skill");

        int choice = scanner.nextInt();
        return null; //placeholder for now. will further implement soon with switch.
    }
}
