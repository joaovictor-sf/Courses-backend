package com.gestao.backend.domain.dto;

import com.gestao.backend.domain.User;

public record CourseDTO(Long id, String name, String description, String imageUrl, String videoUrl, String userMatricula, User user) {
    public CourseDTO(Long id, String name, String description, String imageUrl, String videoUrl, String userMatricula) {
        this(id, name, description, imageUrl, videoUrl, userMatricula, null);
    }
}
