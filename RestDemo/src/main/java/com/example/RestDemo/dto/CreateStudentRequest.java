package com.example.RestDemo.dto;

import java.util.List;
import java.util.Optional;

public record CreateStudentRequest(Optional<Long> id,
                                   String name,
                                   String email,
                                   String phone,
                                   Optional<List<Long>> courseIds) {}
