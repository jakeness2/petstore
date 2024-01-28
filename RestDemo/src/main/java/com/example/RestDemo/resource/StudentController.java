package com.example.RestDemo.resource;

import com.example.RestDemo.core.APICustomResponse;
import com.example.RestDemo.core.GenericController;
import com.example.RestDemo.dto.CreateStudentRequest;
import com.example.RestDemo.entity.Student;
import com.example.RestDemo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentController extends GenericController {
    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<APICustomResponse> createStudent(@RequestBody CreateStudentRequest request) {
        Long id = studentService.createStudent(request);
        if (id != null) {
            return createResponse(id, "Success", HttpStatus.CREATED);
        }
        return createResponse(null, "Failed", HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<APICustomResponse> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        if (students != null) {
            return createResponse(students, "Success", HttpStatus.OK);
        }
        return createResponse(null, "Not Found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APICustomResponse> deleteStudent(@PathVariable Long id) {
        Boolean result = studentService.deleteStudentById(id);
        if (!result) {
            return createResponse(null, "Failed", HttpStatus.BAD_REQUEST);
        }
        return createResponse(null, "Success", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<APICustomResponse> updateStudent(@RequestBody CreateStudentRequest request) {
        Student student = studentService.updateStudent(request);
        if (student != null) {
            return createResponse(student, "Success", HttpStatus.CREATED);
        }
        return createResponse(null, "Failed", HttpStatus.BAD_REQUEST);
    }
}
