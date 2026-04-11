package arena.domain.item;

public class ItemFactory {

    public static Item createItem(ItemType type) {
        return switch (type) {
            case POTION -> new Potion();
            case POWER_STONE -> new PowerStone();
            case SMOKE_BOMB -> new SmokeBomb();
        };
    }
}