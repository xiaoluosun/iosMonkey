package com.ios.monkey.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by hujiachun on 16/12/21.
 */
public class Shell {
	
	public static void launchAPP(final String UDID, final String BUNDLEID, final long startTime, final String TIMING) throws IOException, InterruptedException {	
        final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();  
		Runnable runnable = new Runnable() {  
            public void run() {  
				try {
		        	long endTime = System.currentTimeMillis();
		        	if((endTime - startTime) > (Integer.parseInt(TIMING) * 60 * 1000)) {
		        		service.shutdownNow();
		        	}
		        	
					if(UDID.contains("-")) {
						System.out.println("=======启动模拟器的app守护进程=======");
						String catLog = "tail -n 3 -f /Users/testmac/Library/Logs/CoreSimulator/" + UDID + "/system.log";
						Process pp = Runtime.getRuntime().exec(catLog);
						BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(pp.getInputStream()));

		                String line = bufferedReader.readLine();
//		                while ((line = bufferedReader.readLine()) != null) {
		                    if(line.contains("testMacdeiMac")){
		                    	String str = line.split(" ")[4];
		                    	String curbundleid = str.split("\\[")[0];
		                    	if(curbundleid.contains("LuoJiFM-IOS")) {
		                    		
		                    	} else if (curbundleid.contains("CoreSimulatorBridge")) {
		                    		
		                    	} else {
		                    		System.out.println("==当前启动的APP bundleid是【"+curbundleid+"】，非测试APP，重新呼起测试APP====");
		                     		Runtime.getRuntime().exec("xcrun simctl launch booted " + BUNDLEID);
		                    	}
		                    }
//		                }
		                
		                bufferedReader.close();
		                
					} else {
						System.out.println("=======启动真机的app守护进程=======");
						Process pp = Runtime.getRuntime().exec("/usr/local/bin/idevicesyslog -u "+UDID);
						BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(pp.getInputStream()));
	
		                String line;
		                String curbundleid = BUNDLEID;
		                while ((line = bufferedReader.readLine()) != null) {
		                	System.out.println("=============="+line);
		                    if(line.contains("HW kbd: currently")){
		                    	System.out.println("=============="+line);
		                    	if(line.split(" ")[8].equals("currently")){
		                    		curbundleid = line.split(" ")[9];
		                    	}else{
		                    		curbundleid = line.split(" ")[10];
		                    	}
		                    	//System.out.println("=============="+curbundleid);
		                    	
		                    	if(!curbundleid.equals("LuoJiFM-IOS")){
		                     		System.out.println("==当前启动的APP bundleid是<"+curbundleid+">,非测试APP，重新呼起测试APP====");
		                     		Runtime.getRuntime().exec("/usr/local/bin/idevicedebug -u " + UDID + " run " + BUNDLEID);
		                     	}
		                    }
		                }
		                
		                bufferedReader.close();
					}
	                
				}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
            }  
        };  
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间  
        service.scheduleAtFixedRate(runnable, 30, 10, TimeUnit.SECONDS);  
    }



    public static void exec(String command) throws IOException, InterruptedException {
        Process p;
        p = Runtime.getRuntime().exec(command);
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
//        String line;
//        while ((line = bufferedReader.readLine()) != null) {
//            System.out.println(line);
//        }

//        bufferedReader.close();
        p.waitFor();
        p.destroy();

    }
    
    
    public static void main(String[] args) throws IOException, Exception {
//		launchAPP("1C0B46D2-E3A8-4151-843B-E12193169967","com.luojilab.LuoJiFM-IOS");
	}
}
