package net.enjoy.springboot.registrationlogin.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jakarta.websocket.server.PathParam;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="todo")
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;

    @Size(min=5, message="Enter atleast 5 characters")
    private String description;

    private LocalDate targetDate;
    private boolean done;
}