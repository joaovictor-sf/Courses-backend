package com.gestao.backend.domain;

import com.gestao.backend.domain.dto.CourseDTO;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "courses")
@Entity(name = "course")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private String videoUrl;
    private Boolean active;

    public Course(CourseDTO course) {
        this.name = course.name();
        this.description = "";
        this.imageUrl = "";
        this.videoUrl = "";
        this.active = false;
    }

}
