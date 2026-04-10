package arena.domain.item;

import java.util.List;

public interface ItemContainer {
    void addItem(Item item);
    Item removeItem(int index);
    List<Item> getItems();
    int getSize();
    boolean isEmpty();
}
