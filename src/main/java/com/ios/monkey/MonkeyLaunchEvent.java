package com.ios.monkey;

import com.ios.monkey.util.Shell;

import java.io.IOException;

/**
 * Created by hujiachun on 16/12/23.
 */
public class MonkeyLaunchEvent extends MonkeyEvent {
    private String UDID, BUNDLEID;

    public MonkeyLaunchEvent(String udid, String bundleid) {
        super(MonkeyEvent.EVENT_TYPE_LAUNCH);
        this.UDID = udid;
        this.BUNDLEID = bundleid;

    }

    public int injectEvent() throws Exception {

        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(2000);
                    Shell.exec("pkill idevicedebug");
                    System.out.println("idevicedebug stop");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        System.out.println("launch App:" + BUNDLEID);
//        new Thread(new Runnable() {
//            public void run() {
                try {
                    Shell.exec("idevicedebug -u " + UDID + " run " + BUNDLEID);
                    Thread.sleep(3000);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//            }
//        }).start();


        return MonkeyEvent.INJECT_SUCCESS;
    }
}
