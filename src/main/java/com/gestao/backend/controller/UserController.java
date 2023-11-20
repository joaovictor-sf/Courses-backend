package com.gestao.backend.controller;

import com.gestao.backend.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/user")
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping("/{matricula}")
    @Transactional
    public ResponseEntity getAllUserCourses(@PathVariable String matricula) {
        return ResponseEntity.ok(userService.findAllUserCourses(matricula));
    }
}
