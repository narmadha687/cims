package com.howtodoinjava.demo.web;

import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.AlertEntity;
import com.howtodoinjava.demo.model.AlertEntity;
import com.howtodoinjava.demo.service.AlertService;
import com.howtodoinjava.demo.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/alerts")
public class AlertController
{
	@Autowired
	AlertService service;

	@RequestMapping
	public String getAllAlerts(Model model)
	{
		List<AlertEntity> allAlerts = service.getAllAlerts();
		List<AlertEntity> openAlerts = new ArrayList<>();

		for(AlertEntity alert : allAlerts)
			if(! alert.getStatus().equalsIgnoreCase("Closed"))
				openAlerts.add(alert);

		model.addAttribute("alerts", openAlerts);
		return "list-alerts";
	}

	@RequestMapping(path = {"/edit", "/edit/{id}"})
	public String editAlertById(Model model, @PathVariable("id") Optional<Long> id) 
							throws RecordNotFoundException 
	{
		if (id.isPresent()) {
			AlertEntity entity = service.getAlertById(id.get());
			model.addAttribute("alert", entity);
		} else {
			model.addAttribute("alert", new AlertEntity());
		}
		return "add-edit-alert";
	}
	
	@RequestMapping(path = "/delete/{id}")
	public String deleteAlertById(Model model, @PathVariable("id") Long id) 
							throws RecordNotFoundException 
	{
		service.deleteAlertById(id);
		return "redirect:/users";
	}

	@RequestMapping(path = "/addalert", method = RequestMethod.POST)
	public String createOrUpdateAlert(AlertEntity alert)
	{
		service.createOrUpdateAlert(alert);
		return "redirect:/users";
	}
}
