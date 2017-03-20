package com.arkhipov.downloader.test.helpers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.arkhipov.downloader.helpers.ConsoleParams;

public class ConsoleParamsTest {
	
	ConsoleParams consoleParams;
	
	@Before
	public void setUp() throws Exception {
		String[] params = {"-n", "5", "-l", "2000k", "-o", "output_folder", "-f", "links.txt"};
		consoleParams = new ConsoleParams(params);
	}

	@Test
	public void testGetNumberThreads() {
		assertEquals(5, consoleParams.getNumberThreads());
	}
	
	@Test
	public void testGetSpeed() {
		assertEquals(2048000, consoleParams.getSpeed());
	}
	
	@Test
	public void testGetFolderPath() {
		assertEquals("output_folder", consoleParams.getFolderPath());
	}
	
	@Test
	public void testGetPathToSourceFile() {
		assertEquals("links.txt", consoleParams.getPathToSourceFile());
	}




}
