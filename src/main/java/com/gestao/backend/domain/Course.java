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
    @Column(nullable = false)
    private String name;
    private String description;
    private String imageUrl;
    private String videoUrl;
    private Boolean active;
    private String status;
    private String descProfessor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)//FetchType.LAZY: carrega o objeto apenas quando for necessário. optional = false: não permite que o objeto seja nulo
    @JoinColumn(name = "user_matricula", nullable = false)
    private User user;
    //@ManyToOne
    //@JoinColumn(name = "user_matricula", nullable = false)
    public Course(CourseDTO course) {
        this.name = course.name();
        this.description = "";
        this.imageUrl = "";
        this.videoUrl = "";
        this.active = false;
        this.user = course.user();
    }

    public Course(CourseDTO course, User user) {
        this.name = course.name();
        this.description = course.description();
        this.imageUrl = "";
        this.videoUrl = course.videoUrl();
        this.active = false;
        this.user = user;
        this.status = "0";
        this.descProfessor = "";
    }

}
