package com.dubbo.watcher;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dubbo.controller.DubboController;
import com.dubbo.util.SpringUtil;

public class ZookeeperWatcher implements Watcher {
	
	private static final Logger logger = LoggerFactory.getLogger(WatchedEvent.class);
	
	private CountDownLatch countDownLatch ;
	
	private ZooKeeper zkClient ;
	
	private Stat stat ;
	
	public ZookeeperWatcher(CountDownLatch countDownLatch, Stat stat) {
		super();
		this.stat = stat;
		this.countDownLatch = countDownLatch;
	}
	
	@Override
	public void process(WatchedEvent event) {
		logger.info("【Watcher监听事件】={}",event.getState());
        logger.info("【监听路径为】={}",event.getPath());
        logger.info("【监听的类型为】={}",event.getType()); //  三种监听类型： 创建，删除，更新
        
        if(event.getType() != EventType.None && event.getPath() != null) {
        	try {
        		if(zkClient == null)
        			zkClient = SpringUtil.getBean(ZooKeeper.class);
				String flag = new String(zkClient.getData(event.getPath(), true, stat));
				DubboController.flag = Boolean.parseBoolean(flag);
			} catch (KeeperException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
        }
        
        countDownLatch.countDown();
	}

}
