package CastleObjects;

import BattleObjects.BugWarrior;
import BattleObjects.Wyvern;
import MapObjects.CastleObject;
import UserInterfaces.User;

public class MountainNest extends UnitSaler{

    public MountainNest(User user, CastleObject castle) {
        super(user, castle);
        this.setImage("\uD83C\uDFD4");
        this.setColor(user.getColor());

        this.cost = 150;
        this.type = Wyvern.class;
    }

}
