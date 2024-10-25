package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Entity
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User student; // The student registered for the course

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course; // The course the student is registered for

    private LocalDate registrationDate; // Date of registration

    // Getters and Setters
}
