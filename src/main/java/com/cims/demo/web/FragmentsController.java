package com.cims.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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