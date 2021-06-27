package com.techmentor.Service;

import com.techmentor.Model.User;
import com.techmentor.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepo userrepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("FInd User By username");
		Optional<User> user = userrepo.findById(username);
		if (user.isEmpty()) {
			throw new UsernameNotFoundException("User not found!");
		}
		return user.get();
	}

	public User finduser(String username) {
		System.out.println("FInd User By username");
		Optional<User> user = userrepo.findById(username);
		if (user.isEmpty()) {
			return null;
		}
		return user.get();
	}

	public User save(User user) {
		return userrepo.save(user);

	}

}
