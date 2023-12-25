package example.project.pai.service;

import example.project.pai.entity.ToDo;

import java.util.List;

public interface ToDoService {

    List<ToDo> getAllToDos();

    ToDo getToDo(long id);
    void addToDo(ToDo todo);

    ToDo generateEmptyToDo();

    void deleteToDo(long id);
}
