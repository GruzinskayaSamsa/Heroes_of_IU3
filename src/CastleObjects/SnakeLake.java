package CastleObjects;

import BattleObjects.ArmyObject;
import BattleObjects.BugWarrior;
import BattleObjects.Snake;
import MapObjects.CastleObject;
import UserInterfaces.User;

import java.util.Objects;

public class SnakeLake extends UnitSaler{



    public SnakeLake(User user, CastleObject castle) {
        super(user, castle);
        this.setImage("\uD83C\uDF00");
        this.setColor(user.getColor());

        this.cost = 70;
        this.type = Snake.class;
    }

}
