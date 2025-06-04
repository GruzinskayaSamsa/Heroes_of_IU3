package test;

import BattleObjects.ArmyObject;
import BattleObjects.Peasant;
import MapObjects.CastleObject;
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

class UserTest {

    User user;
    CastleObject castle;

    @BeforeEach
    void initUser() {
        user = new User("Vova", 0, ViewObject.YELLOW);
        user.addHero(new HeroObject("0", 0, new ArmyObject(Peasant.class, 1, 1), user));

        castle = new CastleObject("0", "", user);
    }

    @DisplayName("Возврат без замка в виде JSON")
    @Test
    void removeCastle() {
        user.removeCastle();

        Assertions.assertEquals(new JSONObject()
                        .put("castle", new JSONObject())
                        .put("armies", new JSONArray()
                                .put(new JSONObject()
                                        .put("army", new JSONArray()
                                                .put(new JSONObject()
                                                        .put("name", "Peasant")
                                                        .put("damage", 1)
                                                        .put("hp", 1)
                                                        .put("number", 1)))
                                        .put("id", 0))).toString(),
                user.getUserJSON().toString());

    }

    @DisplayName("Возврат без героев в виде JSON")
    @Test
    void removeHeroes() {
        user.removeHeroByID(0);

        Assertions.assertEquals(new JSONObject()
                        .put("castle", new JSONObject().put("units", new JSONArray()))
                        .put("armies", new JSONArray()).toString(),
                user.getUserJSON().toString());
    }

    @DisplayName("Возврат стартовой конфигурации в виде JSON")
    @Test
    void getUserJSON() {
        Assertions.assertEquals(new JSONObject()
                .put("castle", new JSONObject().put("units", new JSONArray()))
                .put("armies", new JSONArray()
                        .put(new JSONObject()
                        .put("army", new JSONArray()
                                .put(new JSONObject()
                                    .put("name", "Peasant")
                                    .put("damage", 1)
                                    .put("hp", 1)
                                    .put("number", 1)))
                        .put("id", 0))).toString(),
                user.getUserJSON().toString());
    }
}