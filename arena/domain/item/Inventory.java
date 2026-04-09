package arena.domain.item;

import arena.domain.entity.Combatant;
import arena.engine.BattleContext;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private final List<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(int index) {
        items.remove(index);
    }

    public String useItem(int index, Combatant user, BattleContext context) {
        if (index < 0 || index >= items.size()) {
            return "Invalid index";
        }
        Item itemToUse = items.get(index);
        String output = itemToUse.use(user, context);
        removeItem(index);
        return output;
    }

    public List<Item> getItems() {
        return new ArrayList<>(items);
    }

    public int getSize() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
