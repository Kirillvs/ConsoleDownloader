package com.arkhipov.downloader.helpers;

import java.util.TimerTask;

public class TicketBucket extends TimerTask{
	
	private static TicketBucket bucket;
	public static final int BYTES_BUFFER_SIZE = 1;
	
	private volatile int currentBucketSize;
	private final int totalBucketSize;

	
	private TicketBucket(int speed) {
		totalBucketSize = speed;
	}
	
	public static TicketBucket getTicketBucket(int speed){
		if(bucket == null) bucket = new TicketBucket(speed);
		return bucket;
	}	
	
	public synchronized boolean takeTicket(){
		boolean is_ticket = currentBucketSize >= 0;
		currentBucketSize -= BYTES_BUFFER_SIZE;
		return is_ticket;			
	}
	
	private void updateBucket(){
		currentBucketSize = totalBucketSize;
	}

	@Override
	public void run() {
		updateBucket();
	}

}
