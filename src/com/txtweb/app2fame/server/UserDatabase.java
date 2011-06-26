package com.txtweb.app2fame.server;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.txtweb.app2fame.shared.UserProfile;

/** Class UserDatabase
 * 	@version 1.0
 * 	@author Mayank Kandpal
 */


// how to store objects of class UserProfile in the database ?


public class UserDatabase {
	
	public static void addUser(String hashKey){
		
		
		PersistenceManager pm = RAM.get().getPersistenceManager();
		UserProfile user = new UserProfile(hashKey);

        try {
            pm.makePersistent(user);
        }catch (java.lang.NullPointerException ex) {
      //  	System.out.println("exception is +++++" + ex.getMessage());
		}finally {
            pm.close();
        }
	}
	
	public static UserProfile searchUser(String hashKey){	
		
		PersistenceManager pm = RAM.get().getPersistenceManager();
		
	    
		Query query = pm.newQuery(UserProfile.class);
	    query.setFilter("userHashKey == hashKey");
	    query.declareParameters("String hashKey");
	    
	    
	    List<UserProfile> users = (List<UserProfile>) query.execute(hashKey);
	    
	    try{
	    	UserProfile user = users.get(0);
	    	return user;
		}catch (Exception IndexOutOfBoundsException) {
			return null;
		}finally {
	        query.closeAll();
	        pm.close();
		}
		
	}
	
	public static boolean setUserFav(String hashKey, String favId){
		
		
		PersistenceManager pm = RAM.get().getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		tx.begin();
		
		String query = "select from " + UserProfile.class.getName() + " where userHashKey=='"+hashKey+"'";
		List<UserProfile> list = (List<UserProfile>) pm.newQuery(query).execute();
		
	    
	    try{
			
	    	UserProfile profile = list.get(0);
			
	    	String[] favArr = list.get(0).getUserFav();
	    	
			if(!(favArr[0].equals(favId) || favArr[1].equals(favId) || favArr[2].equals(favId) || favArr[3].equals(favId) || favArr[4].equals(favId) )){
				if(favArr[0].equals("blank"))
					favArr[0] = favId;
				else if(favArr[1].equals("blank"))
					favArr[1] = favId;					
				else if(favArr[2].equals("blank"))
					favArr[2] = favId;					
				else if(favArr[3].equals("blank"))
					favArr[3] = favId;					
				else if(favArr[4].equals("blank"))
					favArr[4] = favId;
				else if(favArr[5].equals("blank"))
					favArr[5] = favId;
				else return false;
			}
			
	    	list.get(0).setUserFav(favArr);
	    	pm.makePersistent(profile);
	    	tx.commit();
			
		}catch (IndexOutOfBoundsException e) {
//			System.out.println("exception is +++++" + e.getMessage());
			tx.rollback();
		}finally {
	        pm.close();
		}
		
		return true;
	}

	public static boolean resetUserFav(String hashKey, String feedid) throws UnsupportedEncodingException{
		
		PersistenceManager pm = RAM.get().getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		tx.begin();
		
		String query = "select from " + UserProfile.class.getName() + " where userHashKey=='"+hashKey+"'";
		List<UserProfile> list = (List<UserProfile>) pm.newQuery(query).execute();
		
		boolean exists = false;
		
	    try{
	    	UserProfile profile = list.get(0);
			String[] favArr = list.get(0).getUserFav();
	    	
			for(int count = 0; count < favArr.length; count++){
//				favArr[count] = favArr[count].getBytes("utf-8").toString();
				if( favArr[count].equals(feedid) ){
					favArr[count] = "blank";
					exists = true;
				}
			}
	
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
		
		return exists;
	}

	public static List showUserFav(String hashKey){
				
		PersistenceManager pm = RAM.get().getPersistenceManager();
		String query = "select from " + UserProfile.class.getName() + " where userHashKey=='"+hashKey+"'";
		List<UserProfile> list = (List<UserProfile>) pm.newQuery(query).execute();
		
	    	UserProfile profile = list.get(0);
			String[] favArr = list.get(0).getUserFav();
			
			//public static List<Match> scoreDetailCache = null;
	    	
			List<String> userFav = new ArrayList<String>();

			for(int count = 0; count < favArr.length; count++){
				if(!(favArr[count].equals("blank"))){
					userFav.add(favArr[count]);
				}
			}
	        pm.close();
			
		return userFav;

	}
	
	public static void resetAllUserFav(String hashKey){
		
		PersistenceManager pm = RAM.get().getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		tx.begin();
		
		String query = "select from " + UserProfile.class.getName() + " where userHashKey=='"+hashKey+"'";
		List<UserProfile> list = (List<UserProfile>) pm.newQuery(query).execute();
		
	    try{
	    	UserProfile profile = list.get(0);
			String[] favArr = list.get(0).getUserFav();
	    	
			for(int count = 0; count < favArr.length; count++){
				favArr[count] = "blank";
			}

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
