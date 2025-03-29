package BattleObjects;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ArmyObject {

    private ArrayList<Unit> units;

    public ArmyObject() {
        this.units = new ArrayList<Unit>();
    }

    public ArmyObject(ArrayList<Unit> units) {
        this.units = units;
    }

    public ArmyObject(JSONArray junits) {
        this.units = new ArrayList<Unit>();
        for (int i = 0; i < junits.length(); i++) {
            Unit unit;
            switch (junits.getJSONObject(i).getString("name")) {
                case ("Goblin"):
                    unit = new Goblin();
                    break;
                case ("Peasant"):
                    unit = new Peasant();
                    break;
                case ("Rebel"):
                    unit = new Rebel();
                    break;
                case ("Snake"):
                    unit = new Snake();
                    break;
                case ("Wyvern"):
                    unit = new Wyvern();
                    break;
                case ("BugWarrior"):
                    unit = new BugWarrior();
                    break;
                default:
                    unit = new Goblin();
                    break;
            }

            unit.setNumber(junits.getJSONObject(i).getInt("number"));
            this.units.add(unit);
        }
    }

    public ArmyObject(Class type, int armySize, int numberOfUnits) {
        this.units = new ArrayList<Unit>();

        int currentArmySize = armySize;
        for (int i = 0; i<numberOfUnits; i++) {

            try {
                Unit unit = (Unit) type.newInstance();
                unit.init(Math.min(currentArmySize, armySize / numberOfUnits));
                currentArmySize -= armySize/numberOfUnits;
                units.add(unit);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public int getForce() {
        int force = 0;
        for (int i = 0; i < this.units.size(); i++) {
            force += (this.units.get(i).getDamage() + this.units.get(i).getHp()) * this.units.get(i).getNumber();
        }
        return force;
    }

    public int getSize() {
        return this.units.size();
    }

    public Unit get(int index) {
        return this.units.get(index);
    }

    public void add(Unit unit) {
        this.units.add(unit);
    }

    public JSONArray getUnitsJSON() {
        JSONArray units = new JSONArray();

        for (int i = 0; i < this.units.size(); i++) {
            units.put(new JSONObject()
                    .put("name", this.units.get(i).getName())
                    .put("damage", this.units.get(i).getDamage())
                    .put("hp", this.units.get(i).getHp())
                    .put("number", this.units.get(i).getNumber()));
        }

        return units;
    }

}
