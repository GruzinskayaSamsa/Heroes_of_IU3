package test;

import BattleObjects.ArmyObject;
import BattleObjects.Peasant;
import MapObjects.HeroObject;
import UserInterfaces.User;
import UserInterfaces.ViewObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroObjectTest {

    HeroObject hero;
    User user;

    @BeforeEach
    void heroInit() {
        user = new User("Vova", 0, ViewObject.YELLOW);
        hero = new HeroObject("0", 0, new ArmyObject(Peasant.class, 1, 1), user);
    }

    @DisplayName("Возврат стартовой конфигурации в виде JSON")
    @Test
    void getJSON() {
        Assertions.assertEquals(new JSONObject()
                .put("army", new JSONArray()
                        .put(new JSONObject()
                                .put("name", "Peasant")
                                .put("damage", 1)
                                .put("hp", 1)
                                .put("number", 1)))
                .put("id", 0).toString(),
                hero.getJSON().toString());
    }

    @DisplayName("Проверка работы стамины")
    @Test
    void staminaCheck() {
        hero.setStamina(100);
        assertAll(() -> assertEquals(100, hero.getStamina()),
                () -> assertEquals(true, hero.subtractStamina(5)),
                () -> assertEquals(false, hero.subtractStamina(100)),
                () -> assertEquals(95, hero.getStamina()));
    }
}