package com.qpl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping(value="/")
	public String Index(){
		
		return "index";
	}
	
	@RequestMapping(value="/{page}")
	public String Page(@PathVariable String page){
		
		return page;
	}
	
	
}
