package com.txtweb.app2fame.server;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.txtweb.app2fame.shared.Feed;
import com.txtweb.app2fame.shared.UserProfile;

/** Class UserDatabase
 * 	@version 1.0
 * 	@author Mayank Kandpal
 */


// how to store objects of class UserProfile in the database ?


public class FeedDatabase {

	
	public static void addFeed(String feedname, String feedurl){
		
		
		PersistenceManager pm = RAM.get().getPersistenceManager();
		Feed feed = new Feed(feedname, feedurl);

        try {
            pm.makePersistent(feed);
        }catch (java.lang.NullPointerException ex) {
      //  	System.out.println("exception is +++++" + ex.getMessage());
		}finally {
            pm.close();
        }
	}

// Search feed list by feed name
	public static Feed searchFeed(String feedname){	
		
		PersistenceManager pm = RAM.get().getPersistenceManager();
		
	    
		Query query = pm.newQuery(Feed.class);
	    query.setFilter("feedName == feedname");
	    query.declareParameters("String feedname");
	    
	    
	    List<Feed> feeds = (List<Feed>) query.execute(feedname);
	    
	    try{
	    	Feed feed = feeds.get(0);
	    	return feed;
		}catch (Exception IndexOutOfBoundsException) {
			return null;
		}finally {
	        query.closeAll();
	        pm.close();
		}
		
	}
	
	public static void setUserFav(String hashKey, String favId){
		
		
		PersistenceManager pm = RAM.get().getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		tx.begin();
		
		String query = "select from " + UserProfile.class.getName() + " where userHashKey=='"+hashKey+"'";
		List<UserProfile> list = (List<UserProfile>) pm.newQuery(query).execute();
		
	    
	    try{
			
	    	UserProfile profile = list.get(0);
			
	    	String[] favArr = list.get(0).getUserFav();
	    	
			if(!(favArr[0].equals(favId) || favArr[1].equals(favId))){
				favArr[1] = favArr[0];
				favArr[0] = favId;
			}
			
//	    	if(!(favArr[0].equals("blank")) && favArr[1].equals("blank") &&
//					favArr[0]!=favId){
//				favArr[1] = favId;
//			}
//			else if(!(favArr[1]==favId)) {
//				favArr[0]= favId;
//			}

	    	list.get(0).setUserFav(favArr);
//			out.println("Inside UserDatabase, Setting fav as " 
//					+ favId + " for user " + profile.getKey() + "<br>");
//	    	
	    	pm.makePersistent(profile);
	    	tx.commit();
			
		}catch (IndexOutOfBoundsException e) {
//			System.out.println("exception is +++++" + e.getMessage());
			tx.rollback();
		}finally {
	        pm.close();
		}

	}

	public static void resetUserFav(String hashKey, int index){
		
		PersistenceManager pm = RAM.get().getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		tx.begin();
		
		String query = "select from " + UserProfile.class.getName() + " where userHashKey=='"+hashKey+"'";
		List<UserProfile> list = (List<UserProfile>) pm.newQuery(query).execute();
		
	    try{
	    	UserProfile profile = list.get(0);
			String[] favArr = list.get(0).getUserFav();
	    	
			favArr[index-1]= "blank";

	    	list.get(0).setUserFav(favArr);
//			out.println("Inside UserDatabase, Setting fav as " 
//					+ favId + " for user " + profile.getKey() + "<br>");
//	    	
	    	pm.makePersistent(profile);
	    	tx.commit();
			
		}catch (IndexOutOfBoundsException e) {
			tx.rollback();
		}finally {
	        pm.close();
		}
	}
}
