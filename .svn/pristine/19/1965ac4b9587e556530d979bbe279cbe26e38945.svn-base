package com.mindfire.wsc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindfire.wsc.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByUserName(String userName);
	User findByUserId(int userId);
	
	List<User> findAll();
	void delete(User user);	
	
}
