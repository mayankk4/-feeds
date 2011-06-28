package com.txtweb.app2fame.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import com.txtweb.app2fame.shared.UserProfile;
import com.txtweb.app2fame.utils.Constants;

// http://developer.txtweb.com/txtwebpush?txtweb-mobile=80a9f721-8a4b-4701-97f2-47d5186028d6&txtweb-message=%3Chtml%3E%3Chead%3E%3Cmeta+name=%22txtweb-appkey%22+content=+%220eb8c0f3-c065-4aef-8acd-192756f5b4bd%22%3E%3C/head%3E%3Cbody%3EHi%3C/body%3E%3C/html%3E&txtweb-appkey=0eb8c0f3-c065-4aef-8acd-192756f5b4bd&txtweb-pubkey=3C3329D3-D7AF-443D-8FE9-4027E12F8E25
// http://developer.txtweb.com/txtwebpush?txtweb-mobile=80a9f721-8a4b-4701-97f2-47d5186028d6&txtweb-message=%3Chtml%3E%3Chead%3E%3Cmeta+name=%22txtweb-appkey%22+content=+%220eb8c0f3-c065-4aef-8acd-192756f5b4bd%22%3E%3C/head%3E%3Cbody%3EHi%3C/body%3E%3C/html%3E&txtweb-appkey=0eb8c0f3-c065-4aef-8acd-192756f5b4bd&txtweb-pubkey=3C3329D3-D7AF-443D-8FE9-4027E12F8E25
// http://developer.txtweb.com/txtwebpush?txtweb-mobile=634555a6-e30a-467d-bb07-17fd33b01990&txtweb-message=<html><head><meta+name%3D"txtweb-appkey"+content%3D+"0eb8c0f3-c065-4aef-8acd-192756f5b4bd"><%2Fhead><body>HiDudeWhereTheFuckAreYou<%2Fbody><%2Fhtml>&txtweb-appkey=0eb8c0f3-c065-4aef-8acd-192756f5b4bd&txtweb-pubkey=3C3329D3-D7AF-443D-8FE9-4027E12F8E25


public class CheckPush extends HttpServlet {

	private static String pageHtml = "<html><head><meta name=\"txtweb-appkey\" content=\"0e5e27f3-9cc5-4c60-a420-febe0ff4149f\" /><title> Page title </title></head><body>%s</body></html>";
	public static List<Feed> feedList = new ArrayList<Feed>();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub ...
		super.init(config);
		Constants.createUrlMap();
		
		long index = 1;
		for (String key : Constants.urlMap.keySet()) {
			Feed feed = new Feed(index++, key, Constants.urlMap.get(key));
			feedList.add(feed);
		}
	}

	public int updateFeedBuffer(Feed feed, String[] buffer, int size) throws IOException
	{
		
		String latestFeed = feed.getFeedBuffer()[0];
		int shiftIndex;
		int index;
		for(shiftIndex = 0; shiftIndex < size; shiftIndex++){
			if(buffer[shiftIndex].equalsIgnoreCase(latestFeed))
				break;
		}
		
		for(index = 0; index < size; index++)
		{
			feed.getFeedBuffer()[index] = buffer[index];
		}
		
		if(shiftIndex > 0)
		{
			pushFeed(feed.getKey(), shiftIndex);
		}

		return shiftIndex;
	}
	
	public void getNewFeeds() throws IOException, IllegalArgumentException,
			FeedException {
		
		long id = 1;
		int hammingDist;
		String[] feedBuffer = new String[5];
		for(Feed feed : feedList)
		{
			int count = 0;
			URL url = new URL(feed.getFeedUrl());
			XmlReader reader = null;
			SyndFeed sf = null;
			SyndEntry entry = null;
			try {
				reader = new XmlReader(url);
				sf = new SyndFeedInput().build(reader);
				for (Iterator i = sf.getEntries().iterator(); i.hasNext() && count < 5;) {
					entry = (SyndEntry) i.next();
					feedBuffer[count++] =  entry.getTitle();
					
				}
				hammingDist = updateFeedBuffer(feed, feedBuffer, 5);

			} finally {
				if (reader != null);
					//delete(feedBuffer);
					reader.close();
			}
			
		}
		
		//feed.setFeedBuffer(feedBuffer);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		PrintWriter out = resp.getWriter();

		try {
			getNewFeeds();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FeedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println(String.format(pageHtml, "Success"));
	}
// test
	public void pushFeed(long feedId, int numberUpdates) throws IOException, java.lang.NullPointerException
	{
		

		String publisherKey =  "3C3329D3-D7AF-443D-8FE9-4027E12F8E25";
		String appkey = "0e5e27f3-9cc5-4c60-a420-febe0ff4149f";
		int index = 0;
		String message = "";
		message += feedList.get((int)(feedId-1)).getFeedName()+"<br><br>";
		while(numberUpdates > 0)
		{
			message += feedList.get((int)(feedId-1)).getFeedBuffer()[index++]+"<br><br>";
			numberUpdates--;
		}
		message = 	String.format(pageHtml, message);

		// iterate through all the users
		// if user has feedid in his fav array then ..
		URL urlx = new URL("http://developer.txtweb.com/txtwebpush?txtweb-mobile=80a9f721-8a4b-4701-97f2-47d5186028d6&txtweb-message=%3Chtml%3E%3Chead%3E%3Cmeta+name=%22txtweb-appkey%22+content=+%220eb8c0f3-c065-4aef-8acd-192756f5b4bd%22%3E%3C/head%3E%3Cbody%3EHi%3C/body%3E%3C/html%3E&txtweb-appkey=0eb8c0f3-c065-4aef-8acd-192756f5b4bd&txtweb-pubkey=3C3329D3-D7AF-443D-8FE9-4027E12F8E25");
		URLConnection connx = urlx.openConnection();
		// Get the response
		BufferedReader rdx = new BufferedReader(new InputStreamReader(connx.getInputStream()));


		PersistenceManager pm = RAM.get().getPersistenceManager();
		Query query = pm.newQuery(UserProfile.class);	    
	    
	    List<UserProfile> users = (List<UserProfile>) query.execute();

	    for(UserProfile user : users){
	    	
	    	for(int userSubscribedFeedId : user.getUserFav()){
	    		
	    		if(userSubscribedFeedId == feedId){

	    			String encryptedMobile = user.getUserHashKey();
				
					String urlStr =	"http://developer.txtweb.com/txtwebpush?txtweb-mobile="
						+URLEncoder.encode(encryptedMobile,"utf-8")+
						"&txtweb-message="
						+URLEncoder.encode(message,"utf-8")
						+"&txtweb-appkey="
						+URLEncoder.encode(appkey,"utf-8")
						+"&txtweb-pubkey="
						+URLEncoder.encode(publisherKey,"utf-8");	
					
					URL url = new URL(urlStr);
					URLConnection conn = url.openConnection();
					
					// Get the response
					BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    		}
	    	}
	    }
        query.closeAll();
        pm.close();	    
	}

	public static void main(String[] args) throws ServletException, IllegalArgumentException, IOException, FeedException
	{
		System.out.println("hello");
		ServletConfig config = null;
		CheckPush chk = new CheckPush();
		chk.init(config);
		chk.getNewFeeds();
		System.out.println("bye");
	}
}
