package arena.domain.entity;

import arena.domain.action.SpecialSkill;
import arena.domain.effect.StatusEffect;
import arena.engine.ActionGetter;
import java.util.ArrayList;
import java.util.List;

public abstract class Combatant {
    private final String name;
    private int hp;
    private final int maxHp;
    private final int baseAttack;
    private final int baseDefense;
    private final int speed;
    private final List<StatusEffect> statusEffects;
    private final SpecialSkill specialSkill;

    private ActionGetter actionGetter;

    protected Combatant(String name, int maxHp, int baseAttack, int baseDefense, int speed) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.speed = speed;
        this.statusEffects = new ArrayList<>();
        this.specialSkill = null;
    }

    // If Combatant has a special skill
    protected Combatant(String name, int maxHp, int baseAttack, int baseDefense, int speed, SpecialSkill specialSkill) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.speed = speed;
        this.statusEffects = new ArrayList<>();
        this.specialSkill = specialSkill;
    }

    public void setActionGetter(ActionGetter actionGetter) {
        this.actionGetter = actionGetter;
    }

    public ActionGetter getActionGetter() {
        return actionGetter;
    }

    public void takeDamage(int damage) {
        int modified = damage;
        for (StatusEffect effect : statusEffects) {
            modified = effect.modifyIncomingDamage(modified);
        }
        hp = Math.max(0, hp - modified);
    }

    public void heal(int amount) {
        hp = Math.min(maxHp, hp + amount);
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void applyEffects() {
        for (StatusEffect effect : statusEffects) {
            effect.apply(this);
        }
    }

    public boolean canAct() {
        for (StatusEffect effect : statusEffects) {
            if (effect.preventAction()) return false;
        }
        return true;
    }

    public int getEffectiveAttack() {
        int attack = baseAttack;
        for (StatusEffect effect : statusEffects) {
            attack = effect.modifyAttack(attack);
        }
        return attack;
    }

    public int getEffectiveDefense() {
        int defense = baseDefense;
        for (StatusEffect effect : statusEffects) {
            defense = effect.modifyDefense(defense);
        }
        return defense;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getSpeed() {
        return speed;
    }

    public SpecialSkill getSpecialSkill() {
        return specialSkill;
    }

    public void addStatusEffect(StatusEffect effect) {
        for (StatusEffect existingEffect : statusEffects) {
            // Check whether effect is already applied
            // If yes, extend the duration
            if (existingEffect.getName().equals(effect.getName())) {
                existingEffect.setDuration(existingEffect.getDuration() + effect.getDuration());
                return; 
            }
        }

        // If no, add the effect
        statusEffects.add(effect);
    }

    // Remove a given type of Effect, prevent stacking 
    public void clearEffectsByType(Class<? extends StatusEffect> type) {
        statusEffects.removeIf(type::isInstance);
    }

    public List<StatusEffect> getStatusEffects() {
        return new ArrayList<>(statusEffects);
    }

    public void tickStunEffects() {
        for (int i = statusEffects.size() - 1; i >= 0; i--) {
            StatusEffect effect = statusEffects.get(i);
            // Check whether effect can prevent actions, aka stunning
            if (effect.preventAction()) {
                effect.tick();
                if (effect.isExpired()) {
                    statusEffects.remove(i); 
                }
            }
        }
    }

    public void tickNonStunEffects() {
        for (int i = statusEffects.size() - 1; i >= 0; i--) {
            StatusEffect effect = statusEffects.get(i);
            // Check whether effect cannot prevent actions
            if (!effect.preventAction()) {
                effect.tick();
                if (effect.isExpired()) {
                    statusEffects.remove(i); 
                }
            }
        }
    }   

    public void tickSpecialCooldown() { /* no-op by default */};

    public void startSpecialCooldown() { /* no-op by default */ };
}
