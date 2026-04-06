package arena.domain.entity;

import arena.domain.action.ShieldBash;

public class Warrior extends Player {
    public Warrior() {
        super("Warrior", 260, 40, 20, 30, new ShieldBash());
    }
}
