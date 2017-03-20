package com.arkhipov.downloader.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileHelper {
	
	private static String TARGET_FOLDER; 
	
	public static void createTargetFolder(String path){
		File targetPath = new File(path);
		if(!targetPath.isDirectory()){
			targetPath.mkdirs();
		}
		FileHelper.TARGET_FOLDER = path;		
	}
	
	public static ArrayList<String> loadLinksFromFile(String path){
		ArrayList<String> lines = new ArrayList<>();
		try {
			Files.lines(Paths.get(path), StandardCharsets.UTF_8).forEach(lines::add);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
	
	public static void saveResultToFolder(String fileName, byte[] bytes){
		if(FileHelper.TARGET_FOLDER != null){
			try {
				FileOutputStream fos = new FileOutputStream(new File(FileHelper.TARGET_FOLDER, fileName));
				fos.write(bytes);
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

}
