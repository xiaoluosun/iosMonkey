package com.ios.monkey;

import com.alibaba.fastjson.JSONObject;
import macaca.client.MacacaClient;

/**
 * Created by hujiachun on 16/12/23.
 */
public class MonkeyTapEvent extends MonkeyEvent{
    private double x, y;
    private MacacaClient driver;


    public MonkeyTapEvent(MacacaClient driver, double x, double y) {
        super(MonkeyEvent.EVENT_TYPE_TAP);
        this.x = x;
        this.y = y;
        this.driver = driver;

    }


    public int injectEvent() throws Exception {
        System.out.println("sending Tap Event : Tap->(" + x + ", " + y + ")");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("x", x);
        jSONObject.put("y", y);
        driver.touch("tap", jSONObject);
        //driver.touchAsync("tap", jSONObject);
        return MonkeyEvent.INJECT_SUCCESS;
    }
}
