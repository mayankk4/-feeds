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
    		"SMS @feeds : top post of each subsciption<br>" +
    		"SMS @feeds options : show key value pairs [id] [feed name] for all <br>" +
    		"SMS @feeds mysub : show key value pairs [id] [feed name].<br>" +
    		"SMS @feeds [id] : show whole buffer of that subscription.<br>" + 
    		"SMS @feeds sub [id] :  to subscribe to a feed.<br>" +
    		"SMS @feeds unsub [id] : to unsubscribe to a feed.<br>" +
    		"SMS @feeds unsub all : to unsubscribe to all feeds.<br>" +
    		"SMS @feeds help : show help content.<br>" ;
    
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