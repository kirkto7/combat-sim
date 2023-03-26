package Entities;

public class PlayerCharacter extends Entity{

    public PlayerCharacter(int[] stats, int baseHP, int speed, int totalLevels, int[] ABILITY_SCORES, int totalHP, int speed1) {
        super(stats, baseHP, speed, totalLevels, resistances, immunities);
    }

    public void addAction(Action a) {
        this.actions.add(a);
    }
    public void addBonusAction(BonusAction ba) {
        this.bonusActions.add(ba);
    }
    public void addReaction(Reaction r) {
        this.reactions.add(r);
    }
}
