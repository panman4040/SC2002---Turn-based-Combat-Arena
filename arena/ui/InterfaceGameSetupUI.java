package arena.ui;

import arena.domain.entity.Player;
import arena.domain.entity.PlayerType;
import arena.domain.level.LevelType;

public interface InterfaceGameSetupUI extends InterfaceDisplayUI {
    PlayerType choosePlayerClass();
    LevelType chooseDifficulty();

    // Allows the player to choose items before game
    void chooseItemsStart(Player player);

    // Pre/postgame screens
    void displayWelcomeScreen();
    void displayVictoryScreen(Player player, int totalRounds);
    void displayDefeatScreen(int enemiesRemaining, int totalRounds);

    // Display postgame option
    int promptPostGame(boolean allowReplay);
}
