package com.txtweb.app2fame.server;

/** Class Feed
 *  @version 1.0 
 *  @author Mayank Kandpal
 */



public class Feed {

	private long key;
	

	private String feedName; //key
	
	private String feedUrl; //key
	
	
	private int subscribedUserCount; //key
	
	
	private String[] feedBuffer = new String[5]; // content of feeds, blank if not set; 5 feed titles at max
		
	// Constructor
	public Feed(long key, String feedname, String feedurl){
		this.key = key;
		this.feedName = feedname;
		this.feedUrl = feedurl;
		this.subscribedUserCount = 0;
		for(int count =0; count<feedBuffer.length; count++){
			feedBuffer[count]="blank";
		}
	}
	
	public long getKey() {
		return key;
		}
		
	public void setKey(long key) {
		this.key = key;
		}

	public String getFeedName() {
		return feedName;
	}

	public void setFeedName(String feedName) {
		this.feedName = feedName;
	}

	public String getFeedUrl() {
		return feedUrl;
	}

	public void setFeedUrl(String feedUrl) {
		this.feedUrl = feedUrl;
	}

	public String[] getFeedBuffer() {
		return feedBuffer;
	}

	public void setFeedBuffer(String[] feedBuffer) {
		this.feedBuffer = feedBuffer;
	}

	
	public int getSubscribedUserCount() {
		return subscribedUserCount;
	}

	public void setSubscribedUserCount(int subscribedUserCount) {
		this.subscribedUserCount = subscribedUserCount;
	}
	
}