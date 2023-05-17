package main;

import java.util.ArrayList;
import java.util.regex.*;  
import java.util.List;

public class Reader implements Parser{
	
	private List<String> uneditedAllFileLines = new ArrayList<>(); //stores all lines in input file
	private List<String> allFileLines = new ArrayList<>(); //stores all CORRECT lines in input file
	private String[][] allDirectoriesInLines; //multidimensional array: stores each line complement(source and destination directories) as array in an array 

	//checking syntax of a file (this format ....;....)
	//it just checks syntactically, it doesn't check if the directory exist or not
	public boolean syntaxChecker() {
		for (String lines: uneditedAllFileLines) {
			if(!Pattern.matches(".+[;]{1}.+", lines)) {
				MyLogger.writeWarning("Syntax error at this line: " + lines);
				continue;
			}
			allFileLines.add(lines); //add lines to this list if its syntactically correct
		}
		return true;
	}
	
	@Override 
	//takes all lines one by one, parses them in two (source and destination directories) with separator ";" in this format: ....;....
	public void parsing() {
		try {
		setAllDirectoriesInLines(new String[allFileLines.size()][2]);
		for (int i = 0; i < allFileLines.size(); i++) {
			String[] parts = allFileLines.get(i).split(";"); //parsing with ";"
			getAllDirectoriesInLines()[i][0] = parts[0]; //adding source directory to our multidimensional array
			getAllDirectoriesInLines()[i][1] = parts[1]; //adding destination directory to our multidimensional array
		}
		}catch(Exception e){
			MyLogger.writeWarning("Something is wrong with file format");
			return;
		}
		
	}
	
	
	//getters and setters
	public List<String> getAllFileLines() {
		return allFileLines;
	}

	public void setAllFileLines(List<String> allFileLines) {
		this.allFileLines = allFileLines;
	}
	
	public List<String> getUneditedAllFileLines() {
		return uneditedAllFileLines;
	}

	public void setUneditedAllFileLines(List<String> uneditedAllFileLines) {
		this.uneditedAllFileLines = uneditedAllFileLines;
	}

	public String[][] getAllDirectoriesInLines() {
		return allDirectoriesInLines;
	}

	public void setAllDirectoriesInLines(String[][] allDirectoriesInLines) {
		this.allDirectoriesInLines = allDirectoriesInLines;
	}

}
