package arena.engine;

import java.util.List;
import arena.domain.Combatant;

public interface TurnOrderStrategy {
    List<Combatant> determineTurnOrder(List<Combatant> combatants);
}
