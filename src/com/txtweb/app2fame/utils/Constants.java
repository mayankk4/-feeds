package com.txtweb.app2fame.utils;

import java.util.HashMap;
import java.util.Map;

/** Class Constants
 *  @version 1.0
 *  @author Mayank Kandpal
 */


public class Constants {
	public static String newline = System.getProperty("line.separator");
		
	public static final int FEEDS_CACHE_LIFETIME = 600000; //600000 milliseconds = 10 minutes
	
	public static final String HTML_NEWLINE = "<br>";
    public static final String TEXT_WEB_MSG = "txtweb-message";
    public static final String TEXT_WEB_MOB = "txtweb-mobile";
    
    public static final String HELP_MESSAGE = 
    		"SMS @feeds : view the latest post of each subsciption<br><br>" +
    		"SMS @feeds options : view all available subscriptions with ID.<br><br>" +
    		"SMS @feeds mysub : view all your subscriptions.<br><br>" +
    		"SMS @feeds [id] : view latest five feeds of the subscription.<br><br>" + 
    		"SMS @feeds sub [id] :  to subscribe a feed.<br><br>" +
    		"SMS @feeds unsub [id] : to unsubscribe a feed.<br><br>" +
    		"SMS @feeds unsub all : to unsubscribe to all feeds.<br><br>" +
    		"SMS @feeds help : view help content.<br><br>" ;
    
    public static final String COMING_SOON = "This feature will be available soon.<br>";
    
    public static Map<String, String> urlMap = new HashMap<String, String>();
    
    public static void createUrlMap()
    {
    	urlMap.put("Google News India", "http://news.google.com/news?cf=all&ned=in&hl=en&output=rss");
    	urlMap.put("Mashable", "http://feeds.mashable.com/Mashable?format=xml");
    	urlMap.put("ars technica", "http://feeds.arstechnica.com/arstechnica/index?format=xml");
    	urlMap.put("SMASHING NETWORK", "http://rss1.smashingmagazine.com/feed/?f=smashing-network");
    	urlMap.put("Tech Crunch", "http://feeds.feedburner.com/TechCrunch");
    	urlMap.put("Times Of India", "http://dynamic.feedsportal.com/pf/555218/http://toi.timesofindia.indiatimes.com/rssfeedstopstories.cms");
    	urlMap.put("CRICINFO", "http://www.espncricinfo.com/rss/content/story/feeds/0.xml");
    	urlMap.put("ESPNSTAR", "http://www.espnstar.com/headlines-rss/");
    	urlMap.put("CNN", "http://rss.cnn.com/rss/cnn_topstories.rss");
    }
    
}