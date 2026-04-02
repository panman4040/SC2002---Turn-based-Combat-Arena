package arena.engine;

import java.util.List;
import java.util.Comparator;
import arena.domain.entity.Combatant;

// The characters with higher speed will act before those with lower speed.
public class SpeedBasedTurnOrder implements TurnOrderStrategy {
    @Override
    public List<Combatant> determineTurnOrder(List<Combatant> combatants) {
        // Sorts by speed, then reverses it so the highest speed is at index 0
        combatants.sort(Comparator.comparingInt(Combatant::getSpeed).reversed());
        
        return combatants;
    }
}