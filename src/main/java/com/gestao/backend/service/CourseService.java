package com.gestao.backend.service;

import com.gestao.backend.domain.Course;
import com.gestao.backend.domain.dto.CourseDTO;

import java.util.List;

public interface CourseService {
    List<Course> findAllValidad();
    List<Course> findAllNotValidad();
    void add(CourseDTO course);
    Course update(CourseDTO course);
    void delete(Long id);
    void validar(Long id);
}
