package com.cims.demo.web;

import com.cims.demo.exception.RecordNotFoundException;
import com.cims.demo.model.IncidentEntity;
import com.cims.demo.model.UserEntity;
import com.cims.demo.service.IncidentService;
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
@RequestMapping("/incidents")
public class IncidentController {

    @Autowired
    IncidentService service;

    @Autowired
    UserService userService;

    @RequestMapping
    public String getAllIncidents(Model model) {
        List<IncidentEntity> list = service.getAllIncidents();

        model.addAttribute("incidents", list);
        return "list-incidents";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editIncidentById(Model model, @PathVariable("id") Optional<Long> id) throws RecordNotFoundException {
        List<UserEntity> users = userService.getAllUsers();
        model.addAttribute("users", users);

        IncidentEntity entity = id.isPresent() ? service.getIncidentById(id.get()) : new IncidentEntity();
        model.addAttribute("incident", entity);
        return "add-edit-incident";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteIncidentById(Model model, @PathVariable("id") Long id) throws RecordNotFoundException {
        service.deleteIncidentById(id);
        return "redirect:/users";
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public String updateIncident(IncidentEntity incident) {
        service.updateIncident(incident);
        return "redirect:/incidents";
    }
}
