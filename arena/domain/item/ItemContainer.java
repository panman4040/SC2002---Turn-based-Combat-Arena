package arena.domain.item;

import java.util.List;

public interface ItemContainer {
    public void addItem(Item item);
    public void removeItem(int index);
    public void removeItem(Item item);
    public List<Item> getItems();
    public int getSize();
    public boolean isEmpty();
}
