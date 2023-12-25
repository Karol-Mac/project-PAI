package example.project.pai.service;

import example.project.pai.repository.UserRepository;
import example.project.pai.entity.ToDo;
import example.project.pai.entity.User;
import example.project.pai.repository.ToDoRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ToDoServiceImpl implements ToDoService{

    private final ToDoRepository toDoRepository;

    private final UserRepository userRepository;

    public ToDoServiceImpl(ToDoRepository toDoRepository, UserRepository userRepository) {
        this.toDoRepository = toDoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<ToDo> getAllToDos() {

        return toDoRepository.findByUser(getLoggedInUser());

    }

    @Override
    public ToDo getToDo(long  id) {
        return toDoRepository.findById(id).orElse(null);
    }

    @Override
    public void addToDo(ToDo todo) {
        todo.setUser(getLoggedInUser());
        toDoRepository.save(todo);
    }

    @Override
    public ToDo generateEmptyToDo() {
        return new ToDo(0, "",LocalDate.now().plusYears(1),
                        false, getLoggedInUser());
    }

    @Override
    public void deleteToDo(long id) {
        toDoRepository.deleteById(id);
    }

    private User getLoggedInUser() {

        return userRepository.findByEmail(SecurityContextHolder.getContext()
                                    .getAuthentication().getName()).orElse(null);
    }

}
