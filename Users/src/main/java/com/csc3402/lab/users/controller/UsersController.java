package com.csc3402.lab.users.controller;

import com.csc3402.lab.users.model.Users;
import com.csc3402.lab.users.repository.UsersRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UsersRepository usersRepository;

    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping("list")
    public String showUpdateForm(Model model) {
        model.addAttribute("users", usersRepository.findAll());
        return "list-user1";
    }

    @GetMapping("signup")
    public String showSignUpForm(Users users){
        return "add-user";
    }

    @PostMapping("add")
    public String addStaff(@Valid Users users, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }

        usersRepository.save(users);
        return "redirect:list";
    }

    // UPDATE STAFF
    @GetMapping("update")
    public String showUpdateMainForm(Model model) {
        model.addAttribute("users", usersRepository.findAll());
        return "choose-user-to-update";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Users users = usersRepository.findById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("users", users);
        return "update-user";
    }

    @PostMapping("update/{id}")
    public String updateStaff(@PathVariable("id") long id, @Valid Users users, BindingResult result, Model model) {
        if (result.hasErrors()) {
            users.setUserid((int) id);
            return "index";
        }

        model.addAttribute("users", usersRepository.findAll());
        usersRepository.save(users);
        return "list-user1";
    }

    // profile
    @GetMapping("/profile")
    public String showProfile(Model model) {
        Users user = usersRepository.findById(2)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id: 1"));
        model.addAttribute("user", user);
        return "profile";
    }

//    @PostMapping("/profile/update")
//    public String updateUser(@Valid @ModelAttribute("user") Users user, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "profile";
//        }
//        usersRepository.save(user);
//        return "redirect:/users/profile";
//    }

    @GetMapping("/profile/{userid}")
    public String showUserProfile(@PathVariable("userid") Integer userid, Model model) {
        Optional<Users> userOptional = usersRepository.findById(userid);
        if (userOptional.isPresent()) {
            model.addAttribute("user", userOptional.get());
            return "profile";
        } else {
            model.addAttribute("error", "User not found");
            return "error";
        }
    }

    @PostMapping("/profile/update")
    public String updateUserProfile(@Valid @ModelAttribute("user") Users user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "profile";
        }
        usersRepository.save(user);
        model.addAttribute("success", "Profile updated successfully!");
        return "redirect:/users/profile/" + user.getUserid();
    }

}

