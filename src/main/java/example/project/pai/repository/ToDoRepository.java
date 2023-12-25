package example.project.pai.repository;

import example.project.pai.entity.ToDo;
import example.project.pai.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository <ToDo, Long> {
    List<ToDo> findByUser(User user);
}
