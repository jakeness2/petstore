package com.example.RestDemo.dto;

import java.util.Optional;

public record CreateCourseRequest(Optional<Long> id,
                                  String name,
                                  String description,
                                  Optional<Long> facultyId,
                                  String department,
                                  String semester,
                                  String year) {}
