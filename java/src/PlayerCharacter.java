import java.util.ArrayList;
import java.util.HashMap;

public class PlayerCharacter {
    private final int[] ABILITY_SCORES;
    private final int totalHP;
    private final int speed;
    private ArrayList<Action> actions;
    private ArrayList<BonusAction> bonusActions;
    private ArrayList<Reaction> reactions;

    public PlayerCharacter(int[] stats, int baseHP, int speed, int totalLevels) {
        this.ABILITY_SCORES = stats;
        this.totalHP = baseHP + (ABILITY_SCORES[2] * totalLevels);
        this.speed = speed;
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
