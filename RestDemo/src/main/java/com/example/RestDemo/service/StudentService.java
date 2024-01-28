package com.example.RestDemo.service;

import com.example.RestDemo.dto.CreateStudentRequest;
import com.example.RestDemo.entity.Course;
import com.example.RestDemo.entity.Student;
import com.example.RestDemo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseService courseService;

    public Long createStudent(CreateStudentRequest createStudentRequest) {
        Student student = mapCreateStudentRequestToStudent(createStudentRequest);
        return studentRepository.save(student).getId();
    }

    private Student mapCreateStudentRequestToStudent(CreateStudentRequest createStudentRequest) {
        Student student = new Student();
        if (createStudentRequest.id().isPresent()) {
            student.setId(createStudentRequest.id().get());
        }
        student.setName(createStudentRequest.name());
        student.setEmail(createStudentRequest.email());
        student.setPhone(createStudentRequest.phone());

        List<Long> courseIds = createStudentRequest.courseIds().orElse(List.of());
        List<Course> courses = courseIds.stream()
                .map(courseService::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        student.setCourseList(courses);
        return student;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Boolean deleteStudentById(Long id) {
        if (!studentRepository.existsById(id)) {
            return false;
        }
        studentRepository.deleteById(id);
        return true;
    }

    public Student updateStudent(CreateStudentRequest request) {
        if (!studentRepository.existsById(request.id().orElse(-1L))) {
            return null;
        }
        Student student = mapCreateStudentRequestToStudent(request);
        return studentRepository.save(student);
    }
}
