package com.gestao.backend.service;

import com.gestao.backend.domain.Course;
import com.gestao.backend.domain.dto.CourseDTO;
import com.gestao.backend.repository.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    @Override
    public List<Course> findAllValidad() {
        return courseRepository.findAllByActiveTrue();
    }

    @Override
    public List<Course> findAllNotValidad() {
        return courseRepository.findAllByActiveFalse();
    }

    @Override
    public void add(@RequestBody @Valid CourseDTO course) {
        Course newCourse = new Course(course);
        courseRepository.save(newCourse);
        //return courseRepository.save(newCourse);
    }

    @Override
    public Course update(CourseDTO course) {
        Optional<Course> optionalCourse = courseRepository.findById(course.id());
        if (optionalCourse.isPresent()){
            Course newCourse = optionalCourse.get();
            newCourse.setName(course.name());
            newCourse.setDescription(course.description());
            newCourse.setImageUrl(course.imageUrl());
            newCourse.setVideoUrl(course.videoUrl());
            //newCourse.setActive(course.active());
            return courseRepository.save(newCourse);
        }
        //return null;
        throw new EntityNotFoundException();
    }

    @Override
    public void delete(Long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isPresent()){
            Course course = optionalCourse.get();
            course.setActive(false);
            courseRepository.save(course);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public void validar(Long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isPresent()){
            Course course = optionalCourse.get();
            course.setActive(true);
            courseRepository.save(course);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
