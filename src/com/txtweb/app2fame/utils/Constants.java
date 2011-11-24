package com.txtweb.app2fame.utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
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
    		"SMS @feeds : view the latest post of each subsciption<br>-<br>" +
    		"SMS @feeds options : view all available subscriptions with ID.<br>-<br>" +
    		"SMS @feeds mysub : view all your subscriptions.<br>-<br>" +
    		"SMS @feeds [id] : view latest five feeds of the subscription.<br>-<br>" + 
    		"SMS @feeds sub [id] :  to subscribe a feed.<br>-<br>" +
    		"SMS @feeds unsub [id] : to unsubscribe a feed.<br>-<br>" +
    		"SMS @feeds unsub all : to unsubscribe to all feeds.<br>-<br>" +
    		"SMS @feeds help : view help content.<br>-<br>" ;
    
    public static final String COMING_SOON = "This feature will be available soon.<br>";
    
    public static LinkedHashMap<String, String> urlMap = new LinkedHashMap<String, String>();
    
    public static void createUrlMap()
    {
    	// National News 
    	urlMap.put("Google News India", "http://news.google.com/news?cf=all&ned=in&hl=en&topic=n&output=rss");
    	urlMap.put("Times Of India", "http://dynamic.feedsportal.com/pf/555218/http://toi.timesofindia.indiatimes.com/rssfeedstopstories.cms");
    	
    	// International News
    	urlMap.put("CNN", "http://rss.cnn.com/rss/cnn_topstories.rss");
    	urlMap.put("BBC", "http://feeds.bbci.co.uk/news/rss.xml");
    	
    	// Stock/Finance
    	urlMap.put("World Finance", "http://news.google.com/news?pz=1&cf=all&ned=in&hl=en&topic=b&output=rss");
    	urlMap.put("Economy", "http://www.moneycontrol.com/rss/economy.xml");

    	// Sports
    	urlMap.put("CRICINFO", "http://www.espncricinfo.com/rss/content/story/feeds/0.xml");
    	urlMap.put("ESPNSTAR", "http://www.espnstar.com/headlines-rss/");

    	// Hollywood test
    	urlMap.put("Hollywood News", "http://feeds.feedburner.com/thr/boxoffice");
    	urlMap.put("Hollywood Releases", "http://feeds.hollywood.com/hollywoodcom_new_movies_this_week");
    	urlMap.put("Hollywood Gossip", "http://www.hollywoodgo.com/celebrity-gossip/feed/");

    	// Bollywood
    	urlMap.put("Bollywood News", "http://www.indiaglitz.com/channels/hindi/rss/news-rss.xml");
    	urlMap.put("Bollywood Gossip", "http://www.santabanta.com/rss/cinema.asp?cat=Titbits");

    	// Music
    	urlMap.put("International Music Releases", "http://feeds.feedburner.com/thr/music");
    	urlMap.put("Indian Music Releases", "http://rss.songs.pk/");

    	// Technical
    	urlMap.put("Smashing Network", "http://rss1.smashingmagazine.com/feed/?f=smashing-network");
    	//urlMap.put("Mashable", "http://feeds.mashable.com/Mashable?format=xml");
    	urlMap.put("Ars Technica", "http://feeds.arstechnica.com/arstechnica/index?format=xml");
       	urlMap.put("Tech Crunch", "http://feeds.feedburner.com/TechCrunch");
       	
     // Jobs
    	urlMap.put("Jobs For ITES/BPO/KPO, Customer Service, Ops", "http://jobsearch.naukri.com/mynaukri/mn_newsmartsearch.php?xz=1_0_15&xo=2727&xl=rss&rss=r&type=all&qf=8");
    	urlMap.put("Jobs For Banking, Insurance", "http://jobsearch.naukri.com/mynaukri/mn_newsmartsearch.php?xz=1_0_15&xo=2727&xl=rss&rss=r&type=all&qf=6");
    	
       	
    }
    
}