package br.appLogin.appLogin.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.appLogin.appLogin.model.User;
import br.appLogin.appLogin.repository.UserRepository;
import br.appLogin.appLogin.service.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
public class LoginController {

	@Autowired
	private UserRepository ur;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/")
	public String dashboard(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
		model.addAttribute("name" , CookieService.getCookie(request, "userName"));
		return "index";
	}

	@PostMapping("/login")
	public String loginUser(@ModelAttribute User user, Model model, HttpServletResponse response) throws UnsupportedEncodingException {
		User login = this.ur.login(user.getEmail(), user.getPassword());
		if (login != null) {
			CookieService.setCookie(response, "userId", String.valueOf(login.getId()), 10000);
			CookieService.setCookie(response, "userName", String.valueOf(login.getUser()), 10000);
			return "redirect:/";
		}
		model.addAttribute("erro", "Invalid User");
		return "/login";
	}
	
	@GetMapping("/loginRedirect")
	public String loginRedirect() {
	    return "redirect:/register"; // Redireciona para a página de cadastro
	}
	
	@GetMapping("/sair")
	public String sair(HttpServletResponse response) throws UnsupportedEncodingException {
		CookieService.setCookie(response, "userId", String.valueOf(""), 0);
		return "/login";
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("userLogin") @Valid User userLogin, BindingResult result) {
		if (result.hasErrors()) {
			return "redirect:/register";
		}

		ur.save(userLogin);
		return "redirect:/login";
	}

}
