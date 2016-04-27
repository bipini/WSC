/**
 * 
 */
package com.mindfire.wsc.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mindfire.wsc.model.UserDTO;

/**
 * @author bipinis
 *
 */

@Repository
public class UserDAO{

	public UserDTO processLogin(String uname, String password) {
		 return loginAuthorization(uname, password);
		
	}
		
	public UserDTO loginAuthorization(String uname, String pass) {
		String sql = "select user_id,user_name,password,user_email, user_role from user where user_name=\""+uname+"\" and password=\""+pass+"\"";		
		UserDTO user = null;
		try {			

		System.out.println("Connection Ends");
		
		
		} catch(DataAccessException e) {
			e.printStackTrace();			
		}
		return user;
	}

}
