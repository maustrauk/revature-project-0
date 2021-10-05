package com.revature.project0;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class LogHelper {
	public final Logger log = Logger.getLogger(LogHelper.class);
	
	public void callInfoLogger(String info)  {
		log.setLevel(Level.DEBUG);
		log.info(info);
	}
	
	public void callWarningLogger(String warning) {
		log.setLevel(Level.DEBUG);
		log.warn(warning);
	}
	
	public void callErrorLogger(String error) {
		log.setLevel(Level.DEBUG);
		log.error(error);
	}
	
	public void callExceptionLogger(Exception e) {
		log.setLevel(Level.DEBUG);
		log.debug(e);
	}
	
	public void callFatalLogger(Exception e) {
		log.setLevel(Level.DEBUG);
		log.fatal(e);
	}
}

//public void callStaticLogger() {
//if(myData == 0) {
//	MainDriver.log.warn("myData variable is: " + myData);
//}
//
//}
//
//public void callInstanceLogger() {
//this.myData = 42;
//log2.debug("myData variable is: " + myData);
//}

//	log.setLevel(Level.DEBUG);

//if (onLog) {
//log.setLevel(Level.DEBUG);
//
//log.trace("Ray Tracing");
//log.debug("Sqauishing bugs");
//log.info("It is secret info");
//log.warn("It's dangerous to go alone");
//log.error("thats a big NO-NO");
//log.fatal("Pineapples on pizza");
//
//LogHelper lHelp = new LogHelper();
//
//lHelp.callStaticLogger();
//lHelp.callInstanceLogger();
//}

//log.info("It is secret info");
