
public interface Special {
    String getName();

    default void doSpecial(DungeonCharacter actor, DungeonCharacter target) {
        doSpecial(actor);
    }

    default void doSpecial(DungeonCharacter actor) {

    }
}
