package com.gestao.backend.service;

import com.gestao.backend.domain.Course;
import com.gestao.backend.domain.dto.CourseDTO;

import java.util.List;

public interface CourseService {

    CourseDTO getCourse(Long id);

    List<CourseDTO> findAllValidad();
    //List<Course> findAllValidad();
    List<CourseDTO> findAllNotValidad();
    //List<Course> findAllNotValidad();
    void add(CourseDTO course);
    Course update(CourseDTO course);
    void invalidar(Long id);
    void validar(Long id);

    void delete(Long id);
}
