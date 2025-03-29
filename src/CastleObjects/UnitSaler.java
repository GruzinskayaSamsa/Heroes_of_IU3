package CastleObjects;

import BattleObjects.ArmyObject;
import BattleObjects.Snake;
import BattleObjects.Unit;
import MapObjects.CastleObject;
import UserInterfaces.User;

import java.util.Objects;

public abstract class UnitSaler extends CastleMapObject{

    private User user;
    private CastleObject castle;

    protected int cost;
    protected Class type;


    public UnitSaler(User user, CastleObject castle) {
        super("");

        this.user = user;
        this.castle = castle;
    }

    @Override
    public void doThings() {
        super.doThings();

        if (this.user.getMoney() >= this.cost && Objects.nonNull(this.castle.getHero())) {
            ArmyObject army = this.castle.getHero().getArmy();
            for (int i = 0; i < army.getSize(); i++) {
                if (Objects.equals(army.get(i).getClass(), this.type)) {
                    army.get(i).setNumber(army.get(i).getNumber()+1);
                    this.user.addMoney(-this.cost);
                    return;
                }
            }
            try {
                army.add((Unit) type.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            army.get(army.getSize()-1).setNumber(1);
            this.user.addMoney(-this.cost);
        }
    }

}
