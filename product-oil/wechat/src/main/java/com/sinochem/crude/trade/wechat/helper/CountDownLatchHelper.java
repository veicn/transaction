//package com.sinochem.crude.trade.wechat.helper;
//
//import java.util.List;
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//public class CountDownLatchHelper {
//    public static void main(String[] args) throws InterruptedException {
//        int number = 3;
//        CountDownLatch latch = new CountDownLatch(number);
//
//        for (int i = 0; i < number; i++) {
//            ThreadDemo demo = new ThreadDemo(latch);
//            demo.start();
//            System.out.println(i);
//        }
//        latch.await();
//        System.out.println("Check it Out");
//    }
//    public <T>  List<T> RunList(List<T> list) throws InterruptedException {
//        CountDownLatch latch = new CountDownLatch(list.size());
//        fixedThreadPool.execute(new ThreadDemo(latch)
//        {
//            public void run(CountDownLatch latch) {
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            latch.countDown();
//        });
//
////        for (int i = 0; i < list.size(); i++) {
////            ThreadDemo demo = new ThreadDemo(latch);
////            demo.start();
////            System.out.println(i);
////        }
//        latch.await();
//        return list;
//    }
//
//    public ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
////	public ExecutorService fixedThreadPool1 = new ThreadPoolExecutor(5,30,1000);
//
//    public void startNotify() {
//
////                new ThreadDemo() {
////            public void run(CountDownLatch latch) {
////                try {
////                    Thread.sleep(60000);
//////					AccessTokenUtil.timerGetToken();
//////					AccessTokenUtil.timerDeleteToken();
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
////                latch.countDown();
////
////            }
////        });
//    }
//
//    static class ThreadDemo extends Runnable  {
//
//        private CountDownLatch latch;
//
//        public ThreadDemo(CountDownLatch latch) {
//            this.latch = latch;
//        }
//
//        public void run() {
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            latch.countDown();
//        }
//    }
//}
