package MapObjects;

import BattleObjects.Battlable;
import UserInterfaces.User;
import UserInterfaces.ViewObject;
import BattleObjects.ArmyObject;

public class EnemyObject extends PassiveMapObject implements Battlable {

    private ArmyObject army;
    protected final int defaultPrice = 10;


    public EnemyObject(String type, ArmyObject units) {
        super(type, "", ViewObject.RED);
        this.price = defaultPrice;
        this.army = units;
    }

    public EnemyObject(String type, Class typeOfArmy, int armySize) {
        super(type, "", ViewObject.RED);
        this.price = defaultPrice;
        this.army = new ArmyObject(typeOfArmy, armySize, (armySize>=6) ? 6 : 1);
    }


    @Override
    public void setType(String type) {
        super.setType(type);

        switch (type) {
            default:
                this.setImage("\uD83D\uDC80");
                break;
        }
    }

    @Override
    public ArmyObject getArmy() {
        return this.army;
    }

    @Override
    public void setArmy(ArmyObject army) {
        this.army = army;
    }

    @Override
    public User getUser() {
        return null;
    }

    public int getNumber() {
        int number = 0;
        for (int i = 0; i < this.army.getSize(); i++) {
            number += this.army.get(i).getNumber();
        }
        return number;
    }
}
