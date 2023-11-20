package com.gestao.backend.repository;

import com.gestao.backend.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAllByActiveTrue();
    List<Course> findAllByActiveFalse();

    List<Course> findAllByUserMatricula(String matricula);
}
