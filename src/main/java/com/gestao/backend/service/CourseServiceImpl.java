package com.gestao.backend.service;

import com.gestao.backend.domain.Course;
import com.gestao.backend.domain.User;
import com.gestao.backend.domain.dto.CourseDTO;
import com.gestao.backend.repository.CourseRepository;
import com.gestao.backend.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Override
    public CourseDTO getCourse(Long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isPresent()){
            Course course = optionalCourse.get();
            CourseDTO courseDTO = new CourseDTO(course.getId(), course.getName(), course.getDescription(), course.getImageUrl(), course.getVideoUrl(), course.getUser().getMatricula(), course.getStatus(), course.getDescProfessor());
            return courseDTO;
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public List<CourseDTO> findAllValidad() {
        //Quero achar a matricula de cada usuario que criou o curso e retornar uma lista de cursos trocando a classe User pela matricula do usuario
        List<CourseDTO> list = new ArrayList<>();
        for (Course course : courseRepository.findAllByActiveTrue()) {
            CourseDTO courseDTO = new CourseDTO(course.getId(), course.getName(), course.getDescription(), course.getImageUrl(), course.getVideoUrl(), course.getUser().getMatricula(), course.getStatus(), course.getDescProfessor());
            list.add(courseDTO);
        }
        return list;
        //return courseRepository.findAllByActiveTrue().stream().map(course -> new CourseDTO(course.getId(), course.getName(), course.getDescription(), course.getImageUrl(), course.getVideoUrl(), course.getUser().getMatricula())).collect(Collectors.toList());
        //return courseRepository.findAllByActiveTrue();
    }

    @Override
    public List<CourseDTO> findAllNotValidad() {
        List<CourseDTO> list = new ArrayList<>();
        for (Course course : courseRepository.findAllByActiveFalse()) {
            CourseDTO courseDTO = new CourseDTO(course.getId(), course.getName(), course.getDescription(), course.getImageUrl(), course.getVideoUrl(), course.getUser().getMatricula(), course.getStatus(), course.getDescProfessor());
            list.add(courseDTO);
        }
        return list;
        //return courseRepository.findAllByActiveFalse();
    }

    @Override
    public void add(@RequestBody @Valid CourseDTO course) {
        Optional<User> optionalUser = userRepository.findById(course.userMatricula());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Course newCourse = new Course(course, user);
            courseRepository.save(newCourse);
        } else {
            throw new EntityNotFoundException();
        }

        //Course newCourse = new Course(course, user);
        //courseRepository.save(newCourse);
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
            newCourse.setStatus("0");
            newCourse.setDescProfessor(course.descProfessor());

            Optional<User> optionalUser = userRepository.findById(course.userMatricula());
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                newCourse.setUser(user);
            } else {
                throw new EntityNotFoundException();
            }

            //newCourse.setActive(course.active());
            return courseRepository.save(newCourse);
        }
        //return null;
        throw new EntityNotFoundException();
    }

    @Override
    public void invalidar(Long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isPresent()){
            Course course = optionalCourse.get();
            course.setActive(false);
            course.setStatus("2");
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
            course.setStatus("1");
            courseRepository.save(course);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public void delete(Long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isPresent()){
            Course course = optionalCourse.get();
            courseRepository.delete(course);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
