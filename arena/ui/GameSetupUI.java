package arena.ui;

import arena.domain.entity.Player;
import arena.domain.entity.PlayerType;
import arena.engine.Level;

public interface GameSetupUI extends DisplayUI {
    PlayerType choosePlayerClass();
    Level chooseDifficulty();
    
    // Allows the player to choose items before game
    void chooseItemsStart(Player player);
}
