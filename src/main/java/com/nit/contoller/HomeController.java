package com.nit.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	 
	@GetMapping("/home")
	public String home() {
		return "Welcome to the home";
	}
	 
	 @GetMapping("/user")
	 
	 public String user() {
		 return "welcome user";
			 }
	  @GetMapping("/admin")
	  public String admin() {
		  return "welcome admin";
	  }
}
