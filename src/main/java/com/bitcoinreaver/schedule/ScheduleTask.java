package com.bitcoinreaver.schedule;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author qings2329
 *
 * @date 4:53:18 PM Dec 16, 2015
 */
public class ScheduleTask {
	
	public ScheduleTask(){
		// 定时每天凌晨00:05执行
		Calendar cal = Calendar.getInstance();
		
		Runnable queryRun = new Runnable() {
			@Override
            public void run() {

            }  
        };
        ScheduledExecutorService queryService = Executors.newSingleThreadScheduledExecutor();
        queryService.scheduleAtFixedRate(queryRun, 10, 60 * 60 * 24, TimeUnit.SECONDS); 
        
        // 检查查询任务
        Runnable checkRun = new Runnable(){
        	@Override
        	public void run(){
        		
        	}
        };
        ScheduledExecutorService scanService = Executors.newSingleThreadScheduledExecutor();
        scanService.scheduleAtFixedRate(checkRun, 10, 1, TimeUnit.SECONDS);
	}
	
	public static void main(String[] args){
		Runnable runnable = new Runnable() {  
            public void run() {
                // task to run goes here  
                System.out.println("Hello !!");  
            }  
        };  
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();  
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间  
        service.scheduleAtFixedRate(runnable, 10, 1, TimeUnit.SECONDS); 
	}
	
	// 第四种方法：安排指定的任务task在指定的时间firstTime开始进行重复的固定速率period执行． 
	// Timer.scheduleAtFixedRate(TimerTask task,Date firstTime,long period) 
	public static void timer4() {
		Calendar calendar = Calendar.getInstance(); 
		calendar.set(Calendar.HOUR_OF_DAY, 12); // 控制时 
		calendar.set(Calendar.MINUTE, 0); // 控制分 
		calendar.set(Calendar.SECOND, 0); // 控制秒 
	
		Date time = calendar.getTime(); // 得出执行任务的时间,此处为今天的12：00：00 
	
		Timer timer = new Timer(); 
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() { 
				System.out.println("-------设定要指定任务--------"); 
			} 
		}, time, 1000 * 60 * 60 * 24);// 这里设定将延时每天固定执行 
	} 
	
}
