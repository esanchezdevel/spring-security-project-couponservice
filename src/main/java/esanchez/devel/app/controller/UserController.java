package esanchez.devel.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import esanchez.devel.app.model.User;
import esanchez.devel.app.repository.UserRepository;
import esanchez.devel.app.security.SecurityService;

@Controller
public class UserController {

	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/")
	public String showLogin() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(String email, String password) {
		boolean loginResponse = securityService.login(email, password);
		
		if (loginResponse) {
			return "index";
		} else {
			return "login";
		}
	}
	
	@GetMapping("/showReg")
	public String showRegistrationPage() {
		return "registerUser";
	}
	
	@PostMapping("/registerUser")
	public String register(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword())); //Remember to encode the password before save it
		userRepository.save(user);
		
		return "login";
	}
}
