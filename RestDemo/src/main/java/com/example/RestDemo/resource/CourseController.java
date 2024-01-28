package com.example.RestDemo.resource;

import com.example.RestDemo.core.APICustomResponse;
import com.example.RestDemo.core.GenericController;
import com.example.RestDemo.dto.CreateCourseRequest;
import com.example.RestDemo.entity.Course;
import com.example.RestDemo.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/courses")
@Slf4j
public class CourseController extends GenericController {
    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<APICustomResponse> createCourse(@RequestBody CreateCourseRequest request) {
        Long id = courseService.createCourse(request);
        if (id != null) {
            return createResponse(id, "Success", HttpStatus.CREATED);
        }
        return createResponse(null, "Failed", HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<APICustomResponse> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        if (courses != null) {
            return createResponse(courses, "Success", HttpStatus.OK);
        }
        return createResponse(null, "Not Found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APICustomResponse> deleteCourse(@PathVariable Long id) {
        Boolean result = courseService.deleteCourseById(id);
        if (!result) {
            return createResponse(null, "Failed", HttpStatus.BAD_REQUEST);
        }
        return createResponse(null, "Success", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<APICustomResponse> updateCourse(@RequestBody CreateCourseRequest request) {
        Course course = courseService.updateCourse(request);
        if (course != null) {
            return createResponse(course, "Success", HttpStatus.CREATED);
        }
        return createResponse(null, "Failed", HttpStatus.BAD_REQUEST);
    }
}
