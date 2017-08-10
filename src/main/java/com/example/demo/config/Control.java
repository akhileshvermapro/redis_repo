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

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

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
	
	@RequestMapping(value="/save1", method=RequestMethod.GET)
	@ResponseBody
	public String save1() throws InterruptedException{
		/*Student s = new Student("1","Akki","male",1);
		repo.saveStudent(s);*/
		JedisShardInfo jediShard = new JedisShardInfo("akkiRedisCache.redis.cache.windows.net", 6380, true);
		jediShard.setPassword("IxzBsanhDL27zdODrHR1nZ1IDULS6lPIqABzqjjsaGs=");
		Jedis jedis = new Jedis(jediShard);
		jedis.set("food", "barss");
		return jedis.get("food");
		
		//return "saved1";
	}
	

	
}
