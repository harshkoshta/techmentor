package com.techmentor.Service;

import com.techmentor.Model.Course;
import com.techmentor.Repo.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
@Autowired
private CourseRepo courseRepo;
public List<Course> loadallcourses() {
	List<Course> list = courseRepo.findAll();
        
	return list;
}
public List<Course> loadallenrollable() {
	List<Course> list = courseRepo.findByEnrollableTrue();
	return list;
}
public Course saveCourseService(Course course) {
	Course save = courseRepo.save(course);
	return save;
}
public Course loadcourse(int id) {
	Optional<Course> list = courseRepo.findById(id);
	return list.get();
}
public boolean deletecourse(int id) {
	Course course = this.loadcourse(id);
	if(course != null) {
	courseRepo.deleteById(id);
	return true;
	}
	return false;
}
}
