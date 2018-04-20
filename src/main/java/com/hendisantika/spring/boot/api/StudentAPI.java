package com.hendisantika.spring.boot.api;

import com.hendisantika.spring.boot.model.Student;
import org.jfairy.Fairy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@RestController
public class StudentAPI {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static Map<Integer, Student> studentDB;
	private Fairy fairy = Fairy.create();

	/**
	 * Create a Dummy Student database
	 *
	 */

	@PostConstruct
	public void init() {
		logger.info("Initializing Student Database..");
		studentDB = new HashMap<>();
		for (int i = 0; i < 100; i++) {
			Student student = new Student(i, fairy.person());
			studentDB.put(new Integer(i), student);
		}
		logger.info("Student Database intialized..");
	}

	@RequestMapping("/students")
	public Student searchStudent(@RequestParam(name = "studentId", required = true) Integer studentId) {
		logger.debug("Searching for student with studentId ::" + studentId);
		Student student = getStudentById(studentId);
		return student;
	}

	private Student getStudentById(Integer studentId) {
		logger.info("Searching from student database for studentId : " + studentId);
		return studentDB.get(studentId);
	}
}
