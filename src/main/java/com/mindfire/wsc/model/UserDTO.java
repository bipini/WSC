/**
 * 
 */
package com.mindfire.wsc.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Service;

/**
 * @author bipinis
 *
 */

@Service
public class UserDTO {
	
	@Size(min = 3, max = 50)
	private String userName;
	
	@Size(min = 3, max = 20)
	private String password;
	
	@Size(min = 3, max = 20)
	private String existingpwd;
	
	@Size(min = 3, max = 20)
	private String confpassword;
	
	@Pattern(regexp=".+@.+\\.[a-z]+")
	private String email;
	
	private String role;		
	private int userId;
	
	@Size(min=10,max = 15)
	@Pattern(regexp="(^$|[0-9]{15})")
	private String phone;
	
	@Size(max = 6)
	private String pincode;	
	
	@Size(max = 50)
	private String firstName;
	
	@Size(max = 50)
	private String lastName;
	
	private String sex;
	
	@Size(max = 300)
	private String address;
	
	private String userRole;
	
	
	/**
	 * @return the userRole
	 */
	public String getUserRole() {
		return userRole;
	}
	/**
	 * @param userRole the userRole to set
	 */
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the pincode
	 */
	public String getPincode() {
		return pincode;
	}
	/**
	 * @param pincode the pincode to set
	 */
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the username
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param username the username to set
	 */
	public void setUserName(String username) {
		this.userName = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * @return the userid
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userid the userid to set
	 */
	public void setUserId(int userid) {
		this.userId = userid;
	}
	
	/**
	 * @return the existingpwd
	 */
	public String getExistingpwd() {
		return existingpwd;
	}
	/**
	 * @param existingpwd the existingpwd to set
	 */
	public void setExistingpwd(String existingpwd) {
		this.existingpwd = existingpwd;
	}
	/**
	 * @return the confpassword
	 */
	public String getConfpassword() {
		return confpassword;
	}
	/**
	 * @param confpassword the confpassword to set
	 */
	public void setConfpassword(String confpassword) {
		this.confpassword = confpassword;
	}
}
