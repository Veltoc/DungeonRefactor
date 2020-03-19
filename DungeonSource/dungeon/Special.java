package dungeon;

import java.io.Serializable;

public interface Special
extends Serializable
{
    String getName();

    default void doSpecial(DungeonCharacter actor, DungeonCharacter target) {
        doSpecial(actor);
    }

    default void doSpecial(DungeonCharacter actor) {

    }
}
