package com.bitcoinreaver.ThreadPool;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author qings2329
 *
 * @date 5:08:46 PM Jan 3, 2016
 */
public class ThreadPool {
	/** 查询线程池大小 **/
	private static int threadPoolSize = 10;
	
	public void main(String[] args){
		
		try {
			ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);
			CompletionService<PricePoolFlightResult> execComp = new ExecutorCompletionService<PricePoolFlightResult>(executor);
			ThreadPoolExecutor tpExecutor = (ThreadPoolExecutor)executor;
			
			PricePoolQueryTask pricePoolTask = new PricePoolQueryTask();
			execComp.submit(pricePoolTask);
			
			Future<PricePoolFlightResult> future = execComp.take();
			PricePoolFlightResult ppfResult = future.get();
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
