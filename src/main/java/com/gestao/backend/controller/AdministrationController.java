package com.gestao.backend.controller;

import com.gestao.backend.domain.dto.CourseDTO;
import com.gestao.backend.service.CourseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/adm")
public class AdministrationController {

    private final CourseServiceImpl courseService;

    @GetMapping
    public ResponseEntity getAllNoActiveCourse() {
        //var courses = courseRepository.findAllByActiveTrue();
        var courses = courseService.findAllNotValidad();
        return ResponseEntity.ok(courses);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity validateCourse(@PathVariable Long id, @RequestBody CourseDTO body) {
        courseService.validar(id);
        return ResponseEntity.ok().build();
    }

}
