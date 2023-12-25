package net.enjoy.springboot.registrationlogin.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="todo")
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min=5, message="Enter atleast 5 characters")
    private String description;

    private LocalDate targetDate;

    private boolean done;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;
}
