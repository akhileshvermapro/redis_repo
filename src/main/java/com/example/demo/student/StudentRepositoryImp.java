package com.example.demo.student;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.student.repo.Student;
import com.example.demo.student.repo.StudentRepository;

@Component
public class StudentRepositoryImp implements StudentRepository {
	
	private static final String KEY = "student";
	
	@Autowired
	private RedisTemplate<String, Student> redisTemplate;

	
	
	@Override
	public void saveStudent(Student student) {
		BoundHashOperations<String, String, Student> hashOps = redisTemplate.boundHashOps(KEY);
		hashOps.put(student.getId(), student);
				
	}
	
	public void deleteStudent(String id){
		//redisTemplate.delete(KEY);
		BoundHashOperations<String, String, Student> hashOps = redisTemplate.boundHashOps(KEY);
		hashOps.delete(id);
	}
	
	public Map<String, Student> findAllStudent(){
		BoundHashOperations<String, String, Student> hashOps = redisTemplate.boundHashOps(KEY);
		return hashOps.entries();
	}
	
	

}
