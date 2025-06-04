package UserInterfaces;

import BattleObjects.ArmyObject;
import BattleObjects.Battlable;
import MapObjects.EnemyObject;
import MapObjects.HeroObject;
import MapObjects.LandscapeObject;
import MapObjects.PassiveMapObject;
import BattleObjects.Battle;
import com.company.QuasiConsole;
import com.company.WebClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class GameInterface implements Runnable{

    private KeyboardAdapter keyboard;
    private InputInfo inputInfo;

    private volatile int active;
    private Battle battle;
    private int battlePosition;

    private int currentHero;
    private int currentPlayer;
    private int day;

    private User user;
    private User enemyUser;

    private PlayField playField;

    public GameInterface(User user, User enemyUser, int currentPlayer) {
        this.playField = new PlayField();
        playField.init(40, 20, true, true, true, user, enemyUser);
        //this.heroes = new ArrayList<HeroObject>();
        this.user = user;
        this.enemyUser = enemyUser;
        this.initKeyListening();

        this.day = 1;
        this.currentPlayer = currentPlayer;
        this.keyboard = new KeyboardAdapter();
    }

    public void reset(JSONObject json) {
        this.currentPlayer = json.getInt("currentPlayer");
        this.playField = new PlayField(json, user, enemyUser);

        JSONArray zeroHeroes = json.getJSONArray("players").getJSONObject(0).getJSONArray("armies");
        JSONArray oneHeroes = json.getJSONArray("players").getJSONObject(1).getJSONArray("armies");

        User zeroUser = (this.user.getNumber() == 0) ? this.user : this.enemyUser;
        User oneUser = (this.user.getNumber() == 0) ? this.enemyUser : this.user;

        for (int i = 0; i < zeroHeroes.length(); i++) {
            zeroUser.addHero(new HeroObject("0", zeroHeroes.getJSONObject(i).getInt("id"),
                    new ArmyObject(zeroHeroes.getJSONObject(i).getJSONArray("army")), zeroUser));
            int position = zeroHeroes.getJSONObject(i).getInt("position");
            System.out.println(position);
            this.setHeroPosition(zeroHeroes.getJSONObject(i).getInt("id"),
                    position % this.playField.getWidth(),
                    position / this.playField.getWidth());
        }

        for (int i = 0; i < oneHeroes.length(); i++) {
            oneUser.addHero(new HeroObject("0", oneHeroes.getJSONObject(i).getInt("id"),
                    new ArmyObject(oneHeroes.getJSONObject(i).getJSONArray("army")), oneUser));
            int position = oneHeroes.getJSONObject(i).getInt("position");
            this.setHeroPosition(oneHeroes.getJSONObject(i).getInt("id"),
                    position % this.playField.getWidth(),
                    position / this.playField.getWidth());
        }

        this.initKeyListening();
    }


    private void initKeyListening() {
        this.inputInfo = new InputInfo();

        this.active = 1;
        this.battle = new Battle();

        this.currentHero = 0;
    }

    private void keyWorkMoving() {
        this.setInputInfo(this.keyboard.getInfo());

        if (this.inputInfo.isShiftE()) {
            this.day = (this.user.getNumber() == 1) ? this.day+1 : this.day;
            this.currentPlayer = (this.user.getNumber() == 1) ? 0 : 1;
            WebClient.post(this.toJSON().toString());
        }

        if (this.inputInfo.isE()) this.active = 3;

        if (this.inputInfo.isK1() && 1 <= this.user.getNumberOfHeroes()) this.currentHero = 0;
        if (this.inputInfo.isK2() && 2 <= this.user.getNumberOfHeroes()) this.currentHero = 1;
        if (this.inputInfo.isK3() && 3 <= this.user.getNumberOfHeroes()) this.currentHero = 2;
        if (this.inputInfo.isK4() && 4 <= this.user.getNumberOfHeroes()) this.currentHero = 3;
        if (this.inputInfo.isK5() && 5 <= this.user.getNumberOfHeroes()) this.currentHero = 4;

        int xMove = 0, yMove = 0;

        if (this.inputInfo.isW()) yMove -= 1;
        if (this.inputInfo.isS()) yMove += 1;
        if (this.inputInfo.isA()) xMove -= 1;
        if (this.inputInfo.isD()) xMove += 1;

        try {
            int id = this.user.getHeroByNumber(this.currentHero).getId();
            int targetX = this.getHeroPositionXY(id).get(0) + xMove, targetY = this.getHeroPositionXY(id).get(1) + yMove;



            if (this.user.getHeroByNumber(this.currentHero).getStamina() - this.playField.getTerritory(targetX, targetY).getPrice() >= 0
                && (xMove != 0 || yMove != 0)) {

                PassiveMapObject targetMapObject = this.playField.getTerritory(this.getHeroPositionXY(id).get(0) + xMove, this.getHeroPositionXY(id).get(1) + yMove);


                try {
                    if (targetMapObject.getHero() instanceof Battlable) {
                        if (Objects.equals(targetMapObject.getHero().getUser(), this.user)) {
                            return;
                        }
                        else {
                            this.battle.resetBattle(this.getHeroPosition(id) + xMove + yMove*this.playField.getWidth());

                            this.battle.setLeftHero(this.user.getHeroByNumber(this.currentHero));
                            this.battle.setRightHero(targetMapObject.getHero());

                            this.startBattle();
                        }
                    } else if (targetMapObject instanceof Battlable) {
                        this.battle.resetBattle(this.getHeroPosition(id) + xMove + yMove*this.playField.getWidth());

                        this.battle.setLeftHero(this.user.getHeroByNumber(this.currentHero));
                        this.battle.setRightHero((Battlable) targetMapObject);

                        this.startBattle();
                        return;
                    }
                } catch (NullPointerException e) {}

                PassiveMapObject res = this.setHeroPosition(id, this.getHeroPositionXY(id).get(0) + xMove, this.getHeroPositionXY(id).get(1) + yMove);
                if (Objects.nonNull(res)) {
                    this.user.getHeroByNumber(this.currentHero).subtractStamina(this.playField.getTerritory(targetX, targetY).getPrice());
                }
            }
        } catch (IndexOutOfBoundsException e) {}

    }


    private void keyWorkCastle() {
        this.setInputInfo(this.keyboard.getInfo());

        if (this.inputInfo.isE()) {
            this.active = 1;
            return;
        }

        if (this.inputInfo.isF() && Objects.isNull(this.playField.getTerritory(0 ,0).getHero())) {
            this.inputInfo.setF(false);
            this.user.getCastle().getBuilding(11).doThings();
            this.setHeroPosition(this.user.getHeroByNumber(this.user.getNumberOfHeroes()-1).getId(), 0, 0);
        }

        if (this.inputInfo.isK1() && Objects.nonNull(this.playField.getTerritory(0 ,0).getHero())) {
            this.inputInfo.setK1(false);
            this.user.getCastle().getBuilding(0).doThings();
        }

        if (this.inputInfo.isK2() && Objects.nonNull(this.playField.getTerritory(0 ,0).getHero())) {
            this.inputInfo.setK2(false);
            this.user.getCastle().getBuilding(1).doThings();
        }

        if (this.inputInfo.isK3() && Objects.nonNull(this.playField.getTerritory(0 ,0).getHero())) {
            this.inputInfo.setK3(false);
            this.user.getCastle().getBuilding(2).doThings();
        }

        if (this.inputInfo.isK4() && Objects.nonNull(this.playField.getTerritory(0 ,0).getHero())) {
            this.inputInfo.setK4(false);
            this.user.getCastle().getBuilding(3).doThings();
        }

        if (this.inputInfo.isQ() && Objects.nonNull(this.playField.getTerritory(0 ,0).getHero())) {
            this.inputInfo.setQ(false);
            this.user.getCastle().getBuilding(4).doThings();
        }

        if (this.inputInfo.isS() && Objects.nonNull(this.playField.getTerritory(0 ,0).getHero())) {
            this.inputInfo.setS(false);
            this.user.getCastle().getBuilding(5).doThings();
        }

    }


    public void addHero(HeroObject hero) {
        this.user.addHero(hero);
    }

    public PassiveMapObject setHeroPosition(int id, int x, int y) {
        if (x < 0 || x >= this.playField.getWidth() || y < 0 || y >= this.playField.getHeight())
            return null;

        HeroObject hero = this.user.getHeroByID(id);
        if (Objects.isNull(hero)) {
            hero = this.enemyUser.getHeroByID(id);
            if (Objects.isNull(hero))
                return null;
        }
        return this.playField.setHero(hero, x, y);
    }

    public ArrayList<Integer> getHeroPositionXY(int id) {
        int position = this.playField.getHeroPosition(id);
        ArrayList<Integer> ans = new ArrayList<Integer>();
        ans.add(position % this.playField.getWidth());
        ans.add(position / this.playField.getWidth());
        return ans;
    }

    public int getHeroPosition(int id) {
        return this.playField.getHeroPosition(id);
    }

    public void deleteHero(int number) {
        this.playField.deleteHero(this.user.getHeroByNumber(number).getId());
        this.user.removeHeroByNumber(number);
        this.currentHero = 0;
    }

    public void deleteHeroById(int id) {
        this.playField.deleteHero(id);
        if (this.user.removeHeroByID(id)) {
            return;
        }
        else {
            this.enemyUser.removeHeroByID(id);
        }
        this.currentHero = 0;
    }

    public void destroyTerritory(int position) {
        this.playField.setTerritory(position % this.playField.getWidth(), position / this.playField.getWidth(), new LandscapeObject("8"));
    }


    public void setInputInfo(InputInfo inputInfo) {
        this.inputInfo = inputInfo;
    }

    public void setActive(int active) {
        this.active = active;
    }

    private void startBattle() {
        this.active = 2;
    }

    public boolean isActive() {
        return this.currentPlayer == this.user.getNumber();
    }

    public int getWidth() {
        return this.playField.getWidth();
    }

    public int getHeight() {
        return this.playField.getHeight();
    }

    public User getUser() {
        return this.user;
    }

    public User getEnemyUser() {
        return this.enemyUser;
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject()
                .put("day", this.day)
                .put("gameState", this.active)
                .put("currentPlayer", this.currentPlayer)
                .put("totalPlayers", 2)
                .put("field", new JSONObject()
                        .put("data", this.playField.getLandscapeTypes())
                        .put("width", this.playField.getWidth())
                        .put("height", this.playField.getHeight()))
                .put("treasures", this.playField.getTreasures())
                .put("enemies", this.playField.getEnemies())
                .put("players", new JSONArray());
        if (this.user.getNumber() == 0) {
            json.getJSONArray("players")
                    .put(this.user.getUserJSON())
                    .put(this.enemyUser.getUserJSON());
        } else {
            json.getJSONArray("players")
                    .put(this.enemyUser.getUserJSON())
                    .put(this.user.getUserJSON());
        }

        for (int i = 0; i < json.getJSONArray("players").getJSONObject(0).getJSONArray("armies").length(); i++) {
            json.getJSONArray("players").getJSONObject(0).getJSONArray("armies").getJSONObject(i)
                    .put("position", this.getHeroPosition(json.getJSONArray("players").getJSONObject(0).getJSONArray("armies").getJSONObject(i).getInt("id")));
        }

        for (int i = 0; i < json.getJSONArray("players").getJSONObject(1).getJSONArray("armies").length(); i++) {
            json.getJSONArray("players").getJSONObject(1).getJSONArray("armies").getJSONObject(i)
                    .put("position", this.getHeroPosition(json.getJSONArray("players").getJSONObject(1).getJSONArray("armies").getJSONObject(i).getInt("id")));
        }
        return json;
    }


    @Override
    public void run() {
        while (true) {
            if (this.user.getNumberOfHeroes() == 0 || this.enemyUser.getNumberOfHeroes() == 0) {
                this.active = 4;
            }


            switch (this.active) {
                case (1):
                    if (this.isActive()) this.keyWorkMoving();
                    this.playField.print();
                    try {
                        System.out.println("Current hero: " + this.user.getHeroByNumber(this.currentHero).getId() + " "
                                + this.user.getHeroByNumber(this.currentHero).getStamina());
                        System.out.println("Your force: " + this.user.getHeroByNumber(this.currentHero).getArmy().getForce());
                        System.out.println("Your money: " + this.user.getMoney());
                        System.out.println(this.isActive() + " " + this.currentPlayer);
                    } catch (IndexOutOfBoundsException e){ }
                    break;
                case (2):
                    this.battle.print();
                    if (this.battle.isEnd()) {
                        int position = this.battle.getPosition();
                        if (this.battle.getWinner() == 0) {
                            HeroObject looseHero = this.playField.getTerritoryByPosition(position).getHero();
                            if (Objects.nonNull(looseHero)) {
                                this.deleteHeroById(looseHero.getId());
                            }
                            this.destroyTerritory(position);
                            //this.playField.setHero(this.user.getHeroByNumber(this.currentHero), position % this.playField.getWidth(), position / this.playField.getWidth());
                        } else {
                            this.deleteHero(this.currentHero);
                        }
                        this.active = 1;
                    }
                    break;
                case(3):
                    this.keyWorkCastle();
                    this.user.getCastle().print();
                    break;
                case (4):
                    QuasiConsole.clearConsole();
                    System.out.println("End of Game");
                    break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
