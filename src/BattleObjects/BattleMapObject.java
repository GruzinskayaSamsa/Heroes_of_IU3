package BattleObjects;

import MapObjects.MapObject;

import java.util.Objects;

public class BattleMapObject extends MapObject {

    private Unit unit;

    public BattleMapObject() {
        super("");
        this.unit = null;
    }

    public BattleMapObject(Unit unit) {
        super("");
        this.unit = unit;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public void resetUnit() {
        this.unit = null;
    }

    @Override
    public String getImage() {
        if (Objects.nonNull(this.unit)) {
            return this.unit.getImage();
        }
        return super.getImage();
    }

    @Override
    public void setType(String type) {
        super.setType(type);

        switch (type) {
            case ("0"):
                this.setImage("❎");
                break;
            default:
                this.setImage("❎");
                break;
        }
    }
}
