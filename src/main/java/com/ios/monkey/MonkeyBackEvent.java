package com.ios.monkey;

import com.alibaba.fastjson.JSONObject;
import macaca.client.MacacaClient;

/**
 * Created by hujiachun on 16/12/23.
 */
public class MonkeyBackEvent extends MonkeyEvent {
    private int backX, backY;
    private MacacaClient driver;

    public MonkeyBackEvent(MacacaClient driver, int width, int height) {
        super(MonkeyEvent.EVENT_TYPE_BACK);
        this.backX = width;
        this.backY = height;
        this.driver = driver;

    }

    public int injectEvent() throws Exception {
        System.out.println("sending Event : Back->(" + backX + "," + backY + ")");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("x", backX);
        jSONObject.put("y", backY);
        driver.touch("tap", jSONObject);
        //driver.touchAsync("tap", jSONObject);
        return MonkeyEvent.INJECT_SUCCESS;
    }
}
