package com.arkhipov.downloader.helpers;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleParams {
	
	private int numberThreads;
	private int speed;
	private String folderPath;
	private String pathToSourceFile;
	
	/**
	 * Initialize parameters
	 * @param args
	 */
	public ConsoleParams(String[] args) {
		Map<String, String> params = parseParams(args);
		setInstanceVariables(params);
	}
	
	private Map<String, String> parseParams(String[] args){
		Map<String, String> mapParams = new HashMap<>(); 
		for(int i = 0; i < args.length; i += 2){
			mapParams.put(args[i], args[i+1]);
		}
		return mapParams;
	}
	
	private void setInstanceVariables(Map<String, String> mapParams){
		numberThreads = Integer.parseInt(mapParams.get("-n"));
		speed = parseSpeed(mapParams.get("-l"));
		folderPath = mapParams.get("-o");
		pathToSourceFile = mapParams.get("-f");		
	}
	
	private int parseSpeed(String speed){
		int resultSpeed = 0;
		int suffixSpeed = 1;
		String regexp = "\\D";
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(speed);
		if(Pattern.matches("\\d+\\w", speed)){
			String suffix = "";
			while(matcher.find()){
				suffix = matcher.group();
			}
			suffixSpeed = getSuffixSpeed(suffix.toLowerCase());
		}
		speed = matcher.replaceAll("");
		resultSpeed = Integer.parseInt(speed);
		return resultSpeed*suffixSpeed;
	}
	
	private int getSuffixSpeed(String suffix){
		int speed = 1;
		switch (suffix) {
		case "k":
			speed = 1024;
			break;
		case "m":
			speed = 1024*1024;
			break;
		}
		return speed;
	}
	
	/**
	 * Return number of threads
	 * @return int threads number
	 */
	public int getNumberThreads(){
		return numberThreads;
	}
	
	/**
	 * Return download speed
	 * @return int speed
	 */
	public int getSpeed(){
		return speed;
	}
	
	/**
	 * Return target folder path
	 * @return String path
	 */
	public String getFolderPath(){
		return folderPath;
	}
	
	/**
	 * Return path to source file with links to download
	 * @return String path
	 */
	public String getPathToSourceFile(){
		return pathToSourceFile;
	}

}
