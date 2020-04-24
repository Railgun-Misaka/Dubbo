package com.dubbo.service;

import com.dubbo.service.DubboService;

/**
 * 本地存根
 * @author Administrator
 *
 */
public class DubboServiceStub implements DubboService {
	
	private final DubboService dubboService ;
	
	/**
	 * @param dubboService  自动注入远程代理对象
	 */
	public DubboServiceStub(DubboService dubboService) {
		super();
		this.dubboService = dubboService ;
	}
	
	@Override
	public String getmsg() {
		// 此代码在客户端执行, 你可以在客户端做ThreadLocal本地缓存，或预先验证参数是否合法，等等
		System.out.println("------------调用本地存根-------------");
		return dubboService.getmsg();
	}

}
