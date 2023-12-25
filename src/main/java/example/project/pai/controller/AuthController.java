package example.project.pai.controller;

import example.project.pai.dto.UserDto;
import jakarta.validation.Valid;
import example.project.pai.entity.User;
import example.project.pai.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("name")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // handler method to handle home page request
    @GetMapping("/index")
    public String home(Model model) {
        User user = userService.findUserByEmail(getLoggedEmail());
        model.addAttribute("name", user.getUsername());
        return "index";
    }

    @GetMapping("/")
    public String goHome(Model model) {
        User user = userService.findUserByEmail(getLoggedEmail());
        model.addAttribute("name", user.getUsername());
        return "index";
    }

    // handler method to handle user registration form request
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model) {
        User existingUser = userService.findUserByEmail(userDto.getUsername());

        if (existingUser != null && existingUser.getEmail() != null
                            && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");

        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    // handler method to handle login request
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    private String getLoggedEmail(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}