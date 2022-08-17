package main;

import java.io.File;
import java.nio.file.Path;


public class Mover extends Transporter {
	
	private Reader readerClass = new Reader(); //for parsing
	private String sourcePath; //the directory of the files that are initially located here
	private String destinationPath; //the directory of the files that will eventually be located here
	
	//loops through lines, checks multidimensional array(AllDirectoriesInLines) for source and destination path 
	//and moves directories(moveDir(from,to))
	public void moving() {
		//runs the reader to parse input lines in input file
		getReaderClass().parsing();
		
		 
		for (int i = 0; i < readerClass.getAllFileLines().size(); i++) {
			//gets source path and destination path
			sourcePath = readerClass.getAllDirectoriesInLines()[i][0];
			destinationPath = readerClass.getAllDirectoriesInLines()[i][1];
			
			//creates an file object for directories
			File from = new File(sourcePath);
	        File to = new File(destinationPath);
	        
	        //moves the files if the required conditions are met
	        moveDir(from, to);
		}
		
	}

	//moves the files in source directory to destination directory
    private static boolean moveDir(File from , File to)
    {
    	//creates an path object for directories, from given file object parameters 
    	Path source = from.toPath();
    	Path destination = to.toPath();
    	
    	//checks if source directory is valid
    	if (!source.toFile().isDirectory()) {
    		MyLogger.writeWarning("Invalid Source Directory called: " + source);
    	}
    	//checks if destination directory is valid
    	if (!destination.toFile().isDirectory()) {
    		MyLogger.writeWarning("Invalid Destination Directory called: " + destination);
    	}
    	
    	//if source and destination paths are both valid and non-empty --copies all files--
        if (source.toFile().isDirectory() && destination.toFile().isDirectory())
        {	
        	//checks if source directory is not empty
        	if (from.listFiles().length == 0) {
        		MyLogger.writeWarning("Source Directory is empty: " + from.getPath());
        	}
        		//loops through files in given source directory, copies all files and deletes it
            	for (File f: from.listFiles()) {
            		String newName = to.getPath() + "\\" + f.getName(); //gets new path and file name, then combine them for new name with updated path
            		if(from.listFiles().length != 0) { //checks if destination directory is empty
            		for (File sameFileInDestination: to.listFiles()) {
            		if(sameFileInDestination.getName().equals(f.getName())) { //checks if destination and source directory has same file names
            			MyLogger.writeWarning("File with this name already exist: " + newName);
            		}}}
            	if(f.renameTo(new File(newName))){
            		// if file copied successfully then delete the original file
            		f.delete();
            		}
            	}   
        }
		return true;  
    }
		
    
    //getters and setters
	public Reader getReaderClass() {
		return readerClass;
	}

	public void setReaderClass(Reader readerClass) {
		this.readerClass = readerClass;
	}
}
