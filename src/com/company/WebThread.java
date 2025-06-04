package com.company;

import MapObjects.HeroObject;
import UserInterfaces.GameInterface;
import org.json.JSONObject;

import java.net.http.HttpResponse;
import java.util.Objects;

public class WebThread extends Thread{

    private HttpResponse<?> resp;

    private GameInterface game;

    public WebThread(GameInterface game) {
        this.resp = WebClient.get();
        this.setDaemon(true);
        this.game = game;
    }

    public JSONObject getData() {
        return new JSONObject(this.resp.body().toString());
    }


    @Override
    public void run() {
        while (true) {
            if (this.game.isActive()) {
                WebClient.post(this.game.toJSON().toString());
            } else {
                resp = WebClient.get();
                this.game.reset(new JSONObject(resp.body().toString()));
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
