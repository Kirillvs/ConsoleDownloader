package com.arkhipov.downloader.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Timer;

import org.junit.Test;

import com.arkhipov.downloader.DownloadThread;
import com.arkhipov.downloader.DownloadedObject;
import com.arkhipov.downloader.helpers.TicketBucket;

public class DownloadedThreadTest {

	@Test
	public void testDownloadSpeed() throws InterruptedException {
		/*DownloadedObject downloadedObject = new DownloadedObject("http://apache-mirror.rbc.ru/pub/apache/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.zip first_archive.html");
		TicketBucket bucket =TicketBucket.getTicketBucket(500000);
		Timer timer = new Timer();
		timer.schedule(bucket, 0, 1000);
		Thread thread = new Thread(new DownloadThread(downloadedObject, bucket));
		long startDate = new Date().getTime();
		thread.start();
		thread.join();
		long endDate = new Date().getTime();
		double ttime = (endDate - startDate)/1000;
		double sspeed = downloadedObject.getBytes().length / ttime;
		double ssize = downloadedObject.getBytes().length;
		System.out.println("time - " + ttime);
		System.out.println("size - " + ssize);
		System.out.println("speed - " + sspeed);*/
	}

}
