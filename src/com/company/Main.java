package com.company;

import BattleObjects.ArmyObject;
import BattleObjects.Goblin;
import BattleObjects.Peasant;
import MapObjects.HeroObject;
import UserInterfaces.GameInterface;
import UserInterfaces.PlayField;
import UserInterfaces.User;
import UserInterfaces.ViewObject;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.http.HttpResponse;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {

    boolean w_flag = false;
    boolean a_flag = false;
    boolean s_flag = false;
    boolean d_flaf = false;

    public static void main(String[] args) {



//        JSONObject json = new JSONObject().put("gameState", 0)
//                .put("currentPlayer", 1)
//                .put("totalPlayers", 3)
//                .put("field", new JSONObject()
//                        .put("data", "0010102439582376823767356258761036852378186538745028901")
//                        .put("width", 40)
//                        .put("height", 20))
//                .put("treasures", new JSONArray())
//                .put("enemies", new JSONArray())
//                .put("players", new JSONArray());


//        WebClient client = new WebClient();
//        HttpResponse resp = client.get();
//        System.out.println(resp.body().toString());




        Scanner scanner = new Scanner(System.in);
        System.out.println("Какого имя нового бога?");
        String name = scanner.nextLine();
        System.out.println("0 или 1?");
        int number = scanner.nextInt();



        User user = new User(name, number, ViewObject.PURPLE);
        user.addMoney(10000);
        User enemyUser = new User("Enemy", (number == 0) ? 1 : 0, ViewObject.CYAN);
        enemyUser.addMoney(10000);
        GameInterface game = new GameInterface(user, enemyUser, 0);

        WebThread client = new WebThread(game);
        client.start();

        enemyUser.addHero(new HeroObject("0", 100, new ArmyObject(Peasant.class, 1, 1), enemyUser));

        user.addHero(new HeroObject("0", 0, new ArmyObject(Peasant.class, 1, 1), user));

        if (number == 0) {
            game.setHeroPosition(0, 0, 0);
            game.setHeroPosition(100, game.getWidth() - 1, game.getHeight() - 1);
        } else {
            game.setHeroPosition(100, 0, 0);
            game.setHeroPosition(0, game.getWidth() - 1, game.getHeight() - 1);
        }


        Thread gameThread = new Thread(game);
        gameThread.start();

        WebClient.post(game.toJSON().toString());



//        PlayField field = new PlayField(new JSONObject(WebClient.get().body().toString()));
//        field.print();


//        json = game.toJSON();

        //System.out.println(WebClient.post(json.toString()).body().toString());

        //System.out.println(resp.body());


    }

}
