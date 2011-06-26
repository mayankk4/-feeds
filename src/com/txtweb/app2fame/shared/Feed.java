package com.txtweb.app2fame.shared;

/** Class UserProfile
 *  @version 1.0 
 *  @author Mayank Kandpal
 */

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


@PersistenceCapable
public class Feed {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long key;
	
	public int getSubscribedUserCount() {
		return subscribedUserCount;
	}

	public void setSubscribedUserCount(int subscribedUserCount) {
		this.subscribedUserCount = subscribedUserCount;
	}

	@Persistent
	private String feedName; //key
	
	@Persistent
	private String feedUrl; //key
	
	@Persistent
	private int subscribedUserCount; //key
	
	@Persistent
	private String[] feedBuffer = new String[5]; // content of feeds, blank if not set; 5 feed titles at max
		
	// Constructor
	public Feed(String feedname, String feedurl){
		this.feedName = feedname;
		this.feedUrl = feedurl;
		this.subscribedUserCount = 0;
		for(int count =0; count<feedBuffer.length; count++){
			feedBuffer[count]="blank";
		}
	}
	
	public Long getKey() {
		return key;
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

	public void setKey(Long keyx) {
		this.key = keyx;
		}
	
	
}