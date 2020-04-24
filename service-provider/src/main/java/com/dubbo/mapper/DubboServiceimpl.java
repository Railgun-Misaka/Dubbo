package com.dubbo.mapper;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import com.dubbo.service.DubboService;

@Service
@Component
public class DubboServiceimpl implements DubboService{

	@Override
	public String getmsg() {
		return "hahaha!";
	}
}
