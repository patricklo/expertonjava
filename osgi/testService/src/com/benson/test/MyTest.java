package com.benson.test;

import com.benson.service.MyService;

public class MyTest {
	private MyService myService;

	public void setMyService(MyService myService) {
		this.myService = myService;
	}

	void start() {

		System.out.println("start.......");
		System.out.println(myService.getHello());

	}

	void stop() {
		System.out.println("stop.....");
	}

}
