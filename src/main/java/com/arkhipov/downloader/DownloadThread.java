package com.arkhipov.downloader;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import com.arkhipov.downloader.helpers.TicketBucket;

public class DownloadThread implements Runnable {
	
	private DownloadedObject downloadedObject;
	private TicketBucket bucket;
	
	public DownloadThread(DownloadedObject downloadedObject, TicketBucket bucket) {
		this.downloadedObject = downloadedObject;
		this.bucket = bucket;
	}

	@Override
	public void run() {
		//System.out.println("Поток начал загрузку по ссылке -  " + downloadedObject.getLink());
		URL url;
		try {
			url = new URL(downloadedObject.getLink());
	        InputStream in = new BufferedInputStream(url.openStream());
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        byte[] result;
	        byte buffer[] = new byte[TicketBucket.BYTES_BUFFER_SIZE];
	        int count = in.read(buffer);
	        while(count!= -1) {	    	    
	    	    if(!bucket.takeTicket()) continue; 
	    	    baos.write(buffer);
	    	    count = in.read(buffer);
	        }
	        result = baos.toByteArray();
	        in.close();
	        baos.close();
	        downloadedObject.setBytes(result);
			//System.out.println("Поток закончил загрузку по ссылке -  " + downloadedObject.getLink());
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		} 	        
	}
}
