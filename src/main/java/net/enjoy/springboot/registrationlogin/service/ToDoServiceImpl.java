package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.entity.ToDo;
import net.enjoy.springboot.registrationlogin.repository.ToDoRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ToDoServiceImpl implements ToDoService{

    private final ToDoRepository toDoRepository;

    public ToDoServiceImpl(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @Override
    public List<ToDo> getAllToDos() {
        return toDoRepository.findByUsername(getLoggedInUsername());
    }

    @Override
    public ToDo getToDo(long  id) {
        return toDoRepository.findById(id).orElse(null);
    }

    @Override
    public void addToDo(ToDo todo) {
        todo.setUsername(getLoggedInUsername());
        toDoRepository.save(todo);
    }

    @Override
    public ToDo generateEmptyToDo() {
        return new ToDo(0, getLoggedInUsername(), "",
                LocalDate.now().plusYears(1), false);
    }

    @Override
    public void deleteToDo(long id) {
        toDoRepository.deleteById(id);
    }

    private static String getLoggedInUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
