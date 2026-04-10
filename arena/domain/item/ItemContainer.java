package arena.domain.item;

import java.util.List;

public interface ItemContainer {
    void addItem(Item item);
    void removeItem(int index);
    void removeItem(Item item);
    List<Item> getItems();
    int getSize();
    boolean isEmpty();
}
