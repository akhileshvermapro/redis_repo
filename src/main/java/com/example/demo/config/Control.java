package com.example.demo.config;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.student.repo.Student;
import com.example.demo.student.repo.StudentRepository;

@RestController
@RequestMapping("/redis/*")
public class Control {
	
	/*@Autowired
	StringRedisTemplate template;
	
	@Autowired
	CountDownLatch latch;*/
	
	@Autowired
	StudentRepository repo;
	/*
	@RequestMapping(value="/runRedis", method=RequestMethod.GET)
	@ResponseBody
	public String runRedis() throws InterruptedException{
		System.out.println("Akkikkkiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii  ");
		template.convertAndSend("chat", "Hello from Redis!");
		System.out.println("Akkikkkiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii  1");
		this.latch.await();
		return "Hii";
	}*/
	
	@RequestMapping(value="/save", method=RequestMethod.GET)
	@ResponseBody
	public String save() throws InterruptedException{
		Student s = new Student("1","Akki","male",1);
		repo.saveStudent(s);
		return "saved";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	@ResponseBody
	public String delete() throws InterruptedException{
		repo.deleteStudent("1");
		return "deleted";
	}
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Student> findAll() throws InterruptedException{
		return repo.findAllStudent();
		
	}
	
	@RequestMapping(value="/healthCheck", method=RequestMethod.GET)
	@ResponseBody
	public String healthCheck() throws InterruptedException{
		return "System is UP!!";
		
	}
	

	
}
