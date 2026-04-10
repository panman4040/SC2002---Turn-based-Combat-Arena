package arena.domain.entity;

import arena.domain.action.EnemyBehaviour;

public abstract class Enemy extends Combatant {
    private EnemyBehaviour behaviour;

    protected Enemy(String name, int maxHp, int baseAttack, int baseDefense, int speed, EnemyBehaviour behaviour) {
        super(name, maxHp, baseAttack, baseDefense, speed);
        this.behaviour = behaviour;
    }

    public EnemyBehaviour getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(EnemyBehaviour behaviour) {
        this.behaviour = behaviour;
    }
}
