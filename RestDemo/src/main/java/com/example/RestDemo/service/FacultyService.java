package com.example.RestDemo.service;

import com.example.RestDemo.dto.CreateFacultyRequest;
import com.example.RestDemo.entity.Course;
import com.example.RestDemo.entity.Faculty;
import com.example.RestDemo.repository.FacultyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FacultyService {
    private final FacultyRepository facultyRepository;
    private final CourseService courseService;

    public Long createFaculty(CreateFacultyRequest request) {
        Faculty faculty = mapCreateFacultyRequestToFaculty(request);
        return facultyRepository.save(faculty).getId();
    }

    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    public Boolean deleteFacultyById(Long id) {
        if (!facultyRepository.existsById(id)) {
            return false;
        }
        facultyRepository.deleteById(id);
        return true;
    }

    public Faculty updateFaculty(CreateFacultyRequest request) {
        if (!facultyRepository.existsById(request.id().orElse(-1L))) {
            return null;
        }
        Faculty faculty = mapCreateFacultyRequestToFaculty(request);
        return facultyRepository.save(faculty);
    }

    private Faculty mapCreateFacultyRequestToFaculty(CreateFacultyRequest request) {
        Faculty faculty = new Faculty();
        if (request.id().isPresent()) {
            faculty.setId(request.id().get());
        }
        faculty.setName(request.name());
        faculty.setEmail(request.email());
        faculty.setDepartment(request.department());
        List<Long> courseIds = request.courseIds().orElse(List.of());
        List<Course> courses = courseIds.stream()
                .map(courseService::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        faculty.setCourses(courses);
        return faculty;
    }

    public Optional<Faculty> findById(Long id) {
        return facultyRepository.findById(id);
    }
}
