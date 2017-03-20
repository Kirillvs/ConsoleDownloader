package com.arkhipov.downloader;

public class DownloadedObject {
	private byte[] bytes;
	private String link;
	private String fileName;
	
	public DownloadedObject(String linkLine) {
		parseLine(linkLine);
	}
	
	private void parseLine(String linkLine){
		String[] params = linkLine.split(" ");
		link = params[0];
		fileName = params[1];
	}
	
	public void setBytes(byte[] bytes){
		this.bytes = bytes;
	}
	
	public byte[] getBytes() {
		return bytes;
	}
	public String getLink() {
		return link;
	}
	public String getFileName() {
		return fileName;
	}
	
}
