package com.gestao.backend.controller;

import com.gestao.backend.domain.Course;
import com.gestao.backend.domain.dto.CourseDTO;
import com.gestao.backend.service.CourseServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/course")
public class CourseController {

    //private final CourseRepository courseRepository;
    private final CourseServiceImpl courseService;

    @GetMapping
    public ResponseEntity getAllCourse() {
        //var courses = courseRepository.findAllByActiveTrue();
        var courses = courseService.findAllValidad();
        return ResponseEntity.ok(courses);
    }

    @PostMapping
    public ResponseEntity registerCourse(@RequestBody @Valid CourseDTO data) {
        courseService.add(data);
        //Course course = new Course(data);
        //courseRepository.save(course);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateCourse(@RequestBody @Valid CourseDTO data) {
        Course course = courseService.update(data);

        if (course != null) return ResponseEntity.ok(course);

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteCourse(@PathVariable Long id) {
        courseService.delete(id);
        return ResponseEntity.ok().build();
    }

}
