package com.txtweb.app2fame.server;

/** Class Feed
 *  @version 1.0 
 *  @author Mayank Kandpal
 */

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


public class Feed {

	private int key;
	

	private String feedName; //key
	
	private String feedUrl; //key
	
	private int subscribedUserCount; //key
	
	private String[] feedBuffer = new String[5]; // content of feeds, blank if not set; 5 feed titles at max
		
	// Constructor
	public Feed(int key, String feedname, String feedurl){
		this.key = key;
		this.feedName = feedname;
		this.feedUrl = feedurl;
		this.subscribedUserCount = 0;
		for(int count =0; count<feedBuffer.length; count++){
			feedBuffer[count]="blank";
		}
	}
	
	public int getKey() {
		return key;
		}
		
	public void setKey(int key) {
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