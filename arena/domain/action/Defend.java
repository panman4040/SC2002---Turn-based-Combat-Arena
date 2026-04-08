package arena.domain.action;

import arena.domain.effect.DefendBuff;
import arena.domain.entity.Combatant;
import arena.engine.BattleContext;


public class Defend implements Action {

    private static final int DEFENSE_BONUS = 10;
    private static final int DURATION = 2; // current round + next round

    @Override
    public String execute(Combatant source, BattleContext context) {
        source.addStatusEffect(new DefendBuff(DURATION, DEFENSE_BONUS));
        return String.format(
            "%s takes a defensive stance! (+%d DEF for %d rounds)",
            source.getName(), DEFENSE_BONUS, DURATION
        );
    }

    @Override
    public String getName() {
        return "Defend";
    }
}
