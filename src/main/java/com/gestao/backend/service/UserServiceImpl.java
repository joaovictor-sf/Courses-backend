package com.gestao.backend.service;

import com.gestao.backend.domain.Course;
import com.gestao.backend.domain.dto.CourseDTO;
import com.gestao.backend.domain.dto.ListaDTO;
import com.gestao.backend.repository.CourseRepository;
import com.gestao.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class UserServiceImpl implements UserService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Override
    public List<ListaDTO> findAllUserCourses(String matricula) {
        //Retornar uma lista de todos os cursos criados pelo usuario
        List<ListaDTO> list = new ArrayList<>();
        for (Course course : courseRepository.findAllByUserMatricula(matricula)) {
            ListaDTO listaDTO = new ListaDTO(course.getId(), course.getVideoUrl(),course.getName(), course.getDescription(), course.getImageUrl(), course.getUser().getMatricula(), course.getActive(), course.getStatus());
            list.add(listaDTO);
        }
        return list;
    }
}
