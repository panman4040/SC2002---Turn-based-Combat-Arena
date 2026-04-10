package arena.engine;

import arena.domain.action.Action;
import arena.domain.action.EnemyBehaviour;
import arena.domain.entity.Combatant;

public class EnemyActionGetter implements ActionGetter {
    private final EnemyBehaviour behaviour;

    public EnemyActionGetter(EnemyBehaviour behaviour) {
        this.behaviour = behaviour;
    }

    @Override
    public Action getAction(Combatant source, BattleContext context) {
        return behaviour.chooseAction(source, context);
    }
}
