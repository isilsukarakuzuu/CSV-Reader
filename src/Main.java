//A code to read from an csv file and move files in one given directory to another
// @author Isil Su Karakuzu
package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		//start and initialize logger
		MyLogger.initialize();
		
		Mover moverClass = new Mover();
		int lineCount = 0; //counts the lines in input file
		//reading a file -start-
			File inFile = new File(args[0]);
			Scanner reader;
				
			try {
				reader = new Scanner(inFile);
					
				//finding file's line count
				while(reader.hasNextLine()) {
					//adding lines to an ArrayList named "allFileLines" to store lines
					moverClass.getReaderClass().getUneditedAllFileLines().add(reader.nextLine());
					lineCount++;
				      }
				//checking if file is empty
				if (lineCount == 0) {
					MyLogger.writeWarning("This file is empty");
				}
					}
			catch (FileNotFoundException e) {
				MyLogger.writeSevere("Cannot find input file", e);
				return;
			}
				reader.close();
		//reading a file -finish-
				
			//checks the files syntax	
			moverClass.getReaderClass().syntaxChecker();
			//parses the lines to obtain directories, then move files in one given directory to another
			moverClass.moving();
			
			//finish the program
			MyLogger.logger.info("End of Program");
	}

}
