package com.example.RestDemo.service;

import com.example.RestDemo.dto.CreateCourseRequest;
import com.example.RestDemo.entity.Course;
import com.example.RestDemo.repository.CourseRepository;
import com.example.RestDemo.repository.FacultyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final FacultyRepository facultyService;

    public Optional<Course> findById(Long courseId) {
        return courseRepository.findById(courseId);
    }

    public Long createCourse(CreateCourseRequest request) {
        Course course = mapCreateCourseRequestToCourse(request);
        return courseRepository.save(course).getId();
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Boolean deleteCourseById(Long id) {
        if (!courseRepository.existsById(id)) {
            return false;
        }
        courseRepository.deleteById(id);
        return true;
    }

    public Course updateCourse(CreateCourseRequest request) {
        if (!courseRepository.existsById(request.id().orElse(-1L))) {
            return null;
        }
        Course course = mapCreateCourseRequestToCourse(request);
        return courseRepository.save(course);
    }

    private Course mapCreateCourseRequestToCourse(CreateCourseRequest request) {
        Course course = new Course();
        if (request.id().isPresent()) {
            course.setId(request.id().get());
        }
        course.setName(request.name());
        course.setDescription(request.description());
        course.setDepartment(request.department());
        course.setSemester(request.semester());
        course.setYear(request.year());
        if (request.facultyId().isPresent()) {
            course.setFaculty(facultyService.findById(request.facultyId().get()).orElse(null));
        }
        return course;
    }
}
