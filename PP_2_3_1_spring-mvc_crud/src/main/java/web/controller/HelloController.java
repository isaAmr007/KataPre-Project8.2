package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {
	private UserService userService;




	@GetMapping(value = "/")
	public String printWelcome(ModelMap model) {
		List<String> messages = new ArrayList<>();
		messages.add("Hello!");
		messages.add("I'm Spring MVC application");
		messages.add("5.2.0 version by sep'19 ");
		model.addAttribute("messages", messages);
		return "hello";
	}
	@ModelAttribute("newUser")
	public User getPerson(){
		return new User();
	}
	@GetMapping("/people")
	public String index(Model model){
		model.addAttribute("people",userService.getAllUsers());
		return "view/index";
	}

	@PostMapping("/people")
	public String creat(@ModelAttribute("newUser")@Valid User user,
						BindingResult bindingResult,Model model) {
		if (bindingResult.hasErrors()){
			model.addAttribute("people",userService.getAllUsers());
			return "view/index";
		}

		return "redirect:/people";
	}

	@DeleteMapping("/people/{id}")
	public String deletePerson(@PathVariable("id") int id){
		userService.removeUserById(id);
		return "redirect:/people";
	}
	@GetMapping("/people/{id}/edit")
	public String edit (@ModelAttribute("id") int id,Model model){
		model.addAttribute("user",userService.getUserById(id));
		return "view/edit";
	}

	@PatchMapping("/people/{id}")
	public String updatePerson(@ModelAttribute("user")@Valid User updateuser, BindingResult bindingResult){
		if (bindingResult.hasErrors()){
			return "view/edit";
		}
		userService.updateUser(updateuser);
		return "redirect:/people";
	}
}