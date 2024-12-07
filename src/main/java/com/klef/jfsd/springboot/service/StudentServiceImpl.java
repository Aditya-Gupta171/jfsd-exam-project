package com.klef.jfsd.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.springboot.model.Student;
import com.klef.jfsd.springboot.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService 
{
	@Autowired
	private StudentRepository repository;
	
	@Override
	public String addstudent(Student s) {
		repository.save(s);
		return "student added successfully";
	}

	@Override
	public String updatestudent(Student s) {
		Optional<Student> object =repository.findById(s.getId());
		String msg=null;
		if(object.isPresent()) {
			Student student=object.get();
			student.setAge(s.getAge());
			student.setContact(s.getContact());
			student.setDepartment(s.getDepartment());
			student.setEmail(s.getEmail());
			student.setGender(s.getGender());
			student.setName(s.getName());
			repository.save(student);
			msg="student updated successfully";
		}
		else {
			msg="student not found";
		}
		return msg;
	}

	@Override
	public String deletestudent(int sid) {
		Optional<Student> object =repository.findById(sid);
		String msg=null;
		if(object.isPresent()) {
			Student s=object.get();
			repository.delete(s);
			msg="student deleted successfully";
		}
		else{
			msg="student id not found";
		}
		return msg;
	}

	@Override
	public Student viewstudentbyid(int sid) {
		return repository.findById(sid).get();
	}

	@Override
	public List<Student> viewallstudents() {
		
		return (List<Student>) repository.findAll();
	}
	
	
	
}
