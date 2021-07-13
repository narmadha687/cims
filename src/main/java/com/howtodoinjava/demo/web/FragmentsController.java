package com.howtodoinjava.demo.web;

import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.AlertEntity;
import com.howtodoinjava.demo.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
public class FragmentsController {

	@GetMapping("/header")
	public String getHeader() {
		return "header.html";
	}

	@GetMapping("/markup")
	public String markupPage() {
		return "markup.html";
	}

	@GetMapping("/params")
	public String paramsPage() {
		return "params.html";
	}

}