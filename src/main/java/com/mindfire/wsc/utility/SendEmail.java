/**
 * 
 */
package com.mindfire.wsc.utility;
import com.mindfire.wsc.constants.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.stereotype.Service;

/**
 * @author bipins
 * This class is used to send email once use is registered
 * with its email id
 * @param user email
 */

@Service
public class SendEmail {	
    
	public void sendEmail(String emailid, String username) throws UnknownHostException {
		// Recipient's email ID needs to be mentioned.
	    String to = emailid;  
	    	    
	    // Get the Session
		Session session = sessionInfo(ConstantVaribales.from,ConstantVaribales.pass,ConstantVaribales.host);
		
	   try{
	      // Create a default MimeMessage object.
	      MimeMessage message = new MimeMessage(session);

	      // Set From: header field of the header.
	      message.setFrom(new InternetAddress(ConstantVaribales.from));

	      // Set To: header field of the header.
	      message.addRecipient(Message.RecipientType.TO,
	                               new InternetAddress(to));

	      // Set Subject: header field
	      message.setSubject("Congratulations, You have been registered");
	      
	      InetAddress ipAddr = InetAddress.getLocalHost();
	      System.out.println(ipAddr.getHostAddress());
	      
	      // Encode the UserName and Add to user to the actual message	     
	      String body = "Welcome to our portal. Your Username is: "+username+" and default password id 1234.<br> Please <a href=\"http://"+ipAddr.getHostAddress()+":8082/wsc/authorize/login?uname="+username+"\">Click Here</a> login to Portal";
	      message.setText(body, "UTF-8", "html");
	      
	      // Send message
	      Transport transport = session.getTransport("smtp");
	      transport.connect(ConstantVaribales.host, ConstantVaribales.from, ConstantVaribales.pass);
	      transport.sendMessage(message, message.getAllRecipients());
	      transport.close();
	      System.out.println("Sent message successfully....");
	   }catch (MessagingException mex) {
	      mex.printStackTrace();
	   }	
	}
	
	private static Session sessionInfo(final String from, final String pass, final String host) {		

	   // Get system properties
	   Properties properties = System.getProperties();
	   // Setup mail server
	   properties.put("mail.smtp.starttls.enable", "true");
	   properties.put("mail.smtp.host", host);
	   properties.put("mail.smtp.user", from);
	   properties.put("mail.smtp.password", pass);
	   properties.put("mail.smtp.port", "587");
	   properties.put("mail.smtp.auth", "true");

	   // Get the default Session object.
	   Session session = null;
	   session = Session.getInstance(properties,
	            new javax.mail.Authenticator() {
	                protected PasswordAuthentication getPasswordAuthentication() {
	                    return new PasswordAuthentication(from, pass);
	                }
	            });	
	   return session;
	}

	/*
	 * This Method is used to send the user password to the correspong email.
	 * @param user email , password	
	 */
	public void sentPasswordByEmail(String email, String password) throws UnknownHostException {
		
		// Get the Session Object
		Session session = sessionInfo(ConstantVaribales.from,ConstantVaribales.pass,ConstantVaribales.host);
		
		   try{
		      // Create a default MimeMessage object.
		      MimeMessage message = new MimeMessage(session);

		      // Set From: header field of the header.
		      message.setFrom(new InternetAddress(ConstantVaribales.from));

		      // Set To: header field of the header.
		      message.addRecipient(Message.RecipientType.TO,
		                               new InternetAddress(email));

		      // Set Subject: header field
		      message.setSubject("Don't Reveal your password");
		      
		      InetAddress ipAddr = InetAddress.getLocalHost();
		      System.out.println(ipAddr.getHostAddress());
		      
		      // Encode the UserName and Add to user to the actual message	     
		      String body = "Your password is <strong>"+password+" </strong>. Please reset the password after first login";
		      message.setText(body, "UTF-8", "html");
		      
		      // Send message
		      Transport transport = session.getTransport("smtp");
		      transport.connect(ConstantVaribales.host, ConstantVaribales.from, ConstantVaribales.pass);
		      transport.sendMessage(message, message.getAllRecipients());
		      transport.close();
		      System.out.println("Sent message successfully....");
		   }catch (MessagingException mex) {
		      mex.printStackTrace();
		   }
		
	}
}
