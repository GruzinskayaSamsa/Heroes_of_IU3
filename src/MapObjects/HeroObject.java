package MapObjects;

import BattleObjects.Battlable;
import BattleObjects.ArmyObject;
import UserInterfaces.User;
import org.json.JSONArray;
import org.json.JSONObject;

public class HeroObject extends MapObject implements Battlable {

    private int id;
    private int stamina;
    private final int defaultStamina = 100;

    private User user;

    private ArmyObject army;

    public HeroObject(String type, int id, ArmyObject army, User user) {
        super(type, "", user.getColor());
        this.id = id;
        this.stamina = defaultStamina;
        this.user = user;
        this.army = army;
    }

    @Override
    public void setType(String type) {
        super.setType(type);

        switch (type) {
            case ("0"):
                this.setImage("\uD83E\uDD35");
                break;
            case ("1"):
                this.setImage("\uD83D\uDC82");
                break;
            default:
                this.setImage("\uD83D\uDE46");
                break;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public boolean subtractStamina(int value) {
        if (this.stamina - value >= 0) {
            this.stamina -= value;
            return true;
        }
        return false;
    }

    @Override
    public User getUser() {
        return this.user;
    }

    @Override
    public ArmyObject getArmy() {
        return this.army;
    }

    @Override
    public void setArmy(ArmyObject army) {
        this.army = army;
    }


    public JSONObject getJSON() {
        return new JSONObject()
                .put("army", this.army.getUnitsJSON())
                .put("id", this.getId());
    }
}
