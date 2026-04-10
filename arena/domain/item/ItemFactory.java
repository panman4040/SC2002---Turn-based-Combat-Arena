package arena.domain.item;

import java.util.ArrayList;
import java.util.List;

public class ItemFactory {

    private static final List<String> descriptions = new ArrayList<>();

    static {
        descriptions.add("Potion      – Heal 100 HP");
        descriptions.add("Power Stone – Free extra use of your special skill");
        descriptions.add("Smoke Bomb  – Enemy attacks deal 0 damage for 2 turns");
    }

    public static Item create(int choice) {
        switch (choice) {
            case 1: return new Potion();
            case 2: return new PowerStone();
            case 3: return new SmokeBomb();
            default: throw new IllegalArgumentException("Invalid item choice: " + choice);
        }
    }

    public static void printOptions() {
        for (int i = 0; i < descriptions.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, descriptions.get(i));
        }
    }
}