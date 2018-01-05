package com.qpl.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qpl.service.Interf.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("/upload")
	@ResponseBody
	public Map upload(@RequestParam(value="file",required=false) MultipartFile multipart,Model model){
		
		return userService.uploadXls(multipart);
	}
	@RequestMapping("/daochu")
	@ResponseBody
	public Map daochu(){
		
		return userService.yijiandaochu();
	}
}
