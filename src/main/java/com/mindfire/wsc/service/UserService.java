/**
 * 
 */
package com.mindfire.wsc.service;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindfire.wsc.domain.User;
import com.mindfire.wsc.model.UserDTO;
import com.mindfire.wsc.repositories.UserRepository;
import com.mindfire.wsc.utility.MsgSendTwilio;
import com.mindfire.wsc.utility.SendEmail;
import com.twilio.sdk.TwilioRestException;

/**
 * @author sudhansub
 *
 */
@Service
public class UserService {
	
	
	@Autowired
	UserRepository userRepo;	
	
	@Autowired
	private SendEmail sendemail;
	
	@Autowired
	private MsgSendTwilio msgtwillio;
	
	/**
	 * Provides a single user by user name fetched from DB
	 * @param userName
	 * @return
	 */
	public UserDTO getUserByUserName(String userName){
		return convetUserDomainToUserDTO(userRepo.findByUserName(userName));
	}
		
	
	/**
	 * Provides all the users
	 * @return 
	 */
	public List<UserDTO> getAllUsers(){
		
		List<UserDTO> userDtos = new ArrayList<UserDTO>();
		List<User> users = userRepo.findAll();
		
		for(User user : users ){
			if(!user.getUserRole().equalsIgnoreCase("admin")){
			userDtos.add(convetUserDomainToUserDTO(user));
			}
		}
		
		return userDtos;
	}
	
	/*
	 * Save the users data into the Table
	 * @param userdto object
	 */
	public int save(UserDTO userdto)  {
		
		User user = convetUserDTOUserDomain(userdto);
		User existingUser = userRepo.findByUserName(userdto.getUserName());
		
		if(existingUser != null){			
			existingUser.setPassword(user.getPassword());
			existingUser.setEmail(user.getEmail());
			userRepo.saveAndFlush(existingUser);
			return 0;
		} else {			
			try {
				userRepo.saveAndFlush(user);
				sendemail.sendEmail(userdto.getEmail(), userdto.getUserName());
			} catch (Exception  e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		}		
	}
	
	/*
	 * Save the users data into the Table
	 * @param userdto object
	 */
	public int saveUser(UserDTO userdto) {
		
		User user = convetUserDTOUserDomain(userdto);
		User existingUser = userRepo.findByUserName(userdto.getUserName());
		
		if(existingUser != null){
			user.setUserId(existingUser.getUserId());			
		}
		if(("admin").equals(existingUser.getUserRole())){
			user.setUserRole("admin");		
		}
		
		userRepo.saveAndFlush(user);
		return 0;
	}
	
	/*
	 * Convert User Domain object to User DTO
	 * @param User object
	 */
	private UserDTO convetUserDomainToUserDTO(User user){
		UserDTO userDto = null;
		if(user != null){
			userDto = new UserDTO();
			userDto.setUserId(user.getUserId());
			userDto.setUserName(user.getUserName());
			userDto.setPassword(user.getPassword());
			userDto.setEmail(user.getEmail());
			userDto.setRole(user.getUserRole());			
			userDto.setAddress(user.getAddress());
			userDto.setFirstName(user.getFirstName());
			userDto.setLastName(user.getLastName());
			userDto.setPhone(user.getPhone());
			userDto.setPincode(user.getPincode());
			userDto.setSex(user.getSex());
			//userDto.setRole("user");
		}
		return userDto;
	}
	
	/*
	 * Convert UserDto object to User domain
	 * @param userdto object
	 */
	public User convetUserDTOUserDomain(UserDTO userDto){
		User user = null;
		if(userDto != null){
			user = new User();
			user.setUserId(userDto.getUserId());
			user.setUserName(userDto.getUserName());
			user.setEmail(userDto.getEmail());
			user.setPassword(userDto.getPassword());
			user.setAddress(userDto.getAddress());
			user.setFirstName(userDto.getFirstName());
			user.setLastName(userDto.getLastName());
			user.setPhone(userDto.getPhone());
			user.setPincode(userDto.getPincode());
			user.setSex(userDto.getSex());
			user.setUserRole("user");
		}
		return user;
	}

	/*
	 * Used to Modify the user data
	 * @param userid
	 */
	public UserDTO modifyUser(int eno) {
		UserDTO userDto = convetUserDomainToUserDTO(userRepo.findByUserId(eno));
		return userDto;	
		
	}
	
	/*
	 * User to delete the user
	 * @param userid
	 */
	public void deleteUser(int eno) {
		User user = userRepo.findByUserId(eno);
		userRepo.delete(user);				
	}

	/*
	 * Check User name using Username
	 * @param username
	 */
	public boolean checkUsername(String ename) {
		boolean flag = false;
		User user = userRepo.findByUserName(ename);
		if(user != null)
			flag = true;
		return flag;
	}	

	/*
	 * Update users data
	 * @param Userdto
	 */
	public int update(UserDTO userdto) {
		User user = convetUserDTOUserDomain(userdto);
		User existingUser = userRepo.findByUserName(userdto.getUserName());
		
		if(existingUser != null){
			user.setUserId(existingUser.getUserId());
		}
				
		userRepo.saveAndFlush(user);
		return 0;
		
	}

	// This Methhod is used to send Password in Email
	public void sendPasswordByEmail(String email, String password) throws UnknownHostException {
		// TODO Auto-generated method stub
		sendemail.sentPasswordByEmail(email,password);
	}

	// This Methhod is used to send Password to Mobile
	public void sendMessageToUser(String phone,String password) throws TwilioRestException {
		// TODO Auto-generated method stub
		msgtwillio.sendMessage(phone,password);
	}
	
}
