package arena.ui;

import arena.domain.entity.Player;
import arena.domain.entity.PlayerType;
import arena.engine.LevelType;

public interface GameSetupUI extends DisplayUI {
    PlayerType choosePlayerClass();
    LevelType chooseDifficulty();
    
    // Allows the player to choose items before game
    void chooseItemsStart(Player player);
}
