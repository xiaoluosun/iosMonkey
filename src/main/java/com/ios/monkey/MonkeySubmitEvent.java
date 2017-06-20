package com.ios.monkey;

import macaca.client.MacacaClient;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by hujiachun on 16/12/23.
 */
public class MonkeySubmitEvent extends MonkeyEvent {
    private MacacaClient driver;
    private int x, y;

    public MonkeySubmitEvent(MacacaClient driver, int x, int y) {
        super(MonkeyEvent.EVENT_TYPE_SUBMIT);
        this.x = x;
        this.y = y;
        this.driver = driver;

    }

    public int injectEvent() throws Exception {
        System.out.println("sending Event : Submit->(" + x + "," + y + ")");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("x", x);
        jSONObject.put("y", y);
        driver.touch("tap", jSONObject);
        //driver.touchAsync("tap", jSONObject);
        return MonkeyEvent.INJECT_SUCCESS;
    }


}
