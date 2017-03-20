package com.arkhipov.downloader.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.arkhipov.downloader.DownloadedObject;

public class DownloadedObjectTest {
	
	DownloadedObject testObject;
	String[] parsedParams;
	byte[] forBytes;

	@Before
	public void setUp() throws Exception {
		String params = "http://example.com/archive.zip my_archive.zip";
		parsedParams = params.split(" ");
		testObject = new DownloadedObject(params);
		forBytes = "Test string".getBytes();		
	}

	@Test
	public void testGetLink() {
		assertEquals(parsedParams[0], testObject.getLink());
	}
	
	@Test
	public void testGetFileName() {
		assertEquals(parsedParams[1], testObject.getFileName());
	}
	
	@Test
	public void testSetGetBytes() {
		testObject.setBytes(forBytes);
		assertEquals(forBytes, testObject.getBytes());
	}
	
}
