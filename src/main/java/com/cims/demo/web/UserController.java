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
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService service;

    @RequestMapping
    public String getAllUsers(Model model) {
        List<UserEntity> list = service.getAllUsers();

        model.addAttribute("users", list);
        return "list-users";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}", "/add"})
    public String editUserById(Model model, @PathVariable("id") Optional<Long> id)
            throws RecordNotFoundException {
        if (id.isPresent()) {
            UserEntity entity = service.getUserById(id.get());
            model.addAttribute("user", entity);
        } else {
            model.addAttribute("user", new UserEntity());
        }
        return "add-edit-user";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteUserById(Model model, @PathVariable("id") Long id)
            throws RecordNotFoundException {
        service.deleteUserById(id);
        return "redirect:/users";
    }

    @RequestMapping(path = "/adduser", method = RequestMethod.POST)
    public String createOrUpdateUser(UserEntity user) {
        if (user == null) {
            System.out.println("************************** USER IS NULL");
        } else {
            service.createOrUpdateUser(user);
        }
        return "redirect:/users";
    }
}
