package com.dubbo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dubbo.service.MsgService;

@RestController
public class DubboController {
	
	@Autowired MsgService msgService ;
	
	@GetMapping("/data")
	public String data() {
		return msgService.get();
	}
}
