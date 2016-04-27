package com.mindfire.wsc.utility;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import com.mindfire.wsc.constants.ConstantVaribales;

import java.util.*; 

import com.twilio.sdk.*; 
import com.twilio.sdk.resource.factory.*; 
import com.twilio.sdk.resource.instance.*; 

/*
 * This Class is used to Send Message to Phone
 */

@Service
public class MsgSendTwilio {
	
	static TwilioRestClient client = new TwilioRestClient(ConstantVaribales.ACCOUNT_SID, ConstantVaribales.AUTH_TOKEN);
	
	public void sendMessage(String phone,String password) throws TwilioRestException {
		// Build the parameters 
		 List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		 params.add(new BasicNameValuePair("To", phone)); 
		 params.add(new BasicNameValuePair("From", "+12248367756")); 
		 params.add(new BasicNameValuePair("Body", "Your Password is "+password+". Please reset the password after first login"));   
	 
		 MessageFactory messageFactory = client.getAccount().getMessageFactory(); 
		 Message message = messageFactory.create(params); 
		 System.out.println(message.getSid()); 		
		  
		 System.out.println(message.getBody()); 
	} 
	
	
}
