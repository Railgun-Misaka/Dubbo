package com.dubbo.config;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dubbo.controller.DubboController;
import com.dubbo.watcher.ZookeeperWatcher;

@Configuration
public class ZookeeperConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(ZookeeperConfig.class);
	
	@Value("${zookeeper.address}")
    private String address;
	
	@Value("${zookeeper.timeout}")
    private int timeout;
	
	private ZooKeeper zooKeeper = null;
	
	private Stat stat = new Stat();
	
	private final CountDownLatch countDownLatch = new CountDownLatch(1);
	
	@Bean(name = "zkClient")
    public ZooKeeper zkClient(){
        
        try {
            //连接成功后，会回调watcher监听，此连接操作是异步的，执行完new语句后，直接调用后续代码
            //  可指定多台服务地址 127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183
            zooKeeper = new ZooKeeper(address, timeout, new ZookeeperWatcher(countDownLatch, stat));
            //等待zk连接成功的通知
            countDownLatch.await();
            logger.info("【初始化ZooKeeper连接状态....】={}",zooKeeper.getState());
            //从zk中获取flag
            DubboController.flag = Boolean.parseBoolean(new String(zooKeeper.getData("/jvmcache/data/flag", true, stat)));
        }catch (Exception e){
            logger.error("初始化ZooKeeper连接异常....】={}",e);
        }
         return  zooKeeper;
    }
	
}
