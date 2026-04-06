package arena.domain.entity;

import arena.domain.action.BasicAttackBehaviour;

public class Goblin extends Enemy {
    public Goblin() {
        super("Goblin", 55, 35, 15, 25, new BasicAttackBehaviour());
    }
}
