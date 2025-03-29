package CastleObjects;

import BattleObjects.ArmyObject;
import BattleObjects.Peasant;
import BattleObjects.Unit;
import MapObjects.CastleObject;
import MapObjects.HeroObject;
import UserInterfaces.User;
import UserInterfaces.ViewObject;

import java.util.Objects;

public class Stable extends CastleMapObject{

    private User user;
    private CastleObject castle;

    public Stable(User user, CastleObject castle) {
        super("");
        this.setImage("\uD83C\uDFA0");
        this.setColor(ViewObject.YELLOW);

        this.user = user;
        this.castle = castle;
    }

    @Override
    public void doThings() {
        super.doThings();
        if (this.user.getMoney() >= 250 && Objects.nonNull(this.castle.getHero())) {
            this.castle.getHero().setStamina(150);
            this.user.addMoney(-250);
        }
    }
}
