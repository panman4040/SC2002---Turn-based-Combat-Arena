package arena.domain.entity;

import arena.domain.action.BasicAttackBehaviour;

public class Wolf extends Enemy {
    public Wolf() {
        super("Wolf", 40, 45, 5, 35, new BasicAttackBehaviour());
    }

    public Wolf(String name) {
        super(name, 40, 45, 5, 35, new BasicAttackBehaviour());
    }
}
