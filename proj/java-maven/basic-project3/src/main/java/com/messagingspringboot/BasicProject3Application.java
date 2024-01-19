package com.messagingspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class BasicProject3Application {

	public static void main(String[] args) {
		SpringApplication.run(BasicProject3Application.class, args);
	}

}

@RestController
@RequestMapping("/kafka")
class HelloController{

	@Autowired
	private HelloProducer service;

	@RequestMapping("/hello/{name}")
	public String hello(@PathVariable("name") String name) {
		service.sendMessage("Hello, "+name);
		return "Ok";
	}
}
