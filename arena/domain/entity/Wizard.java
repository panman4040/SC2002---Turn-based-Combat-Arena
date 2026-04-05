package arena.domain.entity;

import arena.domain.action.ArcaneBlast;

public class Wizard extends Player {
    public Wizard() {
        super("Wizard", 200, 50, 10, 20, new ArcaneBlast());
    }
}
