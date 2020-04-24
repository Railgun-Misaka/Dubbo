package com.dubbo.service;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import com.dubbo.service.DubboService;

@Service
public class MsgService {

	@Reference(stub="true")
	DubboService dubboServce ;
	
	public String get() {
		return dubboServce.getmsg();
	}
}
