package com.txtweb.app2fame.server;

	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.io.PrintWriter;
	import java.net.URL;
	import java.net.URLConnection;
	import java.net.URLEncoder;
	import java.util.List;
	import java.util.logging.Logger;

	import javax.servlet.ServletException;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

import com.txtweb.app2fame.utils.Constants;
import com.txtweb.app2fame.utils.Constants.FeedsKeyWords;

public class CheckPush extends HttpServlet {
			
	private static String pageHtml = "<html><head><meta name=\"txtweb-appkey\" content=\"0e5e27f3-9cc5-4c60-a420-febe0ff4149f\" /><title> Page title </title></head><body>%s</body></html>";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
	throws IOException {
		
		for(FeedsKeyWords fkw : FeedsKeyWords.values())
		{
			System.out.println(fkw.ordinal() + " " + fkw.name());
			
		}
		PrintWriter out = resp.getWriter();

	}
	

}
