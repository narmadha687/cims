package com.cims.demo.web;

import com.cims.demo.exception.RecordNotFoundException;
import com.cims.demo.model.UserEntity;
import com.cims.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    UserService service;

    @RequestMapping
    public String signin() {
        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(Model model) {
        List<UserEntity> list = service.getAllUsers();

        model.addAttribute("users", list);
        return "list-users";
    }

    public String deleteUserById(Model model, @PathVariable("id") Long id)
            throws RecordNotFoundException {
        service.deleteUserById(id);
        return "redirect:/users";
    }

    public String createOrUpdateUser(UserEntity user) {
        service.createOrUpdateUser(user);
        return "redirect:/users";
    }
}
