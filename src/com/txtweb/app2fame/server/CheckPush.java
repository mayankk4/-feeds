package com.txtweb.app2fame.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
import com.txtweb.app2fame.utils.Constants;

public class CheckPush extends HttpServlet {

	private static String pageHtml = "<html><head><meta name=\"txtweb-appkey\" content=\"0e5e27f3-9cc5-4c60-a420-febe0ff4149f\" /><title> Page title </title></head><body>%s</body></html>";
	public static List<Feed> feedList = new ArrayList<Feed>();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		Constants.createUrlMap();
		
		long index = 1;
		for (String key : Constants.urlMap.keySet()) {
			Feed feed = new Feed(index++, key, Constants.urlMap.get(key));
			feedList.add(feed);
		}
	}

	public int updateFeedBuffer(Feed feed, String[] buffer, int size)
	{
		String latestFeed = feed.getFeedBuffer()[0];
		int shiftIndex;
		int index;
		for(shiftIndex = 0; shiftIndex < size; shiftIndex++)
		{
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
		int count = 0;
		long id = 1;
		int hammingDist;
		for(Feed feed : feedList)
		{
			String[] feedBuffer = new String[5];
			URL url = new URL(feed.getFeedUrl());
			XmlReader reader = null;
			SyndFeed sf = null;
			SyndEntry entry = null;

			try {

				reader = new XmlReader(url);
				sf = new SyndFeedInput().build(reader);
				// System.out.println("Feed Title: "+ feed.getAuthor());

				for (Iterator i = sf.getEntries().iterator(); i.hasNext() && count < 5;) {
					entry = (SyndEntry) i.next();
					feedBuffer[count++] =  entry.getTitle();
					//System.out.println(entry.getTitle());						
				}
				hammingDist = updateFeedBuffer(feed, feedBuffer, 5);
			} finally {
				if (reader != null)
					reader.close();
			}
			
		}
		//feed.setFeedBuffer(feedBuffer);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		PrintWriter out = resp.getWriter();

	}

	public void pushFeed(long feedId, int numberUpdates)
	{
		
	}
}
