package com.techmentor.Repo;

import com.techmentor.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepo extends JpaRepository<Course, Integer> {

	List<Course> findByEnrollableTrue();

}
