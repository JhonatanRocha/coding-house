package com.coding.house.store.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.coding.house.store.model.User;

@Repository
public class UserDAO implements UserDetailsService {

	@PersistenceContext
    private EntityManager manager;
	
	public User loadUserByUsername(String email) {
		List<User> users = manager.createQuery("select u from User u where u.email = :email", User.class).setParameter("email", email).getResultList();
		
		if(users.isEmpty()) {
			throw new UsernameNotFoundException("User " + email + " not found.");
		}
		
		return users.get(0);
	}
}
