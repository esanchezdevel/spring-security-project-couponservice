package esanchez.devel.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import esanchez.devel.app.model.User;
import esanchez.devel.app.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("user not found for email: " + username);
		} 
		return new org.springframework.security.core.userdetails.User(
				user.getEmail(), 
				user.getPassword(), 
				user.getRoles());
	}

}
