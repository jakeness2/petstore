package com.example.RestDemo.dto;

import java.util.List;
import java.util.Optional;

public record CreateFacultyRequest(Optional<Long> id,
                                   String name,
                                   String email,
                                   String department,
                                   Optional<List<Long>> courseIds) {
}
