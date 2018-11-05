package com.sinochem.crude.trade.wechat.helper;

import com.sinochem.crude.trade.wechat.constant.AccessTokenUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

///程序启动时候自动调用，启动线程；
@Service
public class StartThreadClass implements InitializingBean {

	public void afterPropertiesSet() throws Exception {
		
		System.out.println("线程启动----------------------------");

		//startNotify();
	}

	public ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);
//	public ExecutorService fixedThreadPool1 = new ThreadPoolExecutor(5,30,1000);
   
	public void startNotify() {
		fixedThreadPool.execute(new Runnable() {
			public void run() {
                try {
                    Thread.sleep(60000);
//					AccessTokenUtil.timerGetToken();
//					AccessTokenUtil.timerDeleteToken();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


			}
		});
	}
}
