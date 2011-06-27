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
	
	public static int setUserFav(String hashKey, int favId){
		
		
		PersistenceManager pm = RAM.get().getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		tx.begin();
		
		String query = "select from " + UserProfile.class.getName() + " where userHashKey=='"+hashKey+"'";
		List<UserProfile> list = (List<UserProfile>) pm.newQuery(query).execute();
		
	    
	    try{
			
	    	UserProfile profile = list.get(0);
			
	    	int[] favArr = list.get(0).getUserFav();
	    	
			if(!(favArr[0]==(favId) || favArr[1]==(favId) || favArr[2]==(favId) || favArr[3]==(favId) || favArr[4]==(favId) || favId<=0 || favId > CheckPush.feedList.size() )){
				if(favArr[0] == (-1))
					favArr[0] = favId;
				else if(favArr[1] == (-1))
					favArr[1] = favId;					
				else if(favArr[2] == (-1))
					favArr[2] = favId;					
				else if(favArr[3] == (-1))
					favArr[3] = favId;					
				else if(favArr[4] == (-1))
					favArr[4] = favId;
				else if(favArr[5] == (-1))
					favArr[5] = favId;
				else return 0; // array is full
			} else{
				return -1; // incorrect id
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
		
		return 1; // added subscription
	}

	public static boolean resetUserFav(String hashKey, int feedid) throws UnsupportedEncodingException{
		
		PersistenceManager pm = RAM.get().getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		tx.begin();
		
		String query = "select from " + UserProfile.class.getName() + " where userHashKey=='"+hashKey+"'";
		List<UserProfile> list = (List<UserProfile>) pm.newQuery(query).execute();
		
		boolean exists = false;
		
	    try{
	    	UserProfile profile = list.get(0);
			int[] favArr = list.get(0).getUserFav();
	    	
			for(int count = 0; count < favArr.length; count++){
//				favArr[count] = favArr[count].getBytes("utf-8").toString();
				if( favArr[count]==(feedid) ){
					favArr[count] = -1;
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
			int[] favArr = list.get(0).getUserFav();
			
			//public static List<Match> scoreDetailCache = null;
	    	
			List<Integer> userFav = new ArrayList<Integer>();

			for(int count = 0; count < favArr.length; count++){
				if(!(favArr[count] == (-1))){
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
			int[] favArr = list.get(0).getUserFav();
	    	
			for(int count = 0; count < favArr.length; count++){
				favArr[count] = (-1);
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
