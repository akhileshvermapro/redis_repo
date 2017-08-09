package com.example.demo.student.repo;

import java.util.Map;

public interface StudentRepository {
	void saveStudent(Student student);
	void deleteStudent(String id);
	Map<String, Student> findAllStudent();
}
