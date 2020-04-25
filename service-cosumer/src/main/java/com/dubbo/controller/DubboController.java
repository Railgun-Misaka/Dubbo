package com.dubbo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dubbo.service.BilibiliService;
import com.dubbo.service.MsgService;
import com.dubbo.util.ZookeeperUtil;

@RestController
public class DubboController {
	
	@Autowired MsgService msgService ;
	
	@Autowired BilibiliService bilibiliService ;
	
	public static boolean flag = true ;
	
	@GetMapping("/data")
	public String data() {
		if(flag)
			return msgService.get();
		return bilibiliService.get();
	}
	
	@GetMapping("/switch")
	public boolean switchs() {
		ZookeeperUtil.updateNode("/jvmcache/data/flag", !flag + "");
		return flag;
	}
}
