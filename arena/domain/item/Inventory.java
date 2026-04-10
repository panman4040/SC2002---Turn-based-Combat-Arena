package arena.domain.item;

import java.util.ArrayList;
import java.util.List;

public class Inventory implements ItemContainer {
    private final List<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    @Override
    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public Item removeItem(int index) {
        return items.remove(index);
    }

    @Override
    public List<Item> getItems() {
        return new ArrayList<>(items);
    }

    @Override
    public int getSize() {
        return items.size();
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }
}
