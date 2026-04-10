package arena.domain.entity;

import java.util.ArrayList;
import java.util.List;

public class PlayerFactory {

    private static final List<Player> templates = new ArrayList<>();

    static {
        templates.add(new Warrior());
        templates.add(new Wizard());
    }

    public static Player create(int choice) {
        switch (choice) {
            case 1: return new Warrior();
            case 2: return new Wizard();
            default: throw new IllegalArgumentException("Invalid player choice: " + choice);
        }
    }

    public static void printOptions() {
        for (int i = 0; i < templates.size(); i++) {
            Player p = templates.get(i);
            System.out.printf("%d. %s | HP: %d  ATK: %d  DEF: %d  SPD: %d | %s%n",
                i + 1, p.getName(), p.getMaxHp(), p.getEffectiveAttack(),
                p.getEffectiveDefense(), p.getSpeed(),
                p.getSpecialSkill().getName());
        }
    }
}