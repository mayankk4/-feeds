package com.txtweb.app2fame.server;

/** Class Feeds
 * @version 1.0
 * @author Mayank Kandpal
 */

// Publisher Key: 3C3329D3-D7AF-443D-8FE9-4027E12F8E25
// Application Key: 0e5e27f3-9cc5-4c60-a420-febe0ff4149f

// TODO:
//

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.txtweb.app2fame.shared.UserProfile;
import com.txtweb.app2fame.utils.Constants;

public class Feeds extends HttpServlet {
	
	private static final Logger LOGGER = Logger.getLogger(Feeds.class.getName());
	private static String pageHtml = "<html><head><meta name=\"txtweb-appkey\" content=\"0e5e27f3-9cc5-4c60-a420-febe0ff4149f\" /><title> Page title </title></head><body>%s</body></html>";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
	throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		
		String txtWebMsg = (req.getParameter(Constants.TEXT_WEB_MSG) != null ? req
				.getParameter(Constants.TEXT_WEB_MSG).replace(" ","").toLowerCase() : "");
		
		
		String hashKey = (req.getParameter(Constants.TEXT_WEB_MOB) != null ? req
				.getParameter(Constants.TEXT_WEB_MOB) : "");
		
		if((UserDatabase.searchUser(hashKey)) == null){  // search for hashkey in database
			UserDatabase.addUser(hashKey);
		}
		
		UserProfile user = UserDatabase.searchUser(hashKey); // reference to the user
		
//		PRINT USER INFO : 	[Testing purpose only]
//		out.println("The current user is : " + user.getUserHashKey() + " " + 
//					user.getKey() + " " + user.getUserFav()[0] + " " + 
//					user.getUserFav()[1] + "<br>");
		/* end */
		
// Populate cache initially.
//		if(DataStructure.scoreCacheRefreshTime == null || DataStructure.isScoreCacheRefreshRequired()){
//			DataStructure.resetScoreCache();
//			DataStructure.refreshBothSMSCache();
//		}
		

		LOGGER.info("txtWebMsg value : " + txtWebMsg);
		
		// analysis of input starts here ......
		
		// IF message is @feeds
		if (txtWebMsg.equals("")) {
				out.println(String.format(pageHtml, "This feature will be added soon!"
								) );
					
		// IF message is @feeds <some_keyword>		
		}else if(isValidKeyword(txtWebMsg)){  // To check if the input string doesnt match any keyword
			keywordHandler(txtWebMsg, user, out);
				// Function To handle keywords in case it is detected	
		
		// IF message is @feeds <ID>
		}else{
			LOGGER.info("Invalid Keyword.");
			out.println(String.format(pageHtml, "Please enter a valid keyword!"
			) );
		}//end else
		
	}// end doGet()

// change int to UserProfile
	private static void keywordHandler(String txtWebMsg, UserProfile user, PrintWriter out) throws IOException{
				
// Keep removing keywords from here as the handlers are implemented.
	
			if(txtWebMsg.startsWith("list")){
				// show the list of all the available subscriptions
				String output = "Available subsctiptions are :<br>";
				
				
				out.println(String.format(pageHtml, output + "SMS @feeds LIST to view list of all feeds."));
		
			} // end options handler
			
			if(txtWebMsg.startsWith("mysub")){
				
				List<String> userFav = new ArrayList<String>();
		    	
		    	userFav = UserDatabase.showUserFav(user.getUserHashKey());
				
				String output ="";
				
				for (String feed : userFav) {
					//XXX
					String feedname ="feedname";
					//feedname = ?? 
				    output = output + " " + feed + " : " + feedname + "<br>";
				}

			
				if(output == ""){
					out.println(String.format(pageHtml,"You have not subscribed to any feed.<br>" + "SMS @feeds LIST to view list of all feeds."));
				}else{
					out.println(String.format(pageHtml,"Your subscriptions are:<br>" + output));
				}

			} // end mysub handler
			
			if(txtWebMsg.startsWith("sub")){
				
				String matchId = txtWebMsg.substring(3);
				Boolean isValid = false;
				   
				isValid = UserDatabase.setUserFav(user.getUserHashKey(), matchId);
											
				if(isValid){					
					out.println(String.format(pageHtml,"You have been subscribed to the feed.<br>" + "SMS @feeds LIST to view list of all feeds."));
				} else {
					out.println(String.format(pageHtml,"Incorrect Feeds ID.<br>"+ "SMS @feeds LIST to view list of all feeds." + "SMS @feeds HELP to view help content."));
				}

			} // end sub handler
			
			if(txtWebMsg.startsWith("unsub")){
				if(txtWebMsg.startsWith("unsuball")){
					UserDatabase.resetAllUserFav(user.getUserHashKey());
					out.println(String.format(pageHtml, "You have been unsubscribed from all feeds."));
				}else{
					String feedid = txtWebMsg.substring(5);
					boolean exists = UserDatabase.resetUserFav(user.getUserHashKey(), feedid);
					if(exists){
						out.println(String.format(pageHtml, "You have been unsubscribed from the feed " + feedid));
					}else{ 
						out.println(String.format(pageHtml, feedid +" does not exist in your subscriptions."));
					}
				}
		
			}// end unsub handler
						
			if(txtWebMsg.startsWith("help")){
				out.println(String.format(pageHtml, Constants.HELP_MESSAGE));
			} // end help handler
						
	} // end function keyword handler
         
	// This function checks whether the input contains a valid keyword
	private static boolean isValidKeyword(String txtWebMsg){

		return (txtWebMsg.startsWith("list") || 
				txtWebMsg.startsWith("mysub") ||  
				txtWebMsg.startsWith("sub") ||
				txtWebMsg.startsWith("unsub") ||
				txtWebMsg.startsWith("help"));
	}

	
}