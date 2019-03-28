package com.pingidentity.wait;

import com.pingidentity.config.SeleniumDriver;

public class StaticWait {

	private static StaticWait instance = new StaticWait();
	  
	public static StaticWait getInstance() {
		return instance;
	}
	
	public void waitForMilliSeconds(int milliSec) {
		try {
			Thread.sleep(milliSec);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
	
}
