package test;

import BattleObjects.ArmyObject;
import BattleObjects.Peasant;
import BattleObjects.Unit;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArmyObjectTest {

    ArmyObject army;

    @BeforeEach
    void setUp() {
        army = new ArmyObject(Peasant.class, 1, 1);
    }

    @DisplayName("Возврат стартовой конфигурации в виде JSON")
    @Test
    void getUnitsJSONDefault() {
        Assertions.assertEquals(new JSONArray().put(new JSONObject()
                                            .put("name", "Peasant")
                                            .put("damage", 1)
                                            .put("hp", 1)
                                            .put("number", 1)).toString(),
                army.getUnitsJSON().toString());
    }

    @DisplayName("Возврат после добавления юнитов в виде JSON")
    @Test
    void getUnitsJSONAdded() {
        Unit units = new Peasant();
        units.init(3);
        army.add(units);
        Assertions.assertEquals(new JSONArray()
                .put(new JSONObject()
                        .put("name", "Peasant")
                        .put("damage", 1)
                        .put("hp", 1)
                        .put("number", 1))
                .put(new JSONObject()
                        .put("name", "Peasant")
                        .put("damage", 1)
                        .put("hp", 1)
                        .put("number", 3)).toString(),
                army.getUnitsJSON().toString());
    }


}