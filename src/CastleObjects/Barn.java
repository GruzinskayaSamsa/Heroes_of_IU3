package CastleObjects;

import BattleObjects.ArmyObject;
import BattleObjects.Peasant;
import MapObjects.CastleObject;
import MapObjects.HeroObject;
import UserInterfaces.User;
import UserInterfaces.ViewObject;

import java.util.Objects;

public class Barn extends UnitSaler {



    public Barn(User user, CastleObject castle) {
        super(user, castle);
        this.setImage("\uD83C\uDFE1");
        this.setColor(user.getColor());

        this.cost = 10;
        this.type = Peasant.class;
    }

}
