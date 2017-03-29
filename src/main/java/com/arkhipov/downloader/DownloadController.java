package com.arkhipov.downloader;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.arkhipov.downloader.helpers.ConsoleParams;
import com.arkhipov.downloader.helpers.TicketBucket;
import com.arkhipov.downloader.utils.FileHelper;

public class DownloadController {
	
	public static void main(String[] args) {
		DownloadController controller = new DownloadController(args);
		controller.doWork();
	}
	
	ConsoleParams consoleParams;
	ArrayList<DownloadedObject> downloadedObjects;
	long startTime;
	long endTime;
	
	public DownloadController(String[] args) {
		consoleParams = new ConsoleParams(args);
		FileHelper.createTargetFolder(consoleParams.getFolderPath());
		downloadedObjects = new ArrayList<>();
		ArrayList<String> linkLines = FileHelper.loadLinksFromFile(consoleParams.getPathToSourceFile());
		for(String line : linkLines){
			downloadedObjects.add(new DownloadedObject(line));
		}		
	}
	
	public void doWork(){
		TicketBucket bucket = TicketBucket.getTicketBucket(consoleParams.getSpeed());
		Timer timer = new Timer();
		System.out.println("Загрузка началась...");
		timer.schedule(bucket, 0, 1000);
		startTime = new Date().getTime();
		ExecutorService executorService = Executors.newFixedThreadPool(consoleParams.getNumberThreads());
		for(DownloadedObject object : downloadedObjects){
			executorService.submit(new DownloadThread(object, bucket));
		}
		//блокируем основной поток, пока не выполнятся все задачи ExecutorService
		executorService.shutdown();
		try {
			executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		timer.cancel();
		for(DownloadedObject object : downloadedObjects){
			FileHelper.saveResultToFolder(object.getFileName(), object.getBytes());
		}
		endTime = new Date().getTime();
		double resultTime = (double)(endTime - startTime) / 1000;
		int allBytes = getAllBytes(downloadedObjects);
		double resultSpeed = (double)allBytes / resultTime / 1024.0;
		System.out.println("Прошло " + resultTime + " сек.");
		System.out.println("Всего загруженно - " + allBytes + " байт");
		System.out.println("Средняя скорость - " + resultSpeed + " кбайт/сек.");
	}
	
	private int getAllBytes(ArrayList<DownloadedObject> downloadedObjects){
		int result = 0;
		for(DownloadedObject object : downloadedObjects){
			result += object.getBytes().length; 
		}
		return result;		
	}

}
