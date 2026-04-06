package arena.domain.entity;

import arena.domain.action.Action;
import arena.domain.action.EnemyBehaviour;
import arena.engine.BattleContext;
import arena.ui.GameUI;

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

    @Override
    public void triggerSpecial(BattleContext context) {}

    @Override
    public void reduceSpecialCooldown() {}

    @Override
    public Action chooseAction(BattleContext context, GameUI ui) {
        return behaviour.chooseAction(context, ui);
    }
}
