package com.ios.monkey.screenshot;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;

import com.ios.monkey.util.Utils;

public class Screenshot {
	
	public static void screenshot(String UDID, int x, int y) {
		String path = System.getProperty("user.dir");
		String screenshotPath = path + "/output/ios_monkey.jpeg";
		String screenshotPath2 = path + "/output/ios_monkey_" + Utils.currentTime() + "_" + x + "_" + y +".jpeg";
		if(UDID.contains("-")) {
			try {
				Process pp = Runtime.getRuntime().exec("xcrun simctl io booted screenshot --type=jpeg " + screenshotPath);
				pp.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			try {
				Runtime.getRuntime().exec("idevicescreenshot -u " + UDID + " " + screenshotPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		File file = new File(screenshotPath);
		if(file.exists() && file.length() > 0) {
			new ModifyPic(screenshotPath, screenshotPath2, "这里这里", "STYLE_ITALIC", 50, x, y, Color.RED);
			System.out.println("create screenshot : " + screenshotPath2);
			file.delete();
		} else if (file.exists() && file.length() <= 0){
			file.delete();
		} else {
			System.out.println("截图失败，并没有找到截图文件！");
		}
	}
	
	@Test
	public void f() {
		screenshot("1C0B46D2-E3A8-4151-843B-E12193169967", 600, 900);
	}
}
