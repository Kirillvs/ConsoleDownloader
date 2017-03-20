package com.arkhipov.downloader.test;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.arkhipov.downloader.utils.FileHelper;

public class FileHelperTest {
	
	String oldFolderPath;
	String newFolderPath;
	String linksFilePath;
	String linkLine;
	String resultsFolderPath;
	String resultsFilePath;


	@Before
	public void setUp() throws Exception {
		oldFolderPath = "testOldFolder";
		new File(oldFolderPath).mkdirs();
		newFolderPath = "testNewFolder";
		
		linksFilePath = "test_links.txt";
		
		resultsFolderPath = "result_folder";
		new File(resultsFolderPath).mkdirs();
		resultsFilePath = "test_file.txt";
		
	}

	@After
	public void tearDown() throws Exception {
		new File(oldFolderPath).delete();
		new File(newFolderPath).delete();		
		
		File resultFolder = new File(resultsFolderPath);
		if(resultFolder.exists()){
			for(File file : resultFolder.listFiles()){
				file.delete();
			}
			resultFolder.delete();
		}
	}

	@Test
	public void testCreateTargetFolderIfExists() {
		FileHelper.createTargetFolder(oldFolderPath);
		File oldFolder = new File(oldFolderPath);		
		assertEquals(true, oldFolder.isDirectory());
	}
	
	@Test
	public void testCreateTargetFolderIfNotExists() {
		FileHelper.createTargetFolder(newFolderPath);
		File newFolder = new File(newFolderPath);		
		assertEquals(true, newFolder.isDirectory());
	}
	
	@Test
	public void testReadFromLinkFile(){
		File linksFile = new File(linksFilePath);
		linkLine = "http://example.com/archive.zip first_archive.zip";
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(linksFile));
			bw.write(linkLine);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArrayList<String> result = FileHelper.loadLinksFromFile(linksFilePath);
		assertEquals(linkLine, result.get(0));
		new File(linksFilePath).delete();
	}
	
	@Test
	public void testSaveResultToFolder(){
		FileHelper.createTargetFolder(resultsFolderPath);
		byte[] fileOneArray = "Test text".getBytes();
		FileHelper.saveResultToFolder(resultsFilePath, fileOneArray);
		File resultFile = new File(resultsFolderPath, resultsFilePath);
		assertEquals(true, resultFile.exists());
		try {
			FileInputStream fis = new FileInputStream(resultFile);
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer, 0, fis.available());
			assertEquals(fileOneArray[0], buffer[0]);
			assertEquals(fileOneArray[fileOneArray.length - 1], buffer[buffer.length-1]);
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
