package CastleObjects;

import BattleObjects.Rebel;
import BattleObjects.Wyvern;
import MapObjects.CastleObject;
import UserInterfaces.User;

public class RottingHills extends UnitSaler{

    public RottingHills(User user, CastleObject castle) {
        super(user, castle);
        this.setImage("\uD83D\uDDFB");
        this.setColor(user.getColor());

        this.cost = 500;
        this.type = Rebel.class;
    }

}
