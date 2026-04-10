package arena.ui;

import arena.domain.entity.Player;
import arena.engine.Level;

public interface GameSetupUI extends DisplayUI {
    Player choosePlayerClass();
    Level chooseDifficulty();
    
    // Allows the player to choose items before game
    void chooseItemsStart(Player player);
}
