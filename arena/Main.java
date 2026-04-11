package arena;

import arena.domain.entity.Enemy;
import arena.domain.entity.Player;
import arena.domain.entity.PlayerFactory;
import arena.domain.entity.PlayerType;
import arena.domain.item.Item;
import arena.domain.item.ItemFactory;
import arena.domain.item.ItemType;
import arena.domain.level.Level;
import arena.domain.level.LevelFactory;
import arena.domain.level.LevelType;
import arena.engine.BattleContext;
import arena.engine.BattleEngine;
import arena.engine.EnemyActionGetter;
import arena.engine.PlayerActionGetter;
import arena.engine.SpeedBasedTurnOrder;
import arena.ui.ConsoleUI;
import arena.ui.InterfaceConsoleUI;
import java.util.ArrayList;
import java.util.List;


public class Main {

    private final InterfaceConsoleUI ui;

    public Main() {
        this.ui = new ConsoleUI();
    }

    // Game loop until the player chooses to exit
    public void run() {
        boolean running = true;

        while (running) {
            ui.displayWelcomeScreen();

            // Setup phase
            PlayerType playerType = ui.choosePlayerClass();
            LevelType levelType = ui.chooseDifficulty();

            Player player = PlayerFactory.createPlayer(playerType);
            ui.chooseItemsStart(player);

            // Save item if player wants to replay with same items
            List<ItemType> savedItemTypes = resolveItemTypes(player);

            // Battle loop
            boolean replaySame = true;

            while (replaySame) {
                Level level = LevelFactory.createLevel(levelType);
                List<Enemy> initialEnemies = level.getInitialEnemies();
                List<Enemy> backupEnemies = level.getBackupEnemies();

                player.setActionGetter(new PlayerActionGetter(ui));
                wireEnemyActionGetters(initialEnemies);
                wireEnemyActionGetters(backupEnemies);

                BattleContext context = new BattleContext(player, initialEnemies, backupEnemies);
                BattleEngine engine = new BattleEngine(context, new SpeedBasedTurnOrder(), ui);

                ui.displayLevelSummary(context);

                boolean playerWon = engine.runBattle();
                int totalRounds = context.getRoundNumber() - 1;

                // Result screens

                // Player won
                if (playerWon) {
                    ui.displayVictoryScreen(player, totalRounds);
                    int choice = ui.promptPostGame(false);

                    switch (choice) {
                        // New game
                        case 1:
                            replaySame = false;
                            break;

                        // Exit
                        default:
                            replaySame = false;
                            running = false;
                    }
                } 

                // Player lost
                else {
                    ui.displayDefeatScreen(context.getAliveEnemies().size(), totalRounds);
                    int choice = ui.promptPostGame(true);

                    switch (choice) {
                        // Replay with the same items
                        case 1:
                            player = PlayerFactory.createPlayer(playerType);
                            for (ItemType itemType : savedItemTypes) {
                                player.getInventory().addItem(ItemFactory.createItem(itemType));
                            }
                            break;

                        // New game
                        case 2:
                            replaySame = false;
                            break;
                        // Exit
                        default:
                            replaySame = false;
                            running = false;
                    }
                }
            }
        }

        ui.displayMessage("\nThanks for playing!");
    }

    // Set each enemy's behaviour as their action getter
    private void wireEnemyActionGetters(List<Enemy> enemies) {
        for (Enemy enemy: enemies) {
            enemy.setActionGetter(new EnemyActionGetter(enemy.getBehaviour()));
        }
    }

    // Maps inventory items back to ItemType for replay with same items
    private List<ItemType> resolveItemTypes(Player player) {
        List<ItemType> types = new ArrayList<>();

        for (Item item: player.getInventory().getItems()) {

            for (ItemType type : ItemType.values()) {
                if (type.getName().equals(item.getName())) {
                    types.add(type);
                    break;
                }
            }
        }
        return types;
    }
}
