package com.sinochem.crude.trade.wechat.helper;

import com.sinochem.crude.trade.wechat.domain.WechatSendmsgLogs;
import com.sinochem.crude.trade.wechat.service.impl.WechatSendmsgLogServiceImpl;
import com.sinochem.it.b2b.common.http.HttpConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
@Service
public class ForkJoinSendmsg extends RecursiveTask<List<WechatSendmsgLogs>> {
    private static final int THRESHOLD_NUM = 10;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private WechatSendmsgLogServiceImpl playservice;

    private List<WechatSendmsgLogs> list;

    private HttpConnectionManager httpClientManager;
    public ForkJoinSendmsg(List<WechatSendmsgLogs> list,HttpConnectionManager httpClientManager) {
        this.list = list;
        this.httpClientManager=httpClientManager;
    }


    @Override
    protected List<WechatSendmsgLogs> compute() {

        playservice = new WechatSendmsgLogServiceImpl();
        //如果任务足够小就计算任务
        boolean canCompute = list.size() <= THRESHOLD_NUM;
        if (canCompute) {

            List<WechatSendmsgLogs> reslist = playservice.SendMsgList(list,httpClientManager);
            return reslist;
        } else {
            // 如果任务大于阈值，就分裂成两个子任务计算
            int middle = list.size() / 2;
            List<WechatSendmsgLogs> leftlist = new ArrayList<WechatSendmsgLogs>(list.subList(0, middle));
            List<WechatSendmsgLogs> rightlist = new ArrayList<WechatSendmsgLogs>(list.subList(middle , list.size()));

            ForkJoinSendmsg leftTask = new ForkJoinSendmsg(leftlist,httpClientManager);
            ForkJoinSendmsg rightTask = new ForkJoinSendmsg(rightlist,httpClientManager);
            // 并行执行两个“小任务”
//            leftTask.fork();
//            rightTask.fork();
            invokeAll(leftTask, rightTask);

            // 把两个“小任务”的结果合并起来
            List<WechatSendmsgLogs> result = leftTask.join();
            result.addAll(rightTask.join());
            return result;
        }
    }

    public  List<WechatSendmsgLogs> WXSendmsg( )
    {
        long startTime = System.currentTimeMillis();
        List<WechatSendmsgLogs> reslist = null;//Collections.synchronizedList(new ArrayList<WechatSendmsgLogs>());
        ForkJoinPool forkjoinPool = new ForkJoinPool(THRESHOLD_NUM);
        try {
            //生成一个计算任务
            ForkJoinSendmsg task = new ForkJoinSendmsg(list,httpClientManager);
            // 提交可分解的PrintTask任务
            reslist = forkjoinPool.invoke(task);
            // forkjoinPool.awaitTermination(1, TimeUnit.SECONDS);//阻塞当前线程直到 ForkJoinPool 中所有的任务都执行结束
        }
        catch (Exception e)
        {
            logger.error("forkjoin发送微信消息：",e);
        }
        finally {
            // 关闭线程池
            forkjoinPool.shutdown();
        }
        System.out.println("成功");
        long endTime = System.currentTimeMillis();
        long executeTime = endTime - startTime;
        System.out.println("推送微信消息耗时:"+executeTime);
        return  reslist;
    }

    public static void main(String[] args) throws InterruptedException {
//         List<WechatSendmsgLogs> list = new ArrayList<WechatSendmsgLogs>();//Collections.synchronizedList(new ArrayList<WechatSendmsgLogs>());
//        for (int i = 0; i < 100; i++) {
//            WechatSendmsgLogs logs = new WechatSendmsgLogs();
//            logs.setContent(String.valueOf(i));
//            list.add(logs);
//        }
//       ForkJoinSendmsg.WXSendmsg(list);
    }
}
