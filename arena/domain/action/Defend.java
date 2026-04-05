package arena.domain.action;

import arena.domain.effect.DefendBuff;
import arena.domain.entity.Combatant;
import arena.engine.BattleContext;


public class Defend implements Action {

    private static final int DEFENSE_BONUS = 15;

    @Override
    public String execute(Combatant source, BattleContext context) {
        source.addStatusEffect(new DefendBuff(DEFENSE_BONUS));
        //need determine string format
        return String.format(
            "%s takes a defensive stance! (+" + DEFENSE_BONUS + " DEF this round)",
            source.getName()
        );
    }

    @Override
    public String getName() {
        return "Defend";
    }
}
