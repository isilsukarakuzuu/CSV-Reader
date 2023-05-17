package main;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLogger {

	//logger object to keep track of logs
	public static Logger logger = Logger.getLogger("CSV-Reader Log");

	//initializes the logging process
	public static void initialize(){
		FileHandler fh;
		try {
			//initializes and adds FileHandler
			fh = new FileHandler("CSV-Reader.log");
			logger.addHandler(fh);
			//initializes and adds SimpleFormatter
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			
			//gives info about logger initialization
			logger.info("Logger Initialized");
			
		} catch (Exception e) {
			logger.log(Level.WARNING, "Exception :", e);

		}
	}
	
	// severe logger
	public static void writeSevere(String severe, Exception e) {
		logger.log(Level.SEVERE, severe + "\nException : ", e);
	}
	
	// warning logger
	public static void writeWarning(String warning) {
		logger.log(Level.WARNING, warning);
	}
}
