package arena.domain.entity;

public class PlayerFactory {
    public static Player createPlayer(PlayerType type) {
        return switch (type) {
            case WARRIOR -> new Warrior();
            case WIZARD -> new Wizard();
        };
    }
}
