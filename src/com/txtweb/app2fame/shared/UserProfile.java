package com.txtweb.app2fame.shared;

/** Class UserProfile
 *  @version 1.0 
 *  @author Mayank Kandpal
 */

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;



@PersistenceCapable
public class UserProfile {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long key;
	
	@Persistent
	private String userHashKey; //key
	
	@Persistent
	private String[] userFav= new String[5]; // id of feeds, blank if not set; 5 feed subscriptions at max
	
	// Constructor
	public UserProfile(String userHashKey){
		this.userHashKey = userHashKey;
		for(int count =0; count<userFav.length; count++){
			 userFav[count]="blank";
		}
	}
	
	public Long getKey() {
		return key;
		}
		
	public void setKey(Long keyx) {
		this.key = keyx;
		}
	
	public String getUserHashKey(){
		return userHashKey;
	}
	
	public void setUserHashKey(String userHashKey) {
		this.userHashKey = userHashKey;
	}
	
	public String[] getUserFav(){
		return userFav;
	}
	
	public void setUserFav(String[] fav){
		this.userFav = fav;
	}
	
//	public void resetUserFav(){
//		for(int count =0; count<userFav.length; count++){
//			 userFav[count]="blank";
//		}
//	}
	
	public boolean isFavSet(){
		return (!(userFav[0].equals("blank") && userFav[1].equals("blank"))
				? true 
				: false);
	}

}