package CastleObjects;

import BattleObjects.ArmyObject;
import BattleObjects.BugWarrior;
import BattleObjects.Peasant;
import BattleObjects.Unit;
import MapObjects.CastleObject;
import UserInterfaces.User;

import java.util.Objects;

public class Hollow extends UnitSaler {

    public Hollow(User user, CastleObject castle) {
        super(user, castle);
        this.setImage("\uD83D\uDC8B");
        this.setColor(user.getColor());

        this.cost = 30;
        this.type = BugWarrior.class;
    }

}
