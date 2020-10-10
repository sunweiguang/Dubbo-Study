package com.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {
	private Integer nums  = 0;


	@GetMapping("async")
	public String test(){
		System.out.println("主线程开始执行..");
		System.out.println(nums);
		try {
			nums++;
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("主线程执行结束..");
		return "success";
	}

	@GetMapping("ync")
	public  WebAsyncTask<String> testddd(){
		log.info("主线程开始");
		Callable<String> result = () -> {
			log.info("副线程开始");
			try {
				TimeUnit.SECONDS.sleep(4);
			} catch (Exception e) {
			}
			log.info("副线程返回");
			return "这里是异步线程的返回结果";
		};

		log.info("主线程返回");

		WebAsyncTask<String> wat = new WebAsyncTask<String>(6000L, result);
		wat.onTimeout(new Callable<String>() {

			@Override
			public String call() throws Exception {
				return "异步线程执行超时，返回异常处理结果";
			}
		});
		return wat;
	}
}
