package com.example.RestDemo.resource;

import com.example.RestDemo.core.APICustomResponse;
import com.example.RestDemo.core.GenericController;
import com.example.RestDemo.dto.CreateFacultyRequest;
import com.example.RestDemo.entity.Faculty;
import com.example.RestDemo.service.FacultyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/faculties")
public class FacultyController extends GenericController {
    private final FacultyService facultyService;

    @PostMapping
    public ResponseEntity<APICustomResponse> createFaculty(@RequestBody CreateFacultyRequest request) {
        Long id = facultyService.createFaculty(request);
        if (id != null) {
            return createResponse(id, "Success", HttpStatus.CREATED);
        }
        return createResponse(null, "Failed", HttpStatus.BAD_REQUEST);
    }
    @GetMapping
    public ResponseEntity<APICustomResponse> getAllFaculties() {
        List<Faculty> faculties = facultyService.getAllFaculties();
        if (faculties != null) {
            return createResponse(faculties, "Success", HttpStatus.OK);
        }
        return createResponse(null, "Not Found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APICustomResponse> deleteFaculty(@PathVariable Long id) {
        Boolean result = facultyService.deleteFacultyById(id);
        if (!result) {
            return createResponse(null, "Failed", HttpStatus.BAD_REQUEST);
        }
        return createResponse(null, "Success", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<APICustomResponse> updateFaculty(@RequestBody CreateFacultyRequest request) {
        Faculty faculty = facultyService.updateFaculty(request);
        if (faculty != null) {
            return createResponse(faculty, "Success", HttpStatus.CREATED);
        }
        return createResponse(null, "Failed", HttpStatus.BAD_REQUEST);
    }
}
