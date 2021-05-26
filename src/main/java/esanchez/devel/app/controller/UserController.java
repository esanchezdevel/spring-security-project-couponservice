package esanchez.devel.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import esanchez.devel.app.security.SecurityService;

@Controller
public class UserController {

	@Autowired
	private SecurityService securityService;
	
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
}
